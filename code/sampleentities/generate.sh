#!/bin/bash
CLASSPATH=${HOME}/.m2/repository/io/github/homberghp/recordmappers/1.0/recordmappers-1.0-jar-with-dependencies.jar

java -Dmapper.generator.outDir=src/main/java -Dmapper.generator.classesDir=target/classes -cp ${CLASSPATH} io.github.homberghp.recordmappers.MapperGeneratorRunner sampleentities
