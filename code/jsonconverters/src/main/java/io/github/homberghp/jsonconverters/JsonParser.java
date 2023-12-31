package io.github.homberghp.jsonconverters;

import java.util.Optional;

/**
 *
 * @author "Pieter van den Hombergh {@code p.vandenhombergh@fontys.nl}"
 */
public class JsonParser<E extends Record> {

   
    final Class<E> entityType;

    private JsonParser( Class<E> entityType ) {
        this.entityType = entityType;
    }

    public static <X extends Record>  JsonParser<X> forType( Class<X> clz ) {
        return new JsonParser( clz );
    }

    public Optional<E> parse( String s ) {
        return new Parser<>( s, entityType ).parse();
    }

}
