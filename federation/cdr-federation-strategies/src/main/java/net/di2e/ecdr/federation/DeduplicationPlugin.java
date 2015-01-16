/**
 * Copyright (c) Cohesive Integrations, LLC
 *
 * This is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details. A copy of the GNU Lesser General Public License is distributed along with this program and can be found at
 * <http://www.gnu.org/licenses/lgpl.html>.
 *
 **/
package net.di2e.ecdr.federation;

import ddf.catalog.data.Result;
import ddf.catalog.operation.QueryRequest;
import ddf.catalog.operation.QueryResponse;
import ddf.catalog.plugin.PluginExecutionException;
import ddf.catalog.plugin.PostQueryPlugin;
import ddf.catalog.plugin.StopProcessingException;
import net.di2e.ecdr.commons.constants.SearchConstants;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class DeduplicationPlugin implements PostQueryPlugin {

    @Override
    public QueryResponse process( QueryResponse queryResponse ) throws PluginExecutionException, StopProcessingException {
        QueryRequest queryRequest = queryResponse.getRequest();
        if ( queryRequest != null && queryRequest.containsPropertyName( SearchConstants.DEDUPLICATION_PARAMETER ) ) {
            String dedupStr = String.valueOf( queryRequest.getPropertyValue( SearchConstants.DEDUPLICATION_PARAMETER ) );
            if ( Boolean.valueOf( dedupStr ) ) {
                deduplicateResults( queryResponse.getResults() );
            }
        }
        return queryResponse;
    }

    private void deduplicateResults( List<Result> results ) {
        HashSet<String> idSet = new HashSet<>( results.size() );
        Iterator<Result> iterator = results.iterator();
        while ( iterator.hasNext() ) {
            Result curResult = iterator.next();
            if ( idSet.contains( curResult.getMetacard().getId() ) ) {
                // duplicate entry
                iterator.remove();
            } else {
                idSet.add( curResult.getMetacard().getId() );
            }
        }
    }

}
