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
package net.di2e.ecdr.search.transform.response.upshot;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.activation.MimeType;

import org.apache.tika.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ddf.catalog.CatalogFramework;
import ddf.catalog.data.BinaryContent;
import ddf.catalog.data.Result;
import ddf.catalog.data.impl.BinaryContentImpl;
import ddf.catalog.operation.ResourceResponse;
import ddf.catalog.operation.SourceResponse;
import ddf.catalog.operation.impl.ResourceRequestById;
import ddf.catalog.transform.CatalogTransformerException;
import ddf.catalog.transform.QueryResponseTransformer;

public class UpShotTransformer implements QueryResponseTransformer {

    protected static final String CDR_ATOM_TRANSFORMER_ID = "upshot";
    private CatalogFramework framework = null;

    private static final Logger LOGGER = LoggerFactory.getLogger( UpShotTransformer.class );

    public UpShotTransformer( CatalogFramework fw ) {
        framework = fw;
    }

    @Override
    public BinaryContent transform( SourceResponse response, Map<String, Serializable> properties ) throws CatalogTransformerException {
        if ( properties == null ) {
            properties = new HashMap<String, Serializable>();
        }
        BinaryContent binaryContent = null;
        try {
            List<Result> results = response.getResults();
            StringBuilder jsonBuilder = new StringBuilder( "[" );
            int length = results.size();
            for ( int i = 0; i < length; i++ ) {
                String id = null;
                try {
                    //https://mll-pf-dib01.pf.cee-w.net:8993/services/catalog/sources/dib/3077393282f326baadcc4322aba7eef2?transform=resource
                    //String prod = IOUtils.toString( new URL("https://mll-pf-dib01.pf.cee-w.net:8993/services/catalog/sources/dib/"+results.get( i ).getMetacard().getId() +"?transform=resource").openConnection().getInputStream() );
                    //String prod = IOUtils.toString( new URL("https://mll-pf-dib01.pf.cee-w.net:8993/services/catalog/sources/dib/3077393282f326baadcc4322aba7eef2?transform=resource").openConnection().getInputStream() );
                    HashMap<String,Serializable> props = new HashMap<>();
                    props.put("upshot","upshot");
                    String prod = IOUtils.toString( framework.getLocalResource( new ResourceRequestById( results.get(i).getMetacard().getId(), props ) ).getResource().getInputStream() );
                   // System.out.println(dibResponse);
                    jsonBuilder = jsonBuilder.append( prod );

                    if ( i + 1 != length ) {
                        jsonBuilder = jsonBuilder.append( "," );
                    }
                } catch ( Exception e ) {
                    LOGGER.warn(
                            "WARNING - Could not properly transform metacard with id {} because ran into error '{}'.  Please turn on DEBUG logging for more details",
                            id, e.getMessage() );
                    LOGGER.error( e.getMessage(), e );
                }
            }
            jsonBuilder = jsonBuilder.append( "]" );

            binaryContent = new BinaryContentImpl( new ByteArrayInputStream( jsonBuilder.toString().getBytes() ), new MimeType( "application/json" ) );
        } catch ( Exception e ) {
            LOGGER.error( e.getMessage(), e );
        }
        return binaryContent;
    }

}
