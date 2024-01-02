/*
 * Copyright 2023 Pieter van den Hombergh {@code <pieter.van.den.hombergh@gmail.com>}.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.homberghp.recordmappers;

import io.github.homberghp.gensquared_annotations.Generated;
import io.github.homberghp.gensquared_annotations.ID;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

/**
 *
 * @author Pieter van den Hombergh {@code <pieter.van.den.hombergh@gmail.com>}
 */
public class RecordMapperGeneratorTest {

    //@Disabled("think TDD")
    @Test
    public void testGetFieldNamedId() {
        RecordMapperGenerator rmg = new RecordMapperGenerator( F.class );
        Optional<Field> fieldNamedId = rmg.getFieldNamedId();
        assertThat( fieldNamedId ).isNotEmpty();
        assertThat( fieldNamedId.get().getName() ).isEqualTo( "f_id" );
//        fail( "method testGetFieldNamedId reached end. You know what to do." );
    }

    //@Disabled("think TDD")
    @Test
    public void testJavaSource() {
        RecordMapperGenerator rmg = new RecordMapperGenerator( F.class );
        String javaSource = rmg.javaSource();
        assertThat( javaSource ).contains( "FMapper extends RecordMapper<F, String>" );
//        fail( "method testJavaSource reached end. You know what to do." );
    }
    static String pathSep = System.getProperty( "path.separator" );
    static String fileSep = System.getProperty( "file.separator" );

    //@Disabled("think TDD")
    @Test
    public void testCodeIsCompileable() throws IOException, InterruptedException {
        final Path tempDir = Files.createTempDirectory( "puk" );

        RecordMapperGenerator rmg = new RecordMapperGenerator( F.class );
        
        String mapperName = F.class.getName() + "Mapper";
        var mapperPath = mapperName.replace( ".", fileSep ) + ".java";
        var compilerDirs = new CompilerDirs( tempDir, new String[]{ "src", "main", "java" }, new String[]{ "target", "classes" } );
        Path sourcePath = compilerDirs.sourcePath( mapperPath );
        Files.createDirectories( sourcePath.getParent() );
        String javaSource = rmg.javaSource();
        Files.writeString( sourcePath, javaSource, Charset.forName( "UTF-8" ) );
        var exitCode = runCompiler( compilerDirs, sourcePath );
        assertThat( exitCode ).isEqualTo( 0 );
        // cleanup if test passes
        cleanupOnExit(tempDir);
    }

    private void cleanupOnExit(final Path tempDir) {
        Runnable r = () -> {
            try {
                Files.walk( tempDir )
                        .sorted( Comparator.reverseOrder() )
                        .map( Path::toFile )
                        .forEach( File::delete );
            } catch ( IOException ex ) {
                Logger.getLogger( RecordMapperGeneratorTest.class.getName() ).log( Level.SEVERE, null, ex );
            }
            
        };
        Runtime.getRuntime().addShutdownHook( new Thread(r));
    }

    private int runCompiler(CompilerDirs compilerDirs, Path sourcePath) throws InterruptedException, IOException {
        var compilerArgs = new String[]{
            "javac",
            "-classpath", "target/classes" + pathSep + System.getProperty( "java.class.path" ),
            "-Xlint:all",
            "-d", compilerDirs.classesPath().toString(),
            sourcePath.toString()
        };
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command( compilerArgs );
        processBuilder.command().forEach( c -> System.out.println( "[INFO] " + c ) );
        Process process = processBuilder.start();
        InputStream errorStream = process.getErrorStream();
        try ( BufferedReader reader
                = new BufferedReader( new InputStreamReader( process
                        .getInputStream() ) ); ) {
            String line;
            while ( ( line = reader.readLine() ) != null ) {
                System.out.println( "[INFO] stdout " + line );
            }
        }
        try ( BufferedReader reader
                = new BufferedReader( new InputStreamReader( errorStream ) ); ) {
            String line;
            while ( ( line = reader.readLine() ) != null ) {
                System.out.println( "[INFO] stderr " + line );
            }
        }

        int exitCode = process.waitFor();
        return exitCode;

//        fail( "method testCodeIsCompileable reached end. You know what to do." );
    }

    record CompilerDirs(Path tempDir, String[] sourceDir, String[] targetDir) {

        Path sourcePath(String javaFile) {
            var p = Arrays.copyOf( sourceDir, sourceDir.length + 1 );
            p[ p.length - 1 ] = javaFile;
            return Path.of( tempDir.toString(), p );
        }

        Path classesPath() {
            return Path.of( tempDir.toString(), targetDir );
        }
    }
}
