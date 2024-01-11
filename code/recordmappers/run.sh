#!/bin/bash
mvn -DskipTests clean package
java -cp target/recordmappers-1.0-jar-with-dependencies.jar \
     -Dmapper.generator.classesDir=target/test-classes \
    -Dmapper.generator.outDir=src/test/java/ \
     io.github.homberghp.recordmappers.MapperGeneratorRunner io.github.homberghp.recordmappers

