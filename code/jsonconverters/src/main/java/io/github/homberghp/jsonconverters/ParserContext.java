package io.github.homberghp.jsonconverters;

/**
 *
 * @author Pieter van den Hombergh {@code Pieter.van.den.Hombergh@gmail.com}
 */
public interface ParserContext {
    
        void setQuoted( boolean b );

        void setState( Object newState );

        void addPair();

        void addToKey( int c );

        void addToValue( int c );
}
