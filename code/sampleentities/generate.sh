#!/bin/bash
#CLASSPATH=${HOME}/.m2/repository/io/github/homberghp/mapperplugin/1.0/mapperplugin-1.0-jar-with-dependencies.jar:/home/hom/.m2/repository/io/github/homberghp/gensquared_annotations/1.0/gensquared_annotations-1.0.jar
#CLASSPATH=${HOME}/.m2/repository/io/github/homberghp/mapperplugin/1.0/mapperplugin-1.0.jar:/home/hom/.m2/repository/io/github/homberghp/recordmappers/1.0/recordmappers-1.0.jar:/home/hom/.m2/repository/io/github/homberghp/gensquared_annotations/1.0/gensquared_annotations-1.0.jar
CLASSPATH=/home/hom/.m2/repository/io/github/homberghp/recordmappers/1.0/recordmappers-1.0.jar:/home/hom/.m2/repository/io/github/homberghp/gensquared_annotations/1.0/gensquared_annotations-1.0.jar

rm -fr target
java -Dmapper.generator.destinationDir=src/main/java \
     -Dmapper.generator.classesDir=target/classes \
     -Dmapper.sourcesDir=src/main/java \
     -p ${CLASSPATH} -cp ${CLASSPATH} \
     --module io.github.homberghp.recordmappers/io.github.homberghp.recordmappers.MapperGeneratorRunner sampleentities

# java -Dmapper.generator.outDir=src/test/java \
#      -Dmapper.generator.classesDir=target/test-classes \
#      -Dmapper.sourcesDir=src/test/java \
#      -p target/classes:${CLASSPATH} -cp target/classes:${CLASSPATH} \
#      --module io.github.homberghp.recordmappers/io.github.homberghp.recordmappers.MapperGeneratorRunner sampleentities

echo " ------------- generated StudentMapper: ------------  "
pygmentize -g src/main/java/sampleentities/StudentMapper.java
