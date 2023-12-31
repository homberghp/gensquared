package sampleentities;

import io.github.homberghp.gensquared_annotations.ID;
import java.time.LocalDate;

/**
 *
 * @author Pieter van den Hombergh {@code p.vandehombergh@gmail.com}
 */
public record Tutor(@ID
        String abreviation, String firstname, String lastname, String tussenvoegsel,
        String academicTitle, LocalDate dob, String gender,
        String teaches) {

}
