/**
 * Copyright (C) 2014 Cohesive Integrations, LLC (info@cohesiveintegrations.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.di2e.ecdr.libs.result.relevance;

import ddf.catalog.data.Result;
import ddf.catalog.data.impl.ResultImpl;
import ddf.catalog.filter.FilterAdapter;
import ddf.catalog.operation.Query;
import ddf.catalog.source.UnsupportedQueryException;
import net.di2e.ecdr.commons.constants.SearchConstants;
import net.di2e.ecdr.commons.filter.StrictFilterDelegate;
import net.di2e.ecdr.commons.filter.config.FilterConfig;
import net.di2e.ecdr.commons.query.CDRQueryImpl;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.StopWatch;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import org.opengis.filter.sort.SortBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Normalizes the Relevance of a result set by looking at the contextual criteria, then doing a local calculation of
 * relevance based on the localized result set
 */
public class RelevanceNormalizer {

    public static final String RELEVANCE_TIMER = "RELEVANCE TIMER:";

    private static final Logger LOGGER = LoggerFactory.getLogger( RelevanceNormalizer.class );
    private static final String METADATA_FIELD = "metadata";
    private static final String ID_FIELD = "id";

    private FilterAdapter filterAdapter;

    public RelevanceNormalizer( FilterAdapter filterAdapter ) {
        this.filterAdapter = filterAdapter;
    }

    /**
     * Normalize the relevance score for the results in the query response based on the contextual query criteria
     *
     * @param results
     * @param originalQuery
     * @return
     */
    public List<Result> normalize( List<Result> results, Query originalQuery ) {

        SortBy sortBy = originalQuery.getSortBy();
        // We want to do relevance sort if no sort order was specfied or if Relevance sort was specified
        if ( sortBy == null || sortBy.getPropertyName() == null || sortBy.getPropertyName().getPropertyName() == null || Result.RELEVANCE.equals( sortBy.getPropertyName().getPropertyName() ) ) {

            Map<String, String> filterParameters = getFilterParameters( originalQuery );

            if ( canNormalizeQuery( originalQuery, filterParameters ) ) {
                LOGGER.debug( "Query contained search phrase and will be sorted by relevance, performing re-indexing to normalize relevance." );
                Directory directory = null;
                DirectoryReader iReader = null;
                Map<String, Result> docMap = new HashMap<>();
                List<Result> updatedResults = new ArrayList<>();
                StopWatch stopWatch = new StopWatch();
                stopWatch.start();
                try {
                    Analyzer analyzer = new StandardAnalyzer();

                    // create memory-stored index
                    directory = new RAMDirectory();

                    IndexWriterConfig config = new IndexWriterConfig( Version.LATEST, analyzer );
                    IndexWriter iWriter = new IndexWriter( directory, config );

                    // loop through all of the results and add them to the index
                    for ( Result curResult : results ) {
                        Document doc = new Document();
                        String text = TextParser.parseTextFrom( curResult.getMetacard().getMetadata() );
                        String uuid = UUID.randomUUID().toString();
                        doc.add( new Field( METADATA_FIELD, text, TextField.TYPE_STORED ) );
                        doc.add( new Field( ID_FIELD, uuid, TextField.TYPE_STORED ) );
                        iWriter.addDocument( doc );
                        docMap.put( uuid, curResult );
                    }

                    IOUtils.closeQuietly( iWriter );
                    LOGGER.debug( "{} Document indexing finished in {} seconds.", RELEVANCE_TIMER, (double) stopWatch.getTime() / 1000.0 );
                    // Now search the index:
                    iReader = DirectoryReader.open( directory );
                    IndexSearcher iSearcher = new IndexSearcher( iReader );
                    // Parse a simple query that searches for "text":
                    QueryParser parser = new QueryParser( METADATA_FIELD, analyzer );
                    org.apache.lucene.search.Query query = getQuery( parser, filterParameters );
                    ScoreDoc[] hits = iSearcher.search( query, null, docMap.size() ).scoreDocs;
                    LOGGER.debug( "Got back {} results", hits.length );

                    // loop through the indexed search results and update the scores in the original query results
                    for ( ScoreDoc curHit : hits ) {
                        Document doc = iSearcher.doc( curHit.doc );
                        String uuid = doc.getField( ID_FIELD ).stringValue();
                        Result result = docMap.get( uuid );
                        docMap.remove( uuid );
                        updatedResults.add( updateResult( result, curHit.score ) );
                        LOGGER.debug( "Relevance for result {} was changed FROM {} TO {}", result.getMetacard().getId(), result.getRelevanceScore(), curHit.score );
                    }
                    // check if there are any results left that did not match the keyword query
                    for (Map.Entry<String, Result> curEntry : docMap.entrySet()) {
                        // add result in with 0 relevance score
                        updatedResults.add( updateResult( curEntry.getValue(), 0 ) );
                    }
                    // create new query response
                    return updatedResults;

                } catch ( ParseException | IOException | RuntimeException e ) {
                    LOGGER.warn( "Received an exception while trying to perform re-indexing, sending original queryResponse on.", e );
                    return results;
                } finally {
                    IOUtils.closeQuietly( iReader );
                    IOUtils.closeQuietly( directory );
                    stopWatch.stop();
                    LOGGER.debug( "{} Total relevance process took {} seconds.", RELEVANCE_TIMER, (double) stopWatch.getTime() / 1000.0 );
                }
            } else {
                LOGGER.debug( "Query does not contain search phrase or does not use the keyword query language. Skipping relevance normalization." );
            }
        } else {
            LOGGER.debug( "Query is not sorted based on relevance with contextual criteria. Skipping relevance normalization." );
        }
        return results;
    }

