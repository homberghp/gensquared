#!/bin/bash
mvn -DskipTests clean package
java -cp target/recordmappers-1.0-jar-with-dependencies.jar \
     -Dmapper.generator.classesDir=target/classes \
    -Dmapper.generator.outDir=src/main/java/ \
     io.github.homberghp.recordmappers.MapperGeneratorRunner entities
