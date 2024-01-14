package io.github.homberghp.recordmappers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.joining;
import java.util.stream.Stream;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

/**
 * Helper for mapper plugin. This class accepts the parameters of the plugin and
 * passes it to individual MapperGenarator instances.
 *
 * @author Pieter van den Hombergh {@code Pieter.van.den.Hombergh@gmail.com}
 */
public class MapperGeneratorRunner {
    
    public static void main(String[] args) throws IOException {
        String[] packNames;
        if ( args.length > 0 ) {
            packNames = args;
        } else {
            packNames = new String[]{ "entities" };
        }
        new MapperGeneratorRunner( packNames ).run();
    }
    
    private static String mapperTypeName(Class<?> type) {
        return type.getName() + "Mapper";
        
    }
    
    final String classesDir;
    private final String outDir;
    final String[] packNames;
    final String scope;
    
    public MapperGeneratorRunner(String[] packNames) {
        this.scope = System.getProperty( "mapper.scope", "jar" );
        String clDir = switch ( scope ) {
            case "jar" ->
                "target/classes";
            case "test" ->
                "target/test-classes";
            default ->
                "out";
        };
        this.classesDir = System.getProperty( "mapper.generator.classesDir", "target" + fileSep + "classes" );
        this.packNames = packNames;
        String destDir = switch ( scope ) {
            case "jar" ->
                "src/main/java";
            case "test" ->
                "src/test/java";
            default ->
                "out";
        };
        this.outDir = System.getProperty( "mapper.generator.destinationDir", destDir );
    }
    
    public void run() throws IOException {
        String systemClassPath = System.getProperty( "java.class.path" );
        String systemModulePath = System.getProperty( "jdk.module.path" );
        makeTargetDirs();
        runCompiler( systemClassPath, systemModulePath );
        try {
            for ( String packName : packNames ) {
                System.out.println( "generating for package " + packName );
                generateMappers( outDir, classesDir,
                        getCanditateEntityNames(
                                classesDir, packName ) );
            }
        } catch ( IOException | ClassNotFoundException ex ) {
            Logger.getLogger( RecordMapperGenerator.class.getName() )
                    .log( Level.SEVERE, null, ex );
        }
    }

    /**
     * Get the list of candidate entities from the compiled classes
     * directory.The method removes the following file name patterns from the
     * available files below the start directory.
     * <ul>
     * <li>any filename containing a dash, such as in doc-info.class or
     * module-info.class</li>
     * <li>Any class name ending in Mapper.class to avoid mapping the
     * mapper.</li>
     * </ul>
     *
     * @param startDir to start
     * @param entPackage package for entities
     * @return list of possible entity classes.
     *
     * @throws IOException dir
     */
    public Set<String> getCanditateEntityNames(String startDir,
            String entPackage)
            throws IOException {
        Path startPath = Path.of( startDir );
        Path root = Path.of(
                startDir + fileSep + entPackage.replace( ".", fileSep ) );
        if ( Files.exists( root ) ) {
            try ( Stream<Path> stream = Files.walk( root,
                    Integer.MAX_VALUE ) ) {
                return stream
                        .filter( file -> !Files.isDirectory( file ) )
                        .filter( f -> !fileNameContains( f, "-" ) ) // avoid info files
                        .filter( f -> !fileNameEndsWith( f, "Mapper.class" ) )
                        .filter( file -> fileNameEndsWith( file, ".class" ) )
                        .map( p -> startPath.relativize( p ) )
                        .map( Path::toString )
                        .map( s -> s.substring( 0,
                        s.length() - ".class".length() ) )
                        .map( s -> s.replace( fileSep, "." ) )
                        .peek( c -> System.out.println( "[INFO] possibly generating mapper for entitiy class '" + c + "'" ) )
                        .collect( Collectors.toSet() );
            }
        } else {
            System.out.println( "no classes found under root " + root );
            return Collections.emptySet();
        }
    }
    
    static boolean fileNameEndsWith(Path file, String end) {
        return file.getFileName().toString().endsWith( end );
    }
    
    static boolean fileNameContains(Path file, String needle) {
        return file.getFileName().toString().contains( needle );
    }
    
    static String pathSep = System.getProperty( "path.separator" );
    static String fileSep = System.getProperty( "file.separator" );
    
    public static String generatedJavaFileName(String outDir, Class<?> type) {
        String n = mapperTypeName( type ).replaceAll( "\\.", fileSep );
        var x = outDir + "/" + n + ".java";
        return x;
    }
    
    void generateMappers(String outDir, String classPathElement, Collection<String> entityNames) throws
            ClassNotFoundException, FileNotFoundException, MalformedURLException {
        URLClassLoader cl = new URLClassLoader( new URL[]{
            Path.of( classesDir ).toUri().toURL()
        } );
        System.out.println( "entityNames = " + entityNames );
        for ( String entityName : entityNames ) {
            System.out.println( "entityName = " + entityName );
            Class<?> clz = Class.forName( entityName, true, cl );
            if ( clz.isRecord() ) {
                var rklass = (Class<? extends Record>) clz;
                String fileName = generatedJavaFileName( outDir, rklass );
                File dir = new File( fileName );
                dir.getParentFile().mkdirs();
                String javaSource = new RecordMapperGenerator( rklass ).javaSource();
                if ( !javaSource.isBlank() ) {
                    try ( PrintStream out = new PrintStream( fileName ) ) {
                        out.print( javaSource );
                        out.flush();
                    }
                    System.out.println( "generated mapper " + mapperTypeName( clz )
                            + " for entity " + clz.getCanonicalName() );
                }
            } else {
                System.out.println( "clz " + clz + "is not a record" );
            }
        }
    }
    
    private int runCompiler(String classPath, String modulePath) throws IOException {
        System.out.println( "classPath = " + classPath );
        System.out.println( "modulePath = " + modulePath );
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        String pathPrefix = switch ( this.scope ) {
            case "jar" ->
                "src/main/java" + pathSep + "target/classes";
            case "test" ->
                "src/test/java" + pathSep + "target/test-classes" + pathSep + "src/main/java" + pathSep + "target/classes";
            default ->
                "";
        };
        classPath = pathPrefix + pathSep + classPath;
        modulePath = pathPrefix + pathSep + modulePath;
        var argsClassPath = List.of( "-p", modulePath, "-cp", classPath, "-d", this.classesDir );
        List<String> args = new ArrayList<>( argsClassPath );
        String srcDir = switch ( scope ) {
            case "test" ->
                "src/test/java";
            default ->
                "src/main/java";
        };
        args.addAll( getSourceFiles( srcDir ) );
//        System.out.println( "args = " + args.stream().map( e ->  "[COMPILER ARGS] " + e ).collect( joining("\n")) );
        return compiler.run( null, null, null, args.toArray( String[]::new ) );
    }
    
    List<String> getSourceFiles(String srcDir) {
        
        List<String> result = null;
        try ( Stream<Path> stream = Files.walk( Paths.get( srcDir ),
                Integer.MAX_VALUE ) ) {
            result = stream
                    .filter( path -> !Files.isDirectory( path ) )
                    .filter( path -> path.getFileName().toString().endsWith( ".java" ) )
                    .map( Path::toString )
                    .toList();
        } catch ( IOException ignored ) {
        }
//        System.out.println( "sourcefiles = " + Arrays.toString( result ) );
        return result;
    }
    
    void makeTargetDirs() throws IOException {
        List<String> elements
                = List.of( "target" + fileSep
                        + "classes", "target" + fileSep
                        + "test-classes" );
        for ( String element : elements ) {
            Path p = Path.of( element );
            Files.createDirectories( p );
        }
    }
}
