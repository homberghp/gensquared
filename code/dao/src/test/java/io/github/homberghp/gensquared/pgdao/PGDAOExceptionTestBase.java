package io.github.homberghp.gensquared.pgdao;

import entities.Employee;
import io.github.homberghp.gensquared.dao.DAO;
import io.github.homberghp.gensquared.dao.DAOException;
import io.github.homberghp.gensquared.dao.TransactionToken;
import io.github.homberghp.recordmappers.RecordMapper;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import static org.assertj.core.api.Assertions.*;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import usertypes.Email;

/**
 *
 * @author Pieter van den Hombergh {@code pieter.van.den.hombergh@gmail.com}
 */
abstract class PGDAOExceptionTestBase extends DBTestHelpers {

    Employee gp = new Employee( 1,null,null,null,null,null,null,null );
    @Mock
    DataSource ds;

    DAO<Employee, Integer> eDao;
    RecordMapper<Employee, Integer> mapper;
    private Connection connection;

    abstract Connection getConnection();

    @BeforeEach
    void setup() throws SQLException {
        MockitoAnnotations.openMocks( this );
        this.connection = getConnection();
        Mockito.when( ds.getConnection() ).thenReturn( this.connection );
        mapper = RecordMapper.mapperFor( Employee.class );
        daof = new PGDAOFactory( ds );
        daof.registerPGMashallers( Email.class, Email::new, x -> PGDAOFactory
                .pgobject( "citext", x ) );
        eDao = daof.createDao( Employee.class );
    }

    @AfterEach
    void cleanup() {
        try {
            this.connection.close();
        } catch ( SQLException zipit ) {
            Logger.getLogger( PGDAOExceptionTestBase.class.getName() ).
                    log( Level.SEVERE, zipit.getMessage() );
        }
    }

    @Test
    void delete() {
        ThrowingCallable suspectCode = () -> {
            eDao.deleteEntity( gp );
        };
        assertThatThrownBy( suspectCode ).isInstanceOf( DAOException.class );
    }

    @Test
    void get() throws SQLException {
        assertThatThrownBy( () -> {
            eDao.get( 1 );
        } ).isInstanceOf( DAOException.class );

    }

    @Test
    void getAll() {
        assertThatThrownBy( () -> {
            eDao.getAll();
        } ).isInstanceOf( DAOException.class );

    }

    @Test
    void getByColumnValues() {
        assertThatThrownBy( () -> {
            eDao.getByColumnValues( "name", "nothing" );
        } ).isInstanceOf( DAOException.class );

    }

    @Test
    void intQuery() {
        assertThatThrownBy( () -> {
            ( (PGDAO) eDao ).executeIntQuery( "select count(1) from employees" );
        } ).isInstanceOf( DAOException.class );
//        fail( "test method testIntQuery reached its end, you can remove this line when you aggree." );
    }

    @Test
    void lastId() {
        assertThatThrownBy( () -> {
            eDao.lastId();
        } ).isInstanceOf( DAOException.class );
    }

    @Test
    void save() throws Exception {
        assertThatThrownBy( () -> {
            TransactionToken tok = eDao.getTransactionToken();
            if ( null != tok ) {
                tok.close();
            }
            eDao.save( gp );
        } ).isInstanceOf( DAOException.class );
    }

    @Test
    void setTransactionToken() {
        assertThatCode( () -> {
            eDao.setTransactionToken( null );
        } ).doesNotThrowAnyException();
    }

    @Test
    void size() {
        assertThatThrownBy( () -> {
            eDao.size();
        } ).isInstanceOf( DAOException.class );
    }

    @Test
    void update() {
        assertThatThrownBy( () -> {
            eDao.update( gp );
        } ).isInstanceOf( DAOException.class );
    }

    @Test
    void executeIntQueryInt1() {
        assertThatThrownBy( () -> {
            ( (PGDAO) eDao ).executeIntQuery(
                    "select departmentid from employees where employeeid=?", 1 );
        } ).isInstanceOf( DAOException.class );

        // fail( "testExecuteIntQueryInt1 not yet implemented. Review the code and comment or delete this line" );
    }

}