    /**
     * Checks to see if this query can be normalized.
     *
     * @param filterParameters parameters from original ddf query
     * @return true if this query can be normalzed, false if not
     */
    protected boolean canNormalizeQuery( Query query, Map<String, String> filterParameters ) {
        boolean canNormalize = StringUtils.isNotBlank( getSearchPhrase( filterParameters ) );
        if ( canNormalize && query instanceof CDRQueryImpl ) {
            CDRQueryImpl cdrQuery = (CDRQueryImpl) query;
            if ( cdrQuery.getQueryParameters().containsKey( SearchConstants.QUERYLANGUAGE_PARAMETER ) ) {
                List<String> languageParam = cdrQuery.getQueryParameters().get( SearchConstants.QUERYLANGUAGE_PARAMETER );
                if (languageParam != null && !languageParam.isEmpty()) {
                    canNormalize = StringUtils.equals( languageParam.get( 0 ), SearchConstants.CDR_KEYWORD_QUERY_LANGUAGE );
                }
            }
        }
        return canNormalize;
    }

    protected org.apache.lucene.search.Query getQuery( QueryParser parser, Map<String, String> filterParameters ) throws ParseException {
        String searchPhrase = getSearchPhrase( filterParameters );
        org.apache.lucene.search.Query query = parser.parse( searchPhrase );
        if ( StringUtils.equals( filterParameters.get( SearchConstants.FUZZY_PARAMETER ), "1" ) ) {
            // should get a boolean query for keyword-based searches
            if ( query instanceof BooleanQuery ) {
                BooleanQuery booleanQuery = (BooleanQuery) query;
                for ( BooleanClause clause : booleanQuery.getClauses() ) {
                    if ( clause.getQuery() instanceof TermQuery ) {
                        TermQuery oldQuery = (TermQuery) clause.getQuery();
                        FuzzyQuery newQuery = new FuzzyQuery( oldQuery.getTerm() );
                        clause.setQuery( newQuery );
                    }
                }
            } else {
                LOGGER.debug( "Query was too complex for adding fuzzy. Expected BooleanQuery but ended up being of type {}", query.getClass().getName() );
            }
        }

        return query;
    }

    /**
     * Pull out the string-based search phrase from a query.
     *
     * @param filterParameters
     *            filterparameters from the original query
     * @return Search phrase or null if no search phrase was found.
     */
    protected String getSearchPhrase( Map<String, String> filterParameters ) {
        String searchPhrase = null;
        if ( filterParameters.containsKey( SearchConstants.KEYWORD_PARAMETER ) ) {
            searchPhrase = filterParameters.get( SearchConstants.KEYWORD_PARAMETER );
        }

        return searchPhrase;
    }

    protected Map<String, String> getFilterParameters(Query originalQuery) {
        HashMap<String, String> map = new HashMap<>();
        try {
            map.putAll( filterAdapter.adapt( originalQuery, new StrictFilterDelegate( false, 50000.00, new FilterConfig() ) ) );
        } catch (UnsupportedQueryException uqe) {
            LOGGER.debug( "Query did not contain any contextual criteria (search phrases), cannot perform re-relevance on this query." );
        }
        return map;
    }

    /**
     * Creates a new result with an updated score.
     *
     * @param origResult
     *            Original result that contains an older score.
     * @param newScore
     *            New score to update the result with.
     * @return Result with updated score.
     */
    protected Result updateResult( Result origResult, float newScore ) {
        ResultImpl result = new ResultImpl( origResult.getMetacard() );
        result.setRelevanceScore( (double) newScore );
        result.setDistanceInMeters( origResult.getDistanceInMeters() );
        return result;
    }

}
