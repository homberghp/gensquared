/*
 * Copyright 2024 Pieter van den Hombergh {@code <pieter.van.den.hombergh@gmail.com>}.
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
package mapperplugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.util.stream.Collectors.joining;
import org.apache.maven.model.Dependency;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;

/**
 *
 * @author Pieter van den Hombergh {@code <pieter.van.den.hombergh@gmail.com>}
 *
 */
@Mojo( name = "recordmappergenerator",
        defaultPhase = LifecyclePhase.GENERATE_SOURCES,
        requiresDependencyResolution = ResolutionScope.COMPILE,
        requiresProject = true,
        threadSafe = false )
public class RecordMapperGenerator extends AbstractMojo {

    @Parameter( property = "mapper.entities.packages", defaultValue = "false" )
    protected List<String> entityPackages;

    private final Set<String> entityDirTails = new HashSet<>();

    /**
     * compile extra packages that or not entity packages, e.g. to compile user
     * types.
     */
    @Parameter( property = "mapper.generator.destinationDir", defaultValue = "false" )
    protected String destinationDir;

    @Parameter( property = "mapper.generator.classesDir", defaultValue = "false" )
    protected String classesDir;

    @Parameter( defaultValue = "${project}", required = true, readonly = true )
    MavenProject project;

    @Parameter( property = "mapper.scope", defaultValue = "jar" )
    String scope;

    static String pathSep = System.getProperty( "path.separator" );
    static String fileSep = System.getProperty( "file.separator" );

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {

//        List.of("java", "-cp", classPath)
        List<Dependency> dependencies = project.getDependencies();
        String classPath = getDependencyPath( scope ) + pathSep + System.getProperty( "java.class.path" );
        String modulePath = getDependencyPath( scope ) + pathSep + System.getProperty( "java.class.path" );
        List<String> args = new ArrayList<>( List.of( "java",
                "-Dmapper.generator.destinationDir=" + destinationDir,
                "-Dmapper.generator.classesDir=" + classesDir,
                "-Dmapper.scope=" + scope,
                "-cp", classPath, "-p", modulePath,
                "--module", "io.github.homberghp.recordmappers/io.github.homberghp.recordmappers.MapperGeneratorRunner"
        ) );
        args.addAll( entityPackages );
        args.forEach( c -> System.out.println( "[Args INFO] " + c ) );
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command( args.toArray( String[]::new ) );
//        processBuilder.command().forEach( c -> System.out.println( "[Compiler INFO] " + c ) );
        try {
            Process process = processBuilder.start();

            BufferedReader reader
                    = new BufferedReader( new InputStreamReader( process
                            .getInputStream() ) );
            String line;
            while ( ( line = reader.readLine() ) != null ) {
                System.out.println( line );
            }
            process.waitFor();
        } catch ( InterruptedException ignored ) {
            System.out.println( "timed out" );
        } catch ( IOException ex ) {
            Logger.getLogger( RecordMapperGenerator.class.getName() ).log( Level.SEVERE, null, ex );
        }
    }

    String getDependencyPath(String scope) {
        List<Dependency> dependencies
                = switch ( scope ) {
            case "test" ->
                project.getTestDependencies();
            default ->
                project.getDependencies();
        };
        final String repo = System.getProperty( "user.home" )
                + fileSep + ".m2" + fileSep + "repository";
//        String pVersion = project.getVersion();
//        String pGroupId = project.getGroupId().replace( ".", fileSep );
//        String pArtifactId = project.getArtifactId().replace( ".", fileSep );
        var result = dependencies.stream()
//                .filter( dep -> dep.getGroupId().contains( "homberghp" ) )
                .map( ( Dependency d ) -> {
                    String gid = d.getGroupId().replace( ".", fileSep );
                    String artifactId = d.getArtifactId().replace( ".", fileSep );
                    String version = d.getVersion();
                    var jar = artifactId + "-" + version + ".jar";
                    return String.join( fileSep, repo, gid,
                            artifactId, version, jar );

                } )
                .collect( joining( pathSep ) );
        return result;
//        return String.join( fileSep, pGroupId, pArtifactId, pVersion ) + result;
    }
}
