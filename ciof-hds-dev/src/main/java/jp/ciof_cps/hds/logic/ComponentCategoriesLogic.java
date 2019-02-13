/**
 * ComponentCategoriesLogic.java
 * (C) 2019. Industrial Value Chain Initiative
 */
package jp.ciof_cps.hds.logic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jp.ciof_cps.hds.dto.ComponentCategoryDto;
import jp.ciof_cps.hds.dto.UserDto;
import jp.ciof_cps.hds.entity.ComponentCategoryEntity;

/**
 * This class is the logic class for Component Category.
 */
@ApplicationScoped
@Transactional
public class ComponentCategoriesLogic {
	static final Log LOG = LogFactory.getLog(ComponentCategoriesLogic.class);
	
	private static final Class<ComponentCategoryDto> DTO_CLASS = ComponentCategoryDto.class;

	private static final Class<ComponentCategoryEntity> ENTITY_CLASS = ComponentCategoryEntity.class;

	private static final String[] EXCLUDE_COPY_PROP_NAMES = {
			"componentCategoryId",
			"categoryDictionaryId"
			};
	
	private static final String[] EXCLUDE_NULL_COPY_PROP_NAMES = {
			"componentCategoryType",
			"componentCategoryName",
			"componentCategoryDescription"
			};	
	@Inject
	IdGenerator idGenerator;
	
	@Inject
	EntityOperator entityOperator;
	
	@Inject
	HdsAccessController accessController;
	
	/**
	 * Create data.
	 * 
	 * @param userDto the user DTO
	 * @param categoryDictionaryId the Category Dictionary ID
	 * @param dto the data to create
	 * @return the created data
	 * @throws LogicException operation error in logic
	 */
	public ComponentCategoryDto create(UserDto userDto, String categoryDictionaryId, ComponentCategoryDto dto) throws LogicException {
		assertArguments(userDto, categoryDictionaryId);
		assertArguments(dto);
		accessController.validateRoleIsAdministrator(userDto);
		
		String id = idGenerator.generateId(this.getClass());
		
		ComponentCategoryEntity entity = BeanConverter.convert(dto, ENTITY_CLASS);
		entity.setComponentCategoryId(id);
		entity.setCategoryDictionaryId(categoryDictionaryId);
		entityOperator.insert(entity);
		ComponentCategoryDto newDto = BeanConverter.convert(entity, DTO_CLASS);
		return newDto;
	}

	/**
	 * Update data.
	 * 
	 * @param userDto the user DTO
	 * @param categoryDictionaryId the Category Dictionary ID
	 * @param id the ID of the data.
	 * @param dto the data to update
	 * @return the updated data
	 * @throws LogicException operation error in logic
	 */
	public ComponentCategoryDto update(UserDto userDto, String categoryDictionaryId, String id, ComponentCategoryDto dto) throws LogicException {
		assertArguments(userDto, categoryDictionaryId, id);
		assertArguments(dto);
		accessController.validateRoleIsAdministrator(userDto);
		
		ComponentCategoryEntity entity = readEntityForcely(userDto, categoryDictionaryId, id);

		BeanConverter.copyProperties(dto, entity, ENTITY_CLASS, EXCLUDE_COPY_PROP_NAMES, EXCLUDE_NULL_COPY_PROP_NAMES);
		entity.setComponentCategoryId(id);
		entity.setCategoryDictionaryId(categoryDictionaryId);
		
		entityOperator.update(entity);
		
		return BeanConverter.convert(entity, DTO_CLASS);
	}
	
	/**
	 * Delete data.
	 * 
	 * @param userDto the user DTO
	 * @param categoryDictionaryId the Category Dictionary ID
	 * @param id the ID of the data to delete
	 * @return the deleted data
	 * @throws LogicException operation error in logic
	 */
	public ComponentCategoryDto delete(UserDto userDto, String categoryDictionaryId, String id) throws LogicException {
		assertArguments(userDto, categoryDictionaryId, id);
		accessController.validateRoleIsAdministrator(userDto);
		
		ComponentCategoryEntity entity = readEntityForcely(userDto, categoryDictionaryId, id);
		
		entityOperator.delete(entity);
		return BeanConverter.convert(entity, DTO_CLASS);
	}
	
	/**
	 * Read data.
	 * 
	 * @param userDto the user DTO
	 * @param categoryDictionaryId the Category Dictionary ID
	 * @param id the ID of the data
	 * @return the data
	 * @throws LogicException operation error in logic
	 */
	public ComponentCategoryDto read(UserDto userDto, String categoryDictionaryId, String id) throws LogicException {
		assertArguments(userDto, categoryDictionaryId, id);
		
		ComponentCategoryEntity entity = readEntityForcely(userDto, categoryDictionaryId, id);
		
		return BeanConverter.convert(entity, DTO_CLASS);
	}
	
