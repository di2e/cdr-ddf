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

import java.net.URL;

import javax.activation.MimeType;

import ddf.catalog.operation.ResourceResponse;
import ddf.catalog.operation.impl.ResourceResponseImpl;
import ddf.catalog.plugin.PluginExecutionException;
import ddf.catalog.plugin.PostResourcePlugin;
import ddf.catalog.plugin.StopProcessingException;
import ddf.catalog.resource.impl.ResourceImpl;

public class HTMLPreResourcePlugin implements PostResourcePlugin{


    @Override
    public ResourceResponse process( ResourceResponse request ) throws PluginExecutionException, StopProcessingException {
        System.out.println( request.getProperties());
        System.out.println( request.getPropertyNames() );
        try{
        if ( request.getProperties().get( "upshot" ) == null ){
            System.out.println( " Returning Report" );
            request = new ResourceResponseImpl( request.getRequest(), request.getProperties(), new ResourceImpl( 
                    new URL("http://psw-pf-app02.pf.cee-w.net:8787/v1/documents/?uri=10307691534560623638.json").openConnection().getInputStream(),
                    new MimeType( "text", "html" ), request.getResource().getName() ) );
        }else{
            System.out.println( "Returning JASOPN" );
        }
        }catch( Exception e ){
            e.printStackTrace( );
        }
       
        return request;
    }

}
