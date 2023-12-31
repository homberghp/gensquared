package io.github.homberghp.jsonconverters;

import entities.Student;
import java.time.LocalDate;
import static java.time.LocalDate.of;
import java.util.Arrays;

/**
 *
 * @author "Pieter van den Hombergh {@code p.vandenhombergh@fontys.nl}"
 */
class TestData {

    final static Integer snummer = 123;
    final static String lastName = "Klaassen";
    final static String tussenVoegsel = null;
    final static String firstName = "Jan";
    final static LocalDate dob = of( 2001, 10, 07 );
    final static int cohort=2018;
    final static String email = "jan@home.nl";
    final static String gender = "M";
    final static String group = "INF-ABC";
    final static Boolean active = true;
    final static Object[] studentArgs = new Object[]{
        snummer, lastName, tussenVoegsel, firstName, dob, cohort, email, gender,
        group, active
    };

    static Student jan = new Student(
            snummer, lastName, tussenVoegsel, firstName, dob, cohort, email,
            gender, group, active
    );

    static {
        System.out.println( "jan = " + jan );
        System.out.println(
                "studentArgs = " + Arrays.deepToString( studentArgs ) );
    }
}
