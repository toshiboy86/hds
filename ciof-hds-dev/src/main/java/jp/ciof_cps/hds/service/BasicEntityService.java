/**
 * BasicEntityService.java
 * (C) 2019. Industrial Value Chain Initiative
 */
package jp.ciof_cps.hds.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * This class is the Basic Entity Services.
 */
@ApplicationScoped
public class BasicEntityService {

	@Inject
	EntityManager entityManager;
	
	/**
	 * Returns the entityManager.
	 * @return the value
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * Select all entities.
	 * @param <ENTITY> type parameter of entity class
	 * @param entityClass type of entity 
	 * @return entity list
	 */
	public <ENTITY> List<ENTITY> selectAll(Class<ENTITY> entityClass) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<ENTITY> f = builder.createQuery(entityClass);
		Root<ENTITY> customer = f.from(entityClass);
		f.select(customer);
		return entityManager.createQuery(f).getResultList();
	}

	/**
	 * Select a entity by primary key.
	 * @param <ENTITY> type parameter of entity class
	 * @param entityClass type of entity 
	 * @param primaryKeyObject the primary key object.
	 * @return the entity.
	 */
	public <ENTITY> ENTITY selectByPk(Class<ENTITY> entityClass, Object primaryKeyObject) {
		return entityManager.find(entityClass, primaryKeyObject);
	}

	/**
	 * Find entities by search conditions.
	 * @param <ENTITY> type parameter of entity class
	 * @param entityClass type of entity 
	 * @param conditions search conditions. Map of property name and the value.
	 * @return the entity list
	 */
	public <ENTITY> List<ENTITY> findByConditions(Class<ENTITY> entityClass, Map<String, Object> conditions) {
		return findByConditions(entityClass, conditions, null, null, null, null);
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
	 */
	public <ENTITY> List<ENTITY> findByConditions(Class<ENTITY> entityClass, Map<String, Object> conditions, List<String> orderByAscs, List<String> orderByDescs, Integer limit, Integer offset) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<ENTITY> criteriaQuery = cb.createQuery(entityClass);
		Root<ENTITY> root = criteriaQuery.from(entityClass);
		criteriaQuery = criteriaQuery.select(root);

		Predicate wherePredicate = createWherePredicate(root, cb, conditions);
		if (wherePredicate != null) {
			criteriaQuery = criteriaQuery.where(wherePredicate);
		}
		
		List<Order> orders = new ArrayList<>();
		if (orderByAscs != null) {
			for (String name : orderByAscs) {
				orders.add(cb.asc(root.get(name)));
			}
		}
		if (orderByDescs != null) {
			for (String name : orderByDescs) {
				orders.add(cb.desc(root.get(name)));
			}
		}
		if (orders.size() > 0) {
			criteriaQuery = criteriaQuery.orderBy(orders);
		}

		TypedQuery<ENTITY> typedQuery = entityManager.createQuery(criteriaQuery);
		if (offset != null) {
			typedQuery.setFirstResult(offset);
		}
		if (limit != null) {
			typedQuery.setMaxResults(limit);
		}
		return typedQuery.getResultList();
	}

	/**
	 * Create where predicate for query.
	 * @param <ENTITY> type parameter of entity class
	 * @param root the root type
	 * @param cb criteria builder
	 * @param conditions conditions of where predicate
	 * @return the predicate
	 */
	private <ENTITY> Predicate createWherePredicate(Root<ENTITY> root, CriteriaBuilder cb, Map<String, Object> conditions) {
		List<Predicate> predicates = new ArrayList<>();
		for (Entry<String, Object> entry : conditions.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			Expression<Object> expr = root.get(key);
			
			Predicate eqPredicate = cb.equal(expr, value);
			predicates.add(eqPredicate);
		}
		
		Predicate predicate = null;
		if (predicates.size() == 0) {
			predicate = null;
		} else if (predicates.size() == 1) {
			predicate = predicates.get(0);
		} else {
			predicate = cb.and(predicates.toArray(new Predicate[0]));
		}
		return predicate;
	}

	/**
	 * Insert the entity to the table.
	 *
	 * @param <ENTITY> type parameter of entity class
	 * @param entity the inserted entity
	 */
	public <ENTITY> void insert(ENTITY entity) {
		entityManager.persist(entity);
	}

	/**
	 * Insert entities to the table.
	 *
	 * @param <ENTITY> type parameter of entity class
	 * @param entities the list of inserted entities.
	 */
	public <ENTITY> void insert(List<ENTITY> entities) {
		for (ENTITY i : entities) {
			entityManager.persist(i);
		}
	}

	/**
	 * Update the entity on the table.
	 *
	 * @param <ENTITY> type parameter of entity class
	 * @param entity the entity
	 */
	public <ENTITY> void update(ENTITY entity) {
		entityManager.merge(entity);
	}

	/**
	 * Delete the entity from the table.
	 *
	 * @param <ENTITY> type parameter of entity class
	 * @param entity the entity.
	 */
	public <ENTITY> void delete(ENTITY entity) {
		entityManager.remove(entity);
	}

	/**
	 * Delete entities from the table.
	 *
	 * @param <ENTITY> type parameter of entity class
	 * @param entities the list of entities
	 */
	public <ENTITY> void delete(List<ENTITY> entities) {
		for (ENTITY entity : entities) {
			entityManager.remove(entity);
		}
	}

	/**
	 * Flush the persistence context.
	 *
	 */
	public void flush() {
		entityManager.flush();
	}
}
