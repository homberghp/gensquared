package client;

import entities.Employee;
import io.github.homberghp.recordmappers.RecordMapper;
import io.github.homberghp.recordmappers.RecordMapper.EditHelper;
import static java.time.LocalDate.now;
import java.util.List;
import java.util.Set;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assumptions.assumeThat;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static usertypes.Email.email;

/**
 *
 * @author Pieter van den Hombergh {@code Pieter.van.den.Hombergh@gmail.com}
 */
//@Disabled
public class EmployeeMapperTest {

    @BeforeAll
    static void setupClass() throws ClassNotFoundException {
        Class.forName( "entities.EmployeeMapper" );
    }

    @Test
    public void tMapper() {
        var em = RecordMapper.mapperFor( Employee.class );
        assumeThat( em ).isNotNull();
        List<EditHelper> entityFields = em.editHelpers();
        assertThat( entityFields ).hasSize( 8 );
        Object[] parts = new Object[]{
            1, "Puk", "Piet", email( "piet@student.fontys.nl" ), 42, true, now(), now()
        };
        Employee construct = em.construct( parts );
        assertThat( em.construct( parts ) ).isNotNull();
        assertThat( construct.email() ).isNotNull();
    }

    //@Disabled("think TDD")
    @Test
    public void testGeneratedFieldNames() {
        var em = RecordMapper.mapperFor( Employee.class );
        Set<String> generatedFieldNames = em.generatedFieldNames();
        assertThat(generatedFieldNames).containsExactlyInAnyOrder( "employeeid","hireDate");
        fail( "method testGeneratedFieldNames reached end. You know what to do." );
    }
}
