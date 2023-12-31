package io.github.homberghp.jsonconverters;

import entities.Student;
import java.util.Optional;
import static io.github.homberghp.jsonconverters.TestData.jan;
import io.github.homberghp.recordmappers.RecordMapper;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.*;

/**
 *
 * @author "Pieter van den Hombergh {@code p.vandenhombergh@fontys.nl}"
 */
public class JSonParserTest {

    Class<Student> clz = Student.class;

//    @Disabled("Think TDD")
    @Test
    public void parse() {
        JsonParser<Student> m = JsonParser.forType( Student.class );

        String jason = """ 
                       {
                         "snummer":123,
                         "lastname":"Klaassen",
                         "firstname" : "Jan",
                         "dob":"2001-10-07", 
                         "cohort":2018, 
                         "email":"jan@home.nl",
                         "gender":"M",
                         "student_class":"INF-ABC",
                         "active":true 
                       }
                       """;
        Optional<Student> jantje = m.parse( jason );
        assertThat( jantje ).isNotEmpty();//.get().isEqualTo( jan );
        Student get = jantje.get();
        assertThat( get ).isEqualTo( jan );
//        fail( "method parse reached end. You know what to do." );
    }

//    @Disabled("Think TDD")
    @Test
    public void parse2() {
        String jason = """ 
                       {
                         "snummer":123,
                         "lastname":"Klaassen",
                         "firstname" : "Jan",
                         "dob":"2001-10-07", 
                         "cohort":2018, 
                         "email":"jan@home.nl",
                         "gender":"M",
                         "student_class":"INF-ABC",
                         "active":true 
                       }
                       """;
        JsonParser<Student> m = JsonParser.forType( Student.class );
        Optional<Student> jantje = m.parse( jason );
        assertThat( jantje ).isNotEmpty();//.get().isEqualTo( jan );
        Student getJan = jantje.get();
        assertThat( getJan )//.usingRecursiveComparison().ignoringAllOverriddenEquals()
                .isEqualTo( jan );
        System.out.println( "get = " + getJan );

//        fail( "method parse2 reached end. You know what to do." );
    }
}
