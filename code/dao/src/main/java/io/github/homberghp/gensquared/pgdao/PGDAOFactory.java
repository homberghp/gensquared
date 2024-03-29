package io.github.homberghp.gensquared.pgdao;

import io.github.homberghp.gensquared.dao.DAOFactory;
import io.github.homberghp.gensquared.dao.TransactionToken;
import io.github.homberghp.recordmappers.RecordMapper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.postgresql.PGConnection;
import org.postgresql.util.PGobject;

/**
 * DAO Factory for postgresql database.
 *
 * @author Pieter van den Hombergh {@code pieter.van.den.hombergh@gmail.com}
 */
public final class PGDAOFactory extends DAOFactory {

    private final DataSource ds;

    /**
     * Inject the datasource using this ctor.
     *
     * @param ds the data source
     */
    public PGDAOFactory( DataSource ds ) {
        this.ds = ds;
        queryStringCache = new ConcurrentHashMap<>();
    }

    @Override
    @SuppressWarnings( "unchecked" )
    public <E extends Record, K> PGDAO<E, K> createDao(
            Class<E> forClass ) {
        QueryFactory queryTextCache = queryStringCache
                .computeIfAbsent( forClass, x -> new QueryFactory( RecordMapper
                .mapperFor( forClass ) ) );
        return new PGDAO( this, ds, forClass, queryTextCache );
    }

    @Override
    @SuppressWarnings( "unchecked" )
    public <E extends Record, K > PGDAO<E, K> createDao(
            Class<E> forClass, TransactionToken token ) {
        return (PGDAO<E, K>) createDao( forClass ).setTransactionToken( token );
    }

    final Map<Class<?>, QueryFactory> queryStringCache;

    final Map<String, Class<? extends PGobject>> pgTypeMap = new ConcurrentHashMap<>();

    final Map<Class<?>, Function<?, ?>> marshallInMap = new ConcurrentHashMap<>();
    final Map<Class<?>, Function<?, ?>> marshallOutMap = new ConcurrentHashMap<>();

    /**
     * By default register modern LocalTime and LocalDateTime, so they can be used in the application.
     * 
     */
    {
        registerInMarshaller( LocalDate.class, ( Date d ) -> d.toLocalDate() );
        registerInMarshaller( LocalDateTime.class, ( Timestamp d ) -> d
                .toLocalDateTime() );
    }

    /**
     * Get a connection to be used by the DAOs created by this factory.
     *
     * @return a connection
     *
     * @throws SQLException when connection cannot be retrieved.
     */
    final Connection getConnection() throws SQLException {
        Connection con = ds.getConnection();

        if ( con instanceof PGConnection && !pgTypeMap.isEmpty() ) {
            final PGConnection pgcon = (PGConnection) con;
            for ( Map.Entry<String, Class<? extends PGobject>> entry : pgTypeMap
                    .entrySet() ) {
                pgcon.addDataType( entry.getKey(), entry.getValue() );
            }
        }
        if ( sqlTypeMap != null ) {
            Map<String, Class<?>> typeMap = con.getTypeMap();
            if ( typeMap == null ) {
                typeMap = new HashMap<>();
            }
            typeMap.putAll( sqlTypeMap );
        }
        return con;
    }

//    /**
//     * Register a pg data type or user type.
//     *
//     * @param name postgresql type name
//     * @param type java type, e.g. TsRange like in this package.
//     */
//    public final void registerPGdataType( String name, Class<? extends PGobject> type ) {
//        pgTypeMap.put( name, type );
//    }
    private Map<String, Class<?>> sqlTypeMap;

    /**
     * Transform, convert or cast incoming (from database) data.
     *
     * @param <U>  type of class to use
     * @param type token Class{@code <U>}
     * @param in   incoming
     *
     * @return user type object
     */
    @SuppressWarnings( "unchecked" )
    <U> U marshallIn( Class<U> type, Object in ) {
        return ( (Function<Object, U>) marshallInMap
                .getOrDefault( type, x -> x ) ).apply( in );
    }

    /**
     * Transform, convert or cast outgoing (to database) data.
     *
     * @param <U> type of class to use
     * @param out outgoing data
     *
     * @return object acceptable by the database.
     */
    @SuppressWarnings( "unchecked" )
    <U> U marshallOut( Object out ) {
        return ( (Function<Object, U>) marshallOutMap
                .getOrDefault( out.getClass(), x -> x ) ).apply( out );
    }

    /**
     * Add an in marshaller to this factory, so the connection can apply it on
     * data T coming from the PostgreSQL database and map it to the required
     * user type U.PostgreSQL has a rich type set beyond the standard set
     * defined for java.sql.
     *
     * Those types are typically a subclass of PGobject, which is relatively
     * easily mapped to a user type. An example would be TSDange, which is best
     * expressed as a time range with begin and end point of LocalDateTime.
     *
     * When the user defines a LocalDateTimeRange matching the semantics of
     * TSRange, and provides a mapper from tsrange to LocalDataTimeRange, than
     * such mapper can be registerd to this factory, so that the Dao can easily
     * map from the PGobject to the user object.
     *
     * <pre class='brush:java'>
     *
     * pgdaofactory.registerInMarshaller( LocalDateTimeRange.class, pgo
     * {@literal ->} LocalDateTimeRange.of(pgo));
     *
     * </pre>
     *
     *
     * @param <T>          incoming type
     * @param <R>          user type
     * @param targetType   type token for output
     * @param inMarshaller the mapper function, typically a lambda.
     *
     * @return this factory.
     */
    public final <T extends Serializable, R> PGDAOFactory registerInMarshaller(
            Class<R> targetType, Function<T, R> inMarshaller ) {
        this.marshallInMap.put( targetType, inMarshaller );
        return this;
    }

    public final <T extends Serializable, P extends PGobject> PGDAOFactory registerOutMarshaller(
            Class<T> targetType, Function<T, P> outMarshaller ) {
        this.marshallOutMap.put( targetType, outMarshaller );
        System.out.println( "register out targetType = " + targetType );
        return this;
    }

    public final <T extends Serializable, R, P extends PGobject> PGDAOFactory registerPGMashallers(
            Class<R> targetType, Function<T, R> inMarshaller,
            Function<R, P> outMarshaller ) {
        this.marshallInMap.put( targetType, inMarshaller );
        this.marshallOutMap.put( targetType, outMarshaller );

        return this;
    }

    public final PGDAOFactory registerPgObject( String pgname,
            Class<? extends PGobject> pgoClass ) {
        pgTypeMap.put( pgname, pgoClass );
        return this;
    }
//    Map<Class<? extends Serializable>, String> userToPg= new HashMap<>();
//    public final <T extends Serializable>void registerPGobject( Class<T> userType, String pgName){
//        this.userToPg.put(userType,pgName);
//    }

//    String getPgTypeName(Serializable o){
//        return userToPg.getOrDefault( o.getClass(), "" );
//    }
    
    /**
     * Helper for postgreSQL to wrap a value in a PGobject. The value object
     * must have a meaningful toString that can be understood by the database as
     * a value representation.
     *
     * @param pType pg type
     * @param value to wrap
     *
     * @return the wrapped value.
     */
    public static PGobject pgobject( String pType, Object value ) {
        PGobject result = new PGobject();
        result.setType( pType );
        try {
            result.setValue( value.toString() );
        } catch ( SQLException ex ) {
            Logger.getLogger( PGDAOFactory.class.getName() ).log( Level.SEVERE,
                    null, ex );
        }
        return result;
    }

}
