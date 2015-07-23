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
package net.di2e.ecdr.source.rest;

import java.io.InputStream;

import org.apache.cxf.common.util.Base64Utility;
import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.rs.security.saml.DeflateEncoderDecoder;

public class RestAssertionTest {

    public static final String COOKIE = "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";

    public static void main( String[] args ) throws Exception {
        byte[] cv = Base64Utility.decode( COOKIE );
        DeflateEncoderDecoder decoder = new DeflateEncoderDecoder();
        InputStream is = decoder.inflateToken( cv );
        System.out.println( IOUtils.toString( is ) );
    }

}
