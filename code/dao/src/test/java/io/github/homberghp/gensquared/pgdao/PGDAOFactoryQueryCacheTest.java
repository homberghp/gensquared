package io.github.homberghp.gensquared.pgdao;

import entities.Employee;
import static io.github.homberghp.gensquared.pgdao.DBTestHelpers.daof;
import io.github.homberghp.gensquared.pgdao.PGDAO;
import io.github.homberghp.gensquared.pgdao.PGDAOTestBase;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Pieter van den Hombergh (879417) {@code p.vandenhombergh@fontys.nl}
 */
public class PGDAOFactoryQueryCacheTest extends PGDAOTestBase {

    @Test
    public void testCreatesCache() {
        PGDAO<Employee, Integer> edao = daof.createDao( Employee.class );
        int size = daof.queryStringCache.size();
        assertThat( size ).as( "has one elements" ).isEqualTo( 1 );
    }

//    @Test
//    public void testComputeSelect() {
//        PGDAO<Employee,Integer> edao = daof.createDao( Employee.class );
//        Optional<Employee> get = edao.get( 1 );
//        int size = edao.queryTextCache.size();
//        assertThat( 1 <= size ).as( "the proper id type is Integer" ).isTrue();
//        //Assert.fail( "test method testComputeSelect reached its end, you can remove this line when you aggree." );
//    }
}
