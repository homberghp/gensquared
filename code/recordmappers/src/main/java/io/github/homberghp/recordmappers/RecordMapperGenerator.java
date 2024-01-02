package io.github.homberghp.recordmappers;

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
import io.github.homberghp.gensquared_annotations.ID;
import java.lang.reflect.Field;
import java.lang.reflect.RecordComponent;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;
import static java.util.Map.entry;
import java.util.Optional;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.joining;
import java.util.stream.IntStream;

/**
 *
 * @author Pieter van den Hombergh {@code <pieter.van.den.hombergh@gmail.com>}
 */
public class RecordMapperGenerator {

    static String template = """
                           /*
                            * The code in this class is generated on %10$s. 
                            * Do not edit, your changes will be lost on the next build of your project.
                            */
                           package %1$s;
                           
                           %2$s;
                           import java.util.function.Function;
                           import java.util.List;
                           import io.github.homberghp.recordmappers.RecordMapper;
                           import static io.github.homberghp.recordmappers.RecordMapper.EditHelper;
                             
                           /**
                            * Maps %3$s class.
                            * 
                            */
                           public class %4$s extends RecordMapper<%3$s, %5$s> {
                           
                               private %4$s() {
                                   super(%3$s.class);
                               }
                           
                               static {
                                   RecordMapper.register( new %4$s() );
                               }
                           
                               @Override
                               public %3$s construct(Object[] fieldValues) {
                                   return new %3$s( %6$s );
                               }
                           
                               @Override
                               public Object[] deconstruct(%3$s %8$s) {
                                   return new Object[]{ %7$s };
                               }
                           
                               @Override
                               public Function<? super %3$s, ? extends %5$s> keyExtractor() {
                                   return %8$s -> %8$s.%9$s();
                               }
                           
                               @Override
                               public Class<?> keyType() {
                                   return %5$s.class;
                               }
                           
                               @Override
                               public String keyName() {
                                   return "%9$s";
                               }
                             
                               private static List<EditHelper> editHelpers = List.of(
                                    %11$s
                                 );
                             
                               public List<EditHelper> editHelpers(){
                                    return editHelpers;
                               }
                             
                           }
                           """;

//    public static void main(String[] args) {
//        System.out.println( new RecordMapperGenerator( puk.F.class ).javaSource() );
//    }
    private final Class<? extends Record> rklass;
    private final Field[] declaredFields;
    private final String instanceName;

    public RecordMapperGenerator(Class<? extends Record> rklass) {
        this.rklass = rklass;
        declaredFields = rklass.getDeclaredFields();
        instanceName = rklass.getSimpleName().substring( 0, 1 ).toLowerCase();
    }

    public final String javaSource() {
        String packageName = rklass.getPackageName();
        String importStatement = "import " + rklass.getCanonicalName();
        String simpleClassName = rklass.getSimpleName();
        Field keyComponent = keyComponent();
        String keyType = simplestClassName( RecordMapper.getCasterFor( keyComponent.getType() ) );
        String keyFieldName = keyComponent.getName();
        String ctorParamsWithCast = ctorParamsWithCast();
        String deconstructorArrayElements = deconstructorArrayElements();
        String mapperName = getMapperSimpleName();
        return template.formatted(
                packageName, // 1 package
                importStatement, //2 import
                simpleClassName, // 3 type name
                mapperName, // 4 mappername
                keyType, // 5 key type
                ctorParamsWithCast, // 6 record components and casts
                deconstructorArrayElements, // 7 array elements
                instanceName, // 8 instance name
                keyFieldName, // 9 name of key
                LocalDateTime.now(), // 10 timestamp
                editHelpers() // 11 edithelpers
        );
    }

    public Field keyComponent() {
        return Arrays.stream( declaredFields )
                .filter( r -> r.isAnnotationPresent( ID.class ) )
                .findFirst()
                .or( this::getFieldNamedId )
                .orElse( declaredFields[ 0 ] ); // default to first field to prevent exceptions.
        //field for record " + rklass.getName() ) );
    }

    private String ctorParamsWithCast() {
        return IntStream.range( 0, declaredFields.length )
                .mapToObj( i -> castExpression( i ) )
                .collect( Collectors.joining( ",\n                      " ) );
    }

    private String castExpression(int i) {
        Class<?> castType = RecordMapper.getCasterFor( declaredFields[ i ].getType() );
        String clzName = simplestClassName( castType );
        return clzName + ".class.cast( fieldValues[ " + i + " ] )";
    }

    String simplestClassName(Class<?> klass) {
        return klass.getPackageName().equals( "java.lang" ) ? klass.getSimpleName() : klass.getCanonicalName();
    }

    private String deconstructorArrayElements() {
        return Arrays.stream( declaredFields )
                .map( rc -> instanceName + "." + rc.getName() + "()" )
                .collect( Collectors.joining( ",\n                             " ) );
    }

    Optional<Field> getFieldNamedId() {
        return Arrays.stream( declaredFields )
                //                .peek( System.out::println )
                .filter( r -> r.getName().toLowerCase().endsWith( "id" ) )
                .findFirst();
    }
    /**
     * To ensure proper mapping of primitive types to their corresponding
     * wrapper types.
     */
    private static final Map<Class<?>, Class<?>> CASTERMAP = Map.ofEntries(
            entry( boolean.class, Boolean.class ),
            entry( byte.class, Byte.class ),
            entry( char.class, Character.class ),
            entry( short.class, Short.class ),
            entry( int.class, Integer.class ),
            entry( long.class, Long.class ),
            entry( float.class, Float.class ),
            entry( double.class, Double.class )
    );

    String editHelpers() {
        RecordComponent[] recordComponents = rklass.getRecordComponents();
        return Arrays.stream( recordComponents )
                .map( rc -> """
                            new EditHelper( "%1$s", %2$s )"""
                .formatted( rc.getName(),
                        simplestClassName( CASTERMAP.getOrDefault( rc.getType(), rc.getType() ) ) + ".class" ) )
                .collect( joining( ",\n         " ) );
    }

    public final String getMapperTypeName() {
        return rklass.getTypeName() + "Mapper";
    }

    public final String getMapperSimpleName() {
        String[] split = getMapperTypeName().split( "\\.");
        return split[split.length-1];
    }

    public final String getMapperCanonicalName() {
        return rklass.getCanonicalName() + "Mapper";
    }
}
