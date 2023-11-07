package repository;

import java.util.Optional;
import java.util.List;

public interface CrudInterface<T, ID> {

	/**
	 * Creates a new entity in the database
	 * 
	 * @param entity The entity to create
	 * @return True if creation successful
	 */
	Boolean create(T entity);

	/**
	 * Find all entities from the table
	 * 
	 * @return the list with all entities
	 */
	List<T> findAll();

	/**
	 * Find an entity by a given ID, it can be null if ID doesn't exist
	 * 
	 * @param id The ID to search
	 * @return The entity with the given ID or null
	 */
	Optional<T> findById(ID id);

	/**
	 * Updates the entity in the database
	 * 
	 * @param entity The entity to update
	 * @return True if update successful
	 */
	Boolean update(T entity);

	/**
	 * Deletes the given entity from the database
	 * 
	 * @param entity The entity to delete
	 * @return True if delete successful
	 */
	Boolean delete(T entity);
}
