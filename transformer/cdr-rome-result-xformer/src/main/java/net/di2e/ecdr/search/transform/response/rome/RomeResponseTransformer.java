/**
 * Copyright (C) 2014 Cohesive Integrations, LLC (info@cohesiveintegrations.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.di2e.ecdr.search.transform.response.rome;

import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import ddf.catalog.data.Metacard;
import ddf.catalog.data.Result;
import ddf.catalog.data.impl.MetacardImpl;
import ddf.catalog.data.impl.ResultImpl;
import ddf.catalog.operation.QueryRequest;
import ddf.catalog.operation.SourceResponse;
import ddf.catalog.operation.impl.SourceResponseImpl;
import net.di2e.ecdr.api.queryresponse.SearchResponseTransformer;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Map;

public class RomeResponseTransformer implements SearchResponseTransformer {

    private static final Logger LOGGER = LoggerFactory.getLogger( RomeResponseTransformer.class );

    @Override
    public SourceResponse processSearchResponse( InputStream inputStream, QueryRequest request, String siteName ) {
        ArrayList<Result> resultList = new ArrayList<>();
        Map<String, Serializable> responseProperties = null;
        SyndFeedInput input = new SyndFeedInput();
        try {
            SyndFeed feed = input.build( new XmlReader( inputStream ) );
            if (LOGGER.isTraceEnabled()) {
                LOGGER.trace( "Marshaled response into feed object: \n{}", feed.toString() );
            }
            for ( SyndEntry entry : feed.getEntries() ) {
                resultList.add( convertEntryToResult( entry, siteName ) );
            }

        } catch ( IOException | FeedException e ) {
            LOGGER.warn( "Could not transform response to metacards, returning 0 results.", e );
        }
        int totalResults = resultList.size();
        return new SourceResponseImpl( request, responseProperties, resultList, totalResults );
    }

    private Result convertEntryToResult( SyndEntry entry, String siteName ) {
        ResultImpl result = new ResultImpl();
        result.setMetacard( convertEntryToMetacard( entry, siteName ) );
        return result;
    }

    private Metacard convertEntryToMetacard( SyndEntry entry, String siteName ) {
        MetacardImpl metacard = new MetacardImpl();
        String id = entry.getUri();
        String link = entry.getLink();
        // might have had an issue with a whitespace in the ID so this is just a safety check
        id = StringUtils.deleteWhitespace( id );
        metacard.setId( id );
        if ( entry.getLink() != null ) {
            try {
                metacard.setResourceURI( new URI( link ) );
            } catch ( URISyntaxException use ) {
                LOGGER.warn( "Could not set URI due to bad link in data.", use );
            }
        }
        metacard.setSourceId( siteName );
        metacard.setTitle( entry.getTitle() );
        metacard.setCreatedDate( entry.getPublishedDate() );
        if ( entry.getUpdatedDate() != null ) {
            metacard.setModifiedDate( entry.getUpdatedDate() );
        }
        SyndContent content = entry.getDescription();
        if ( content.getType().equals( "text/xml" ) ) {
            metacard.setMetadata( content.toString() );
        } else {
            metacard.setMetadata( "<xml-fragment>" + content.toString() + "</xml-fragment>" );
        }
        return metacard;
    }
}
