package sampleentities;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Simple student with LocalDate birthday.
 *
 * @author Pieter van den Hombergh {@code pieter.van.den.hombergh@gmail.com}
 */
public record Student(Integer snummer, String lastname, String tussenvoegsel,
        String firstname, LocalDate dob, int cohort, String email,
        String gender, String student_class, Boolean active) implements Serializable {

}
