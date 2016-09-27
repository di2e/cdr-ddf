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
package net.di2e.ecdr.describe.commands;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.cxf.helpers.FileUtils;
import org.apache.karaf.shell.api.action.Action;
import org.apache.karaf.shell.api.action.Argument;
import org.apache.karaf.shell.api.action.Command;
import org.apache.karaf.shell.api.action.Completion;
import org.apache.karaf.shell.api.action.Option;
import org.apache.karaf.shell.api.action.lifecycle.Reference;
import org.apache.karaf.shell.api.action.lifecycle.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ddf.catalog.CatalogFramework;
import ddf.catalog.data.Metacard;
import net.di2e.ecdr.describe.generator.DescribeGenerator;
import net.di2e.ecdr.describe.publisher.DescribePublisher;

@Command(scope = "cdr", name = "describe", description = "Generates describe records for one more more local/remote Sources. "
        + "If no options are selected it will cycle through all Sources and write the files to the " + GenerateDescribeCommand.DESCRIBE_DIR + " directory")
@Service
public class GenerateDescribeCommand implements Action {

    private static final Logger LOGGER = LoggerFactory.getLogger( GenerateDescribeCommand.class );
    static final String DESCRIBE_DIR = "describe";

    @Argument(index = 0, name = "sourceIds", description = "The name of the Source/Site to generate the Describe Record for", required = false, multiValued = true)
    @Completion(value = net.di2e.ecdr.describe.commands.GenerateDescribeCompleter.class)
    private List<String> ids;

    @Option(name = "--publish", description = "publish the describe metadata to the local catalog with the tag 'describe'")
    private boolean publishDescribe = false;

    @Option(name = "--nowrite", description = "Do not write the output of the describe file to disk.  By default they are written to $DDF_HOME/describe directory")
    private boolean doNotWrite = false;

    @Option(name = "--print", description = "Print the describe records to the screen")
    private boolean print = false;

    @Reference
    private CatalogFramework framework;
    @Reference
    private DescribeGenerator describeGenerator;

    private PrintStream console = System.out;

    public GenerateDescribeCommand() {
        FileUtils.mkDir( new File( DESCRIBE_DIR ) );
    }

    @Override
    public Object execute() throws Exception {
        try {
            Map<String, Metacard> describeRecords = new HashMap<>();
            if ( CollectionUtils.isNotEmpty( ids ) ) {

                ids.forEach( sourceId -> {
                    console.println( "Generating Describe record for " + sourceId );
                    Metacard card = describeGenerator.generate( sourceId );
                    describeRecords.put( sourceId, card );
                } );
            } else {
                this.framework.getSourceIds().forEach( ( sourceId ) -> {
                    console.println( "Generating Describe record for " + sourceId );
                    Metacard card = describeGenerator.generate( sourceId );
                    describeRecords.put( sourceId, card );
                } );
            }

            if ( !doNotWrite ) {
                describeRecords.forEach( ( id, card ) -> {
                    writeToFile( id, card.getMetadata() );
                } );
            }

            if ( publishDescribe ) {
                DescribePublisher publisher = new DescribePublisher( framework );
                publisher.publish( describeRecords.values() );
                console.println( "Succesfully published " + describeRecords.size() + " describe records to catalog." );
            }

            if ( print ) {
                describeRecords.forEach( ( id, card ) -> {
                    console.println( "------------------- " + id + " -------------------" );
                    console.println( card.getMetadata() );
                    console.println( "---------------------------------------------------------" );
                } );

            }

        } catch ( Exception e ) {
            console.println( "Encountered error while trying to perform command. Check log for more details." );
            LOGGER.warn( "Error while performing command.", e );
        }
        return null;
    }

    protected void writeToFile( String sourceId, String xml ) {
        try {
            String filename = sourceId + "-describe-" + (System.currentTimeMillis() / 1000) + ".xml";
            Files.write( Paths.get( DESCRIBE_DIR, filename ), xml.getBytes(), StandardOpenOption.CREATE );
            console.println( "New describe file written to DDF_HOME/" + DESCRIBE_DIR + ": " + filename );
        } catch ( IOException e ) {
            LOGGER.error( "Could not write describe file for source {}", sourceId, e );
        }
    }

}
