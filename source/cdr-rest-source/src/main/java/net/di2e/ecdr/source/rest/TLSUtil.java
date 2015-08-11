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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.cxf.configuration.jsse.TLSClientParameters;
import org.apache.cxf.jaxrs.client.ClientConfiguration;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.transport.http.HTTPConduit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class TLSUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger( TLSUtil.class );
    private static final String SSL_KEYSTORE_JAVA_PROPERTY = "javax.net.ssl.keyStore";
    private static final String SSL_KEYSTORE_PASSWORD_JAVA_PROPERTY = "javax.net.ssl.keyStorePassword";

    private TLSUtil() {
    }

    public static void setTLSOptions( WebClient client, boolean disableCNCheck ) {
        ClientConfiguration clientConfiguration = WebClient.getConfig( client );

        HTTPConduit httpConduit = clientConfiguration.getHttpConduit();

        String keyStorePath = System.getProperty( SSL_KEYSTORE_JAVA_PROPERTY );
        String keyStorePassword = System.getProperty( SSL_KEYSTORE_PASSWORD_JAVA_PROPERTY );
        if ( StringUtils.isNotBlank( keyStorePath ) && StringUtils.isNotBlank( keyStorePassword ) ) {
            try {
                TLSClientParameters tlsParams = new TLSClientParameters();
                LOGGER.debug( "Setting disable of CN check on client URL {} to [{}]", client.getCurrentURI(), disableCNCheck );
                tlsParams.setDisableCNCheck( disableCNCheck );

                KeyStore keyStore = KeyStore.getInstance( KeyStore.getDefaultType() );

                // add the keystore if it exists
                File keystore = new File( keyStorePath );
                if ( keystore.exists() && keyStorePassword != null ) {
                    FileInputStream fis = new FileInputStream( keystore );
                    try {
                        LOGGER.debug( "Loading keyStore {}", keystore );
                        keyStore.load( fis, keyStorePassword.toCharArray() );
                    } catch ( IOException e ) {
                        LOGGER.error( "Unable to load keystore. {}", keystore, e );
                    } catch ( CertificateException e ) {
                        LOGGER.error( "Unable to load certificates from keystore. {}", keystore, e );
                    } finally {
                        IOUtils.closeQuietly( fis );
                    }
                    KeyManagerFactory keyFactory = KeyManagerFactory.getInstance( KeyManagerFactory.getDefaultAlgorithm() );
                    keyFactory.init( keyStore, keyStorePassword.toCharArray() );
                    KeyManager[] km = keyFactory.getKeyManagers();
                    tlsParams.setKeyManagers( km );
                }

                httpConduit.setTlsClientParameters( tlsParams );
            } catch ( KeyStoreException e ) {
                LOGGER.error( "Unable to read keystore: ", e );
            } catch ( NoSuchAlgorithmException e ) {
                LOGGER.error( "Problems creating SSL socket. Usually this is " + "referring to the certificate sent by the server not being trusted by the client.", e );
            } catch ( FileNotFoundException e ) {
                LOGGER.error( "Unable to locate one of the SSL stores: {} | {}", keyStorePath, e );
            } catch ( UnrecoverableKeyException e ) {
                LOGGER.error( "Unable to read keystore: ", e );
            }
        }
    }

}
