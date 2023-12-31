package io.github.homberghp.recordmappers;

/*
 * Copyright 2023 Pieter van den Hombergh @code{<pieter.van.den.hombergh@gmail.com>}.
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
import java.util.List;
import java.util.Map;
import static java.util.Map.entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pieter van den Hombergh {@code<pieter.van.den.hombergh@gmail.com>}
 * @param <R> record type
 * @param <K> the identifying field type
 */
public abstract class RecordMapper<R extends Record, K> implements RecordEdit<R, K> {

    private final Class<? extends Record> rklass;

    public RecordMapper(Class<? extends Record> rklass) {
        this.rklass = rklass;
    }

    /**
     *
     * @return the type (class) of the mapped entity
     */
    public final Class<? extends Record> entityType() {
        return rklass;
    }

    /**
     * Deconstruct a record into an array of its components
     *
     * @param r to deconstruct
     * @return the array
     */
    public abstract Object[] deconstruct(R r);

    /**
     * Construct a record from the given array of components. The implementation
     * should type check the array elements against the component types of the
     * record.
     *
     * @param params to use
     * @return a new record.
     */
    public abstract R construct(Object[] params);

    /**
     * An entity typically has some kind of identity. Primary key in a database,
     * key in a HashMap.
     *
     * The function should produce a the key given an entity. You use it like
     * this: {@code K = mapper.keyExtractor(entity);}
     *
     * @return the extractoE function.
     */
    public abstract Function<? super R, ? extends K> keyExtractor();

    /**
     * Return the key defined foE the entity.
     *
     * @return class of entity
     */
    public abstract Class<?> keyType();

    /**
     * The name of the (primary) key field.
     *
     * @return the name of the key field
     */
    public abstract String keyName();

    public record EditHelper(String fieldName, Class<?> guard) {

    }

    public abstract List<EditHelper> editHelpers();

    /**
     * The size of the record to (de)construct a record of type {@code <R>}.
     * @return the size
     */
    public int recordArraySize() {
        return editHelpers().size();
    }

    /**
     * 'Edit' means returning a new record with changes applied.
     *
     * @param orginal input
     * @param changes to apply
     * @return a new record
     */
    public final R edit(R orginal, Map<String, Object> changes) {
        Object[] params = deconstruct( orginal );
        return construct( changes, params );
    }

    /**
     * Helper.
     *
     * @param params to the construction
     * @param receiver array that collects the input to the constructor.
     * @return a new record
     */
    private R construct(Map<String, Object> params, Object[] receiver) {
        var helpers = editHelpers();
        for ( int i = 0; i < helpers.size(); i++ ) {
            EditHelper helper = helpers.get( i );
            String fieldName = helper.fieldName();
            if ( params.containsKey( fieldName ) ) {
                receiver[ i ] = helper.guard.cast( params.get( fieldName ) );
            }
        }
        return construct( receiver );
    }

    /**
     * Construct a new record from the information in the params map.
     *
     * @param params input
     * @return a new record.
     */
    public final R construct(Map<String, Object> params) {
        return construct( params, new Object[ editHelpers().size() ] );
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

    /**
     * Get a type to cast a field in the component array of the construct
     * method. Returns the given clz unless it is a primitive type, then the
     * wrapper type is used.
     *
     * @param clz in
     * @return the type to do the casting.
     */
    static Class<?> getCasterFor(Class<?> clz) {
        return CASTERMAP.getOrDefault( clz, clz );
    }

    private static final Logger logger = Logger.getLogger( RecordMapper.class
            .getName() );

    /**
     * Retrieve a mapper for the given type. The mapper is either loaded by the
     * class loader or returned from a cache after the first load.
     *
     * @param <XR> generic entity type
     * @param <Y> generic key field type of the entity
     * @param et type of (class) entity
     *
     * @return the mapper.
     */
    @SuppressWarnings( "unchecked" )
    public static <XR extends Record, Y> RecordMapper<XR, Y> mapperFor(
            Class<XR> et) {
        return MapperRegistry.mapperFor( et );
    }

    private static String mapperName(Class<? extends Record> forEntity) {
        return forEntity.getCanonicalName() + "Mapper";
    }

    private static class MapperRegistry {

        private MapperRegistry() {
        } // no values
        private static final ConcurrentMap<Class<? extends Record>, RecordMapper<?, ?>> register = new ConcurrentHashMap<>();

        /**
         * Retrieve a mapper for the given type. The mapper is either loaded by
         * the class loader or returned from a cache after the first load.
         *
         * @param <Y> generic entity type
         * @param <KY> generic key field type of the entity
         * @param <YM> Mapper for Y
         * @param et type of (class) entity
         *
         * @return the mapper.
         */
        @SuppressWarnings( "unchecked" )
        public static <Y extends Record, KY extends Object, YM extends RecordMapper< Y, KY>> YM mapperFor(
                Class<Y> et) {
            if ( !register.containsKey( et ) ) {
                loadMapperClass( et );
            }
            return (YM) register.get( et );
        }

        static <SR extends Record, SRM extends RecordMapper<SR, ?>> void register(SRM em) {
            logger.log( Level.INFO,
                    "registering mapper {0} for type {1}",
                    new Object[]{ em.getClass().getCanonicalName(), em.entityType().getCanonicalName() } );
            register.putIfAbsent( em.entityType(), em );
        }

        private static final Logger logger = Logger.getLogger( RecordMapper.class
                .getName() );

        /**
         * Try to load a mapper for an entity by name. If the type ==
         * String.class, do nothing, because String is special.
         *
         * @param <E> generic type of entity
         * @param forEntity class
         *
         * @throws a RuntimeException when the requested mapper class cannot be
         * loaded
         */
        static <E> void loadMapperClass(Class<? extends Record> forEntity) {

            String mapperName = mapperName( forEntity );
            try {
                Class.forName( mapperName, true, forEntity.getClassLoader() );
                logger.log( Level.INFO,
                        "mapper {0} for class {1} successfully loaded",
                        new Object[]{ mapperName, forEntity.getSimpleName() } );

            } catch ( ClassNotFoundException ex ) {
                logger.log( Level.SEVERE,
                        "could not find mapper {0} for class {1}",
                        new String[]{
                            mapperName,
                            forEntity
                                    .getSimpleName() } );
                throw new RuntimeException( ex );
            }
        }
    }

    /**
     * Register a mapper.
     *
     * @param <EM> type of mapper
     * @param em mapper to register.
     */
    public static <EM extends RecordMapper> void register(EM em) {
        MapperRegistry.register( em );
    }

}
