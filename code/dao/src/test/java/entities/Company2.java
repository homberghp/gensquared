package entities;


/**
 * Entity with non numeric primary key.
 *
 * @author Pieter van den Hombergh {@code pieter.van.den.hombergh@gmail.com}
 */
 public record Company2( String name, String country, String city,
            String address, String ticker, String postcode, int i, Integer j ) { 
    public Company2( String name, String country, String city,
            String address, String ticker, String postcode ) {
        this( name, country, city, address, ticker, postcode, 0, null );
    }

}
