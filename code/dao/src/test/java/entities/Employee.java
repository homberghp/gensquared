package entities;

import io.github.homberghp.gensquared_annotations.Generated;
import io.github.homberghp.gensquared_annotations.ID;
import usertypes.Email;
import java.time.LocalDate;

/**
 * Dutch naming. (fields all lower case.)
 *
 * @author Pieter van den Hombergh {@code pieter.van.den.hombergh@gmail.com}
 */
public record Employee(@ID
        Integer employeeid, String lastname, String firstname,
        Email email, Integer departmentid, Boolean available, LocalDate dob,
        @Generated
        LocalDate hiredate) {

}
