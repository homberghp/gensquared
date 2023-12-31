package io.github.homberghp.jsonconverters;

import java.util.Optional;

/**
 *
 * @author Pieter van den Hombergh {@code Pieter.van.den.Hombergh@gmail.com}
 */
class Parser<Z extends Record> implements ParserContext {

    final String input;
    final Class<Z> type;
    Z result;
    private final StringBuilder key = new StringBuilder();
    private final StringBuilder value = new StringBuilder();
    EntityBuilder<Z> builder;
    ParserState state = ParserState.OPENBRACE;
    boolean quotedValue = false;

    void clear( StringBuilder sb ) {
        sb.delete( 0, sb.length() );
    }

    Parser( String input, Class<Z> type ) {
        this.input = input;
        this.type = type;
        builder = new EntityBuilder<>( type );
    }

    @Override
    public void addPair() {
        //            System.out.println( "add this = " + key.toString()+", value="+value.toString() );
        builder.addfieldValue( key.toString(), value.toString(), quotedValue );
        key.delete( 0, key.length() );
        value.delete( 0, value.length() );
    }

    @Override
    public void setQuoted( boolean b ) {
        quotedValue = b;
    }

    @Override
    public void setState( Object newState ) {
        this.state = (ParserState) newState;
    }

    Optional<Z> parse() {
        input.chars()
                .forEach( c -> state.accept( this, c ) );
        return builder.build();
    }

    @Override
    public void addToKey( int c ) {
        key.append( (char) c );
    }

    @Override
    public void addToValue( int c ) {
        value.append( (char) c );
    }

}
