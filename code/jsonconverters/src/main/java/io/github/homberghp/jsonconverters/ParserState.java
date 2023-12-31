package io.github.homberghp.jsonconverters;

import java.util.function.ObjIntConsumer;

/**
 *
 * @author Pieter van den Hombergh {@code Pieter.van.den.Hombergh@gmail.com}
 */
enum ParserState implements ObjIntConsumer<ParserContext> {
    START {
        @Override
        public void accept(ParserContext t, int value) {
//             throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
        }
    },
    OPENBRACE {
        @Override
        public void accept(ParserContext t, int c) {
            if ( c == '{' ) {
                t.setState( QUOTE1 );
            }
        }
    }, QUOTE1 {
        @Override
        public void accept(ParserContext t, int c) {
            if ( c == '"' ) {
                t.setState( COLLECT_KEY );
            }
        }
    }, COLLECT_KEY {
        @Override
        public void accept(ParserContext t, int c) {
            if ( c == '"' ) {
                t.setState( COLON );
            } else {
                t.addToKey( c );
            }
        }
    }, COLON {
        @Override
        public void accept(ParserContext t, int c) {
            if ( c == ':' ) {
                t.setState( QUOTE3 );
            }
        }
    }, QUOTE3 {
        @Override
        public void accept(ParserContext t, int c) {
            if ( c == '"' ) {
                t.setState( COLLECT_VALUE_QUOTED );
                t.setQuoted( true );
            } else if ( Character.isLetterOrDigit( c ) ) {
                t.addToValue( c );
                t.setState( COLLECT_VALUE );
            }
        }
    }, COLLECT_VALUE_QUOTED {
        @Override
        public void accept(ParserContext t, int c) {
            if ( c == '"' ) {
                t.addPair();
                t.setState( COMMA );
            } else {
                t.addToValue( c );
            }
        }
    }, COLLECT_VALUE {
        @Override
        public void accept(ParserContext t, int c) {
            if ( !Character.isLetterOrDigit( c ) ) {
                t.addPair();
                switch ( c ) {
                    case ',':
                        t.setState( QUOTE1 );
                        break;
                    case '}':
                        t.setState( CLOSE_BRACE );
                        break;
                    default:
                        t.setState( COMMA );
                        break;
                }
            } else {
                t.addToValue( c );
            }
        }
    }, COMMA {
        @Override
        public void accept(ParserContext t, int c) {
            if ( c == ',' ) {
                t.setState( QUOTE1 );
            } else if ( c == '}' ) {
                t.setState( CLOSE_BRACE );
            }
        }
    }, CLOSE_BRACE {
        @Override
        public void accept(ParserContext t, int c) {
            // ignore input
        }
    }

}