	/**
	 * Read data list
	 * 
	 * @param userDto the user DTO
	 * @param categoryDictionaryId the Category Dictionary ID
	 * @param componentCategoryType the componentCategoryType
	 * @return the data list
	 * @throws LogicException operation error in logic
	 */
	public List<ComponentCategoryDto> readList(UserDto userDto, String categoryDictionaryId, String componentCategoryType) throws LogicException {
		assertArguments(userDto, categoryDictionaryId);
		
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("categoryDictionaryId", categoryDictionaryId);
		if (componentCategoryType != null) {
			conditions.put("componentCategoryType", componentCategoryType);
		}
		List<ComponentCategoryEntity> entities = entityOperator.findByConditions(ENTITY_CLASS, conditions);
		return BeanConverter.convert(entities, DTO_CLASS);
	}

	/**
	 * Validate the specified componentCategoryId belongs to the categoryDictionaryId.
	 * 
	 * @param userDto the user DTO
	 * @param categoryDictionaryId the Category Dictionary ID
	 * @param componentCategoryId the Component Category ID
	 * @throws LogicException if The componentCategoryId doesn't belong to the categoryDictionaryId
	 */
	public void validateComponentDictionary(UserDto userDto, String categoryDictionaryId, String componentCategoryId) throws LogicException {
		if (categoryDictionaryId == null || componentCategoryId == null) {
			throw new LogicException(String.format(
					"The component category and category dictonary are invalid.: (categoryDictionaryId,componentCategoryId)=(%s,%s)",
					categoryDictionaryId, componentCategoryId), Status.BAD_REQUEST.getStatusCode());
		}
		ComponentCategoryEntity entity = readEntityForcely(userDto, categoryDictionaryId, componentCategoryId);
		if (!categoryDictionaryId.equals(entity.getCategoryDictionaryId())) {
			throw new LogicException(String.format(
					"The component category doesn't belong to the category dictonary.: (categoryDictionaryId,componentCategoryId)=(%s,%s)",
					categoryDictionaryId, componentCategoryId), Status.BAD_REQUEST.getStatusCode());
		}
	}

	/**
	 * obtain entity.
	 * If entity not found, an exception occurs.
	 * 
	 * @param userDto the user DTO
	 * @param categoryDictionaryId the Category Dictionary ID
	 * @param componentCategoryId the Component Category ID
	 * @return the entity
	 * @throws LogicException operation error in logic.
	 */
	private ComponentCategoryEntity readEntityForcely(UserDto userDto, String categoryDictionaryId, String componentCategoryId) throws LogicException {
		ComponentCategoryEntity entity = entityOperator.selectByPk(ENTITY_CLASS, componentCategoryId);
		if (entity == null) {
			throw new LogicException("Data not found.: componentCategoryId=" + componentCategoryId, Status.NOT_FOUND.getStatusCode());
		}
		return entity;
	}

	/**
	 * Assert arguments.
	 * @param userDto the user DTO
	 * @param categoryDictionaryId the Category Dictionary ID
	 * @throws LogicException Assertion failed.
	 */
	private void assertArguments(UserDto userDto, String categoryDictionaryId) throws LogicException {
		if (userDto == null) {
			throw new LogicException("userDto is null.", Status.BAD_REQUEST.getStatusCode());
		}

		if (categoryDictionaryId == null) {
			throw new LogicException("categoryDictionaryId is null.", Status.BAD_REQUEST.getStatusCode());
		}
	}

	/**
	 * Assert arguments.
	 * @param userDto the user DTO
	 * @param categoryDictionaryId the Category Dictionary ID
	 * @param id the ID
	 * @throws LogicException Assertion failed.
	 */
	private void assertArguments(UserDto userDto, String categoryDictionaryId, String id) throws LogicException {
		assertArguments(userDto, categoryDictionaryId);
		
		if (id == null) {
			throw new LogicException("ID is null.", Status.BAD_REQUEST.getStatusCode());
		}
	}

	/**
	 * Assert arguments.
	 * @param dto the DTO
	 * @throws LogicException Assertion failed.
	 */
	private void assertArguments(ComponentCategoryDto dto) throws LogicException {
		if (dto == null) {
			throw new LogicException("Invalid data.", Status.BAD_REQUEST.getStatusCode());
		}
	}
}
