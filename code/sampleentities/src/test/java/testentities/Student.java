package testentities;

import io.github.homberghp.gensquared_annotations.ID;
import java.io.Serializable;
import java.time.LocalDate;

public record Student(String lastname, String tussenvoegsel,
        String firstname, LocalDate dob, int cohort, String email,
        String gender, String student_class,
        @ID
        Integer snummer, Boolean active) implements Serializable {

}
