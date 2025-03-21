package ra.business.dao;

import java.util.List;

/**
 * A generic DAO interface providing CRUD operations.
 *
 * @param <T> the type of entity
 * @param <E> the type of entity's identifier
 */
public interface IGenericDao<T, E> {

    /**
     * Retrieves all entities.
     *
     * @return a list of all entities
     */
    List<T> findAll();

    /**
     * Finds an entity by its identifier.
     *
     * @param id the identifier of the entity
     * @return the entity with the given id, or null if not found
     */
    T findById(E id);

    /**
     * Saves or updates an entity.
     *
     * @param entity the entity to be saved or updated
     */
    void save(T entity);

    /**
     * Deletes an entity by its identifier.
     *
     * @param id the identifier of the entity to be deleted
     */
    void deleteById(E id);
}

