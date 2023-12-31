package testentities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author Pieter van den Hombergh {@code Pieter.van.den.Hombergh@gmail.com}
 */
public record Person(String lastname, String tussenvoegsel, String firstname,
        LocalDate dob, String gender) implements Serializable {

}
