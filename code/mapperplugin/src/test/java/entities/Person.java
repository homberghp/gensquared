package entities;

import io.github.homberghp.gensquared_annotations.ID;
import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author Pieter van den Hombergh {@code Pieter.van.den.Hombergh@gmail.com}
 */
public record Person( @ID String lastname, String tussenvoegsel, String firstname,
            LocalDate dob, String gender )  implements Serializable {}