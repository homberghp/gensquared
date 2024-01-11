package entities;

import io.github.homberghp.gensquared_annotations.ID;
import io.github.homberghp.gensquared_annotations.TableName;
import java.io.Serializable;

/**
 * Entity with non numeric primary key.
 *
 * @author Pieter van den Hombergh {@code pieter.van.den.hombergh@gmail.com}
 */
@TableName("companies")
public record Company( String name, String country, String city,
            String address, String ticker, String postcode, Integer i, Integer j ){
    public Company( String name, String country, String city,
            String address, String ticker, String postcode ) {
        this( name, country, city, address, ticker, postcode, 0, null );
    }
}
