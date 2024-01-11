package io.github.homberghp.gensquared.pgdao;


import entities.Employee;
import io.github.homberghp.gensquared.pgdao.PGDAO;
import io.github.homberghp.gensquared.pgdao.PGDAOFactory;
import io.github.homberghp.gensquared.pgdao.PGJDBCUtils;
import javax.sql.DataSource;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assumptions.assumeThat;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import usertypes.Email;

/**
 *
 * @author Pieter van den Hombergh {@code pieter.van.den.hombergh@gmail.com}
 */
public class PGDAOTestBase extends DBTestHelpers {

    PGDAO<Employee, Integer> eDao;

    @BeforeAll
    public static void setupHelpers() throws ClassNotFoundException {
        DataSource ds = PGJDBCUtils.getDataSource( "simpledao" );
        assumeThat( ds ).isNotNull();

        DBTestHelpers.setupClass();
        Class.forName( "entities.CompanyMapper" );
        Class.forName( "entities.DepartmentMapper" );
        Class.forName( "entities.EmployeeMapper" );
        Class.forName( "entities.TruckMapper" );
    }

    @BeforeEach
    void setUp() throws Throwable {
        assertThat( daof ).isNotNull();
        daof.registerInMarshaller( Email.class, Email::new );
        daof.registerOutMarshaller( Email.class, x-> PGDAOFactory.pgobject("citext", x ));

        daof.registerPGMashallers( Email.class, Email::new, x -> PGDAOFactory
                .pgobject( "citext", x ) );
        eDao = daof.createDao( Employee.class );
        assumeThat( eDao ).isNotNull();
        insertPiet();
    }

}
