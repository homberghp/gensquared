package io.github.homberghp.gensquared.dao;


/**
 * Super of all DAO factories. Registers mappers that help to understand 
 * the entities that are to be mapped by this DAO.
 * 
 * @author Pieter van den Hombergh {@code pieter.van.den.hombergh@gmail.com}
 */
public abstract class DAOFactory {

    /**
     * Create a DAO for a given entity class indexed by a key class.
     *
     * @param <K> the key generic type
     * @param <E> the entity generic type
     * @param forClass actual type of the entity
     * @return the prepared DAO.
     */
    public abstract <E extends Record, K> DAO<E,K> 
        createDao( Class<E> forClass );

    /**
     *
     * Create a DAO for a given entity class indexed by a key class, prepared to
     * participate in a Transaction.
     *
     * @param <K> the key generic type
     * @param <E> the entity generic type
     * @param forClass actual type of the entity
     * @param token transaction token.
     * @return the prepared DAO
     */
    public abstract <E extends Record, K> DAO<E, K>
         createDao( Class<E> forClass,
            TransactionToken token );
}
