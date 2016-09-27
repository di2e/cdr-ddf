/**
 * Copyright (C) 2016 Pink Summit, LLC (info@pinksummit.com)
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
package net.di2e.ecdr.describe.generator.util;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.di2e.jaxb.cdr.describe.wrapper.Describe;
import net.opengis.gml.v_3_2_1.ObjectFactory;

public final class DescribeXMLParser {
    
    private DescribeXMLParser() {
    }

    private static JAXBContext jaxbContext = null;
    private static final Logger LOGGER = LoggerFactory.getLogger( DescribeXMLParser.class );

    static {
        try {
            jaxbContext = JAXBContext.newInstance( Describe.class, ObjectFactory.class );
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    public static String marshalDescribe( Describe describe ) {
        String resultString = null;
        StringWriter writer = new StringWriter();

        try {
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE );
            marshaller.setProperty( Marshaller.JAXB_ENCODING, "UTF_8" );
            marshaller.marshal( describe, writer );
        } catch ( JAXBException e ) {
            e.printStackTrace();
        }

        resultString = writer.toString();

        LOGGER.debug( "Describe Record converted to String{}:()", System.lineSeparator(), resultString );
        return resultString;
    }

}
