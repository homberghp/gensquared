package io.github.homberghp.gensquared.pgdao;

import entities.Company;
import entities.Employee;
import io.github.homberghp.recordmappers.RecordMapper;
import io.github.homberghp.recordmappers.RecordMapper.EditHelper;
import java.lang.reflect.Field;
import java.util.function.Supplier;
import static java.util.stream.Collectors.joining;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

/**
 *
 * @author Pieter van den Hombergh {@code Pieter.van.den.Hombergh@gmail.com}
 */
public class QueryFactoryTest {

    RecordMapper<?, ?> mapper = RecordMapper.mapperFor( Employee.class );
    QueryFactory fac = new QueryFactory( mapper );

    //@Disabled("Think TDD")
    @Test
    void tSaveQueryText() {
        String queryText = cachedTest( () -> fac.saveQueryText() );
        //Start Solution::replacewith:://TODO
        String columnNames = columnNames( );
        String placeHolders = fac.makePlaceHolders( 6 );

        String[] expected = { "insert", "into", "employees", columnNames,
                              placeHolders };
        System.out.println( "saveQueryText = " + queryText );
        assertThat( queryText.toLowerCase() ).contains( expected );
        //End Solution::replacewith::fail( "tSaveQueryText completed succesfully; you know what to do" );
    }

    //@Disabled("Think TDD")
    @Test
    void tDeleteQueryText() {
        String queryText = cachedTest( () -> fac.deleteQueryText() );
        //Start Solution::replacewith:://TODO
        String[] expected = { "delete", "from", "employees", "where",
                              "employeeid", "=", "?" };
        System.out.println( "saveQueryText = " + queryText );
        assertThat( queryText.toLowerCase() ).contains( expected );
        //End Solution::replacewith::fail( "tDeleteQueryText completed succesfully; you know what to do" );
    }

    //@Disabled("Think TDD")
    @Test
    void tUpdateQueryText() {
        String queryText = cachedTest( () -> fac.updateQueryText() );
        String columnNames = columnNames( );
        String placeHolders = fac.makePlaceHolders( 8 );

        String[] expected = { "update", "employees", "set", "(" + columnNames
                              + ")", "=",
                              "(" + placeHolders + ")" };
        System.out.println( "saveQueryText = " + queryText );
        assertThat( queryText.toLowerCase() ).contains( expected );
        //End Solution::replacewith::fail( "tUpdateQueryText completed succesfully; you know what to do" );
    }

    private String columnNames() {
        //Start Solution::replacewith:://TODO
        String columnNames = mapper.editHelpers().stream()
                .map( EditHelper::fieldName ).collect( joining( "," ) );
        return columnNames;
    }

    //@Disabled("Think TDD")
    @Test
    void tSelectQueryText() {
        String queryText = cachedTest( () -> fac.getQueryText() );
        //Start Solution::replacewith:://TODO
       String columnNames = columnNames( );

        String[] expected = { "select", columnNames, "from", "employees",
                              "where", "employeeid", "=",
                              "?" };
        System.out.println( "saveQueryText = " + queryText );
        assertThat( queryText.toLowerCase() ).contains( expected );
        //End Solution::replacewith::fail( "tSelectQueryText completed succesfully; you know what to do" );
    }

    //@Disabled("Think TDD")
    @Test
    void tSelectAllQueryText() {
        String queryText = cachedTest( () -> fac.allQuery() );
        //Start Solution::replacewith:://TODO
        String columnNames = columnNames( );

        String[] expected = { "select", columnNames, "from", "employees" };
        System.out.println( "saveQueryText = " + queryText );
        assertThat( queryText.toLowerCase() ).contains( expected );
        //End Solution::replacewith::fail( "tSelectAllQueryText completed succesfully; you know what to do" );
    }

    //@Disabled("Think TDD")
    @Test
    void tLastIdQuery() {
        String queryText = cachedTest( () -> fac.lastIdQuery() );
        assertThat( queryText ).contains( "select", "max(employeeid)", "as",
                "lastid", "from", "employees" );

//        fail( "method LastIdQuery completed succesfully; you know what to do" );
    }

    //@Disabled("Think TDD")
    @Test
    void tDropAllQuery() {
        String queryText = cachedTest( () -> fac.dropallQuery() );
        assertThat( queryText ).contains( "truncate", "employees", "restart",
                "identity", "cascade" );

//        fail( "method tDropAllQuery completed succesfully; you know what to do" );
    }

    /**
     * Check if a call result is cached.
     *
     * @param supplier
     * @return
     */
    String cachedTest( Supplier<String> supplier ) {
        String first = supplier.get();
        assertThat( supplier.get() ).isSameAs( first );
        return first;
    }

    //@Disabled("Think TDD")
    @Test
    void tTableNameAnnotationUsed() {
        RecordMapper<?, ?> cmapper = RecordMapper.mapperFor( Company.class );
        QueryFactory fac = new QueryFactory( cmapper );

        assertThat( cachedTest( () -> fac.tableName() ) )
                .isEqualTo( "companies" );
//        fail( "method TableNameAnnotationUsed completed succesfully; you know what to do" );
    }
}
