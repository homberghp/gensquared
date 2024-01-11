package io.github.homberghp.gensquared.inmemorydao;

import io.github.homberghp.gensquared.dao.DAO;
import io.github.homberghp.gensquared.dao.DAOFactory;
import io.github.homberghp.gensquared.dao.TransactionToken;
import java.io.Serializable;

/**
 *
 * @author "Pieter van den Hombergh {@code p.vandenhombergh@fontys.nl}"
 */
public class InMemoryDAOFactory extends DAOFactory {

    @Override
    public <E extends Record, K> DAO<E, K> createDao( Class<E> forClass ) {
        return new InMemoryDAO<>( forClass );
    }

    /**
     * The created dao do not use the transaction tokens.
     *
     * @param <E>      generic entity
     * @param <K>      generic key
     * @param forClass entity type
     * @param token    to pass
     * @return the dao.
     */
    @Override
    public <E extends Record, K> DAO<E, K> createDao( Class<E> forClass, TransactionToken token ) {
        return new InMemoryDAO<E, K>( forClass ).setTransactionToken( token );
    }

}
