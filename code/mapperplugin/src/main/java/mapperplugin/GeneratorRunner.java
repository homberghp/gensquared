package mapperplugin;

import io.github.homberghp.recordmappers.MapperGeneratorRunner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Generates mappers for named Types. The types are fully qualified types to be
 * read from the class path.
 *
 * @author Pieter van den Hombergh {@code Pieter.van.den.Hombergh@gmail.com}
 */
public class GeneratorRunner {

    final String classesDir;
    final List<String> packNames;
    final String outDir;
    final String jars;

    public GeneratorRunner(String classesDir, String outDir, List<String> packNames, String jars) {
        this.classesDir = classesDir;
        this.outDir = outDir;
        this.packNames = packNames;
        this.jars = jars;
    }


    void run() {

        Properties props = System.getProperties();
        props.put( "mapper.generator.outDir", outDir );
        props.put( "mapper.generator.classesDir", classesDir );
        String[] packageNames = packNames.toArray( String[]::new );
        MapperGeneratorRunner.main( packageNames );
    }

    private String[] joinArrays(String[] commandParams, String[] packageNames) {
        String[] result = Arrays.copyOf( commandParams,
                commandParams.length + packageNames.length );
        System.arraycopy( packageNames, 0, result, commandParams.length,
                packageNames.length );
        return result;

    }

}
