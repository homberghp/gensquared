package io.github.homberghp.jsonconverters;

import io.github.homberghp.recordmappers.RecordMapper;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import static java.util.Map.entry;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.IntStream;

/**
 *
 * @author Pieter van den Hombergh {@code Pieter.van.den.Hombergh@gmail.com}
 */
class EntityBuilder<Y extends Record> {

    final RecordMapper<Y, ?> mapper;
    final Object[] receiver;

    EntityBuilder(Class<Y> clz) {
        this.mapper = RecordMapper.mapperFor( clz );
        receiver = new Object[ mapper.recordArraySize() ];
    }

    final EntityBuilder<Y> addfieldValue(String fieldName, String value,
            boolean quotedValue) {
        int fieldNumber = indexOf( fieldName );
        if ( 0 <= fieldNumber && fieldNumber < mapper.recordArraySize() ) {
            Object object;
            if ( !quotedValue && "null".equals( value ) ) {
                object = null;
            } else {
                object = convertFromString( fieldNumber, value );
            }
            receiver[ fieldNumber ] = object;
        }
        return this;
    }

    Object convertFromString(int fieldNumber, String value) {
        Object object;
        Function<String, Object> orDefault
                = conversionMap.getOrDefault( mapper.editHelpers().get(
                        fieldNumber ).guard(),
                        x -> x );
        object = orDefault.apply( value );
        return object;
    }

    // candidate to move to mapper.
    private Map<String, Integer> positionMap;

    final int indexOf(String fieldName) {

        if ( positionMap == null ) {
            positionMap = Map.ofEntries( IntStream.range( 0, receiver.length )
                    .mapToObj( i -> Map.entry( mapper.editHelpers().get( i ).fieldName(), i ) )
                    .toArray( Map.Entry[]::new )
            );
        }
        return positionMap.getOrDefault( fieldName, -1 );
    }

    final Optional<Y> build() {
        return Optional.ofNullable( mapper.construct( receiver ) );
    }

    static final Map<Class<?>, Function<String, Object>> conversionMap
            = Map.ofEntries(
                    entry( String.class, x -> x ), // identity
                    entry( byte.class, Byte::parseByte ),
                    entry( short.class, Short::parseShort ),
                    entry( int.class, Integer::parseInt ),
                    entry( long.class, Long::parseLong ),
                    entry( float.class, Float::parseFloat ),
                    entry( double.class, Double::valueOf ),
                    entry( boolean.class, Boolean::parseBoolean ),
                    entry( Byte.class, Byte::valueOf ),
                    entry( Short.class, Short::valueOf ),
                    entry( Integer.class, Integer::valueOf ),
                    entry( Long.class, Long::valueOf ),
                    entry( Float.class, Float::valueOf ),
                    entry( Double.class, Double::valueOf ),
                    entry( Boolean.class, Boolean::valueOf ),
                    entry( LocalDate.class, LocalDate::parse ),
                    entry( LocalDateTime.class, LocalDateTime::parse )
            );
}
