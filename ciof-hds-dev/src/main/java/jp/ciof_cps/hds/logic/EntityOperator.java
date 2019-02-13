/**
 * EntityOperator.java
 * (C) 2019. Industrial Value Chain Initiative
 */
package jp.ciof_cps.hds.logic;

import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.AssertionFailure;
import org.hibernate.exception.GenericJDBCException;
import org.hibernate.exception.JDBCConnectionException;

import jp.ciof_cps.hds.service.BasicEntityService;

/**
 * This class is the entity operator class for Logic Implementation.
 */
@ApplicationScoped
public class EntityOperator {
	static final Log LOG = LogFactory.getLog(EntityOperator.class);
	
	/**
	 * This interface indicates execution of entity service.
	 * @param <TYPE> the type of result
	 */
	public interface EntityServiceExecutor<TYPE> {
		/**
		 * Execute operation.
		 * @return the result
		 */
		TYPE execute();
	}

	/**
	 * Wrap the execution of entity service.
	 * Exceptions which occurs in entity service are converted to LogicException.
	 * @param <TYPE> the type of result
	 * @param entityServiceExecutor the executor of entity service
	 * @return the result
	 * @throws LogicException operation error
	 */
	public static <TYPE> TYPE wrap(EntityServiceExecutor<TYPE> entityServiceExecutor) throws LogicException {
		TYPE result = null;
		try {
			result = entityServiceExecutor.execute();
		} catch (PersistenceException e) {
			if (e.getCause() instanceof JDBCConnectionException || e.getCause() instanceof GenericJDBCException) {
				Throwable cause = e.getCause();
				String message = "Failed to operate data.";
				LOG.debug(message, e);
				Integer code = Status.INTERNAL_SERVER_ERROR.getStatusCode();
				String detail = e.getMessage();
				throw new LogicException(message, code, detail, cause);
			}
			
			String message = "Failed to operate data.";
			LOG.debug(message, e);
			Integer code = Status.BAD_REQUEST.getStatusCode();
			String detail = e.getMessage();
			Throwable cause = e;
			throw new LogicException(message, code, detail, cause);
		} catch (AssertionFailure e) {
			String message = "Failed to operate data.";
			LOG.debug(message, e);
			Integer code = Status.BAD_REQUEST.getStatusCode();
			String detail = e.getMessage();
			Throwable cause = e;
			throw new LogicException(message, code, detail, cause);
		} catch (RuntimeException e) {
			String message = "Failed to operate data.";
			LOG.debug(message, e);
			Integer code = Status.INTERNAL_SERVER_ERROR.getStatusCode();
			String detail = e.getMessage();
			Throwable cause = e;
			throw new LogicException(message, code, detail, cause);
		}
		return result;
	}

	@Inject
	BasicEntityService entityService;

	/**
	 * Select all entities.
	 * @param <ENTITY> type parameter of entity class
	 * @param entityClass type of entity 
	 * @return entity list
	 * @throws LogicException operation error
	 */
	public <ENTITY> List<ENTITY> selectAll(Class<ENTITY> entityClass) throws LogicException {
		return wrap(()->{
			return entityService.selectAll(entityClass);
		});
	}

	/**
	 * Select a entity by primary key.
	 * @param <ENTITY> type parameter of entity class
	 * @param entityClass type of entity 
	 * @param primaryKeyObject the primary key object.
	 * @return the entity.
	 * @throws LogicException operation error
	 */
	public <ENTITY> ENTITY selectByPk(Class<ENTITY> entityClass, Object primaryKeyObject) throws LogicException {
		return wrap(()->{
			return entityService.selectByPk(entityClass, primaryKeyObject);
		});
	}
	

	/**
	 * Find entities by search conditions.
	 * @param <ENTITY> type parameter of entity class
	 * @param entityClass type of entity 
	 * @param conditions search conditions. Map of property name and the value.
	 * @return the entity list
	 * @throws LogicException operation error
	 */
	public <ENTITY> List<ENTITY> findByConditions(Class<ENTITY> entityClass, Map<String, Object> conditions) throws LogicException {
		return wrap(()->{
			return entityService.findByConditions(entityClass, conditions);
		});
	}

	/**
	 * Find entities by search conditions.
	 * @param <ENTITY> type parameter of entity class
	 * @param entityClass type of entity 
	 * @param conditions search conditions. Map of property name and the value.
	 * @param orderByAscs property name list of entity
	 * @param orderByDescs property name list of entity
	 * @param limit maximum number of results to find
	 * @param offset position of the first result, numbered from 0
	 * @return the entity list
	 * @throws LogicException operation error
	 */
	public <ENTITY> List<ENTITY> findByConditions(Class<ENTITY> entityClass, Map<String, Object> conditions, List<String> orderByAscs, List<String> orderByDescs, Integer limit, Integer offset) throws LogicException {
		return wrap(()->{
			return entityService.findByConditions(entityClass, conditions, orderByAscs, orderByDescs, limit, offset);
		});
	}
	

	/**
	 * Insert the entity to the table.
	 *
	 * @param <ENTITY> type parameter of entity class
	 * @param entity the inserted entity
	 * @throws LogicException operation error
	 */
	public <ENTITY> void insert(ENTITY entity) throws LogicException {
		wrap(()->{
			entityService.insert(entity);
			return null;
		});
	}
	

	/**
	 * Insert entities to the table.
	 *
	 * @param <ENTITY> type parameter of entity class
	 * @param entities the list of inserted entities.
	 * @throws LogicException operation error
	 */
	public <ENTITY> void insert(List<ENTITY> entities) throws LogicException {
		wrap(()->{
			entityService.insert(entities);
			return null;
		});
	}

	/**
	 * Update the entity on the table.
	 *
	 * @param <ENTITY> type parameter of entity class
	 * @param entity the entity
	 * @throws LogicException operation error
	 */
	public <ENTITY> void update(ENTITY entity) throws LogicException {
		wrap(()->{
			entityService.update(entity);
			return null;
		});
	}

	/**
	 * Delete the entity from the table.
	 *
	 * @param <ENTITY> type parameter of entity class
	 * @param entity the entity.
	 * @throws LogicException operation error
	 */
	public <ENTITY> void delete(ENTITY entity) throws LogicException {
		wrap(()->{
			entityService.delete(entity);
			return null;
		});
	}

	/**
	 * Delete entities from the table.
	 *
	 * @param <ENTITY> type parameter of entity class
	 * @param entities the list of entities
	 * @throws LogicException operation error
	 */
	public <ENTITY> void delete(List<ENTITY> entities) throws LogicException {
		wrap(()->{
			entityService.delete(entities);
			return null;
		});
	}
	
	/**
	 * Flush the persistence context.
	 * 
	 * @throws LogicException operation error
	 */
	public  void flush() throws LogicException {
		wrap(()->{
			entityService.flush();
			return null;
		});
	}
}
