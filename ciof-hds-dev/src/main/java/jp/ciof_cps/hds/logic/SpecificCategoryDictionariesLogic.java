/**
 * SpecificCategoryDictionariesLogic.java
 * (C) 2019. Industrial Value Chain Initiative
 */
package jp.ciof_cps.hds.logic;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jp.ciof_cps.hds.dto.ComponentCategoryDto;
import jp.ciof_cps.hds.dto.SpecificCategoryDictionaryDto;
import jp.ciof_cps.hds.dto.UserDto;
import jp.ciof_cps.hds.entity.SpecificCategoryDictionaryEntity;

/**
 * This class is the logic class for Specific Category Dictionary.
 */
@ApplicationScoped
@Transactional
public class SpecificCategoryDictionariesLogic {
	static final Log LOG = LogFactory.getLog(SpecificCategoryDictionariesLogic.class);
	
	private static final Class<SpecificCategoryDictionaryDto> DTO_CLASS = SpecificCategoryDictionaryDto.class;

	private static final Class<SpecificCategoryDictionaryEntity> ENTITY_CLASS = SpecificCategoryDictionaryEntity.class;

	private static final String[] EXCLUDE_COPY_PROP_NAMES = {
			"categoryDictionaryId",
			"componentCategoryIdList"
			};
	
	private static final String[] EXCLUDE_NULL_COPY_PROP_NAMES = {
			"categoryDictionaryName",
			"categoryDictionaryDescription"
			};	

	private static final String PROCESS = "process";
    private static final String DATA = "data"; 
    private static final String EVENT = "event";   
	
	@Inject
	IdGenerator idGenerator;
	
	@Inject
	EntityOperator entityOperator;
	
	@Inject
	ComponentCategoriesLogic componentCategoriesLogic;
	
	@Inject
	HdsAccessController accessController;
	
	/**
	 * Create data.
	 * 
	 * @param userDto the user DTO
	 * @param dto the data to create 
	 * @return newDto the created data
	 * @throws LogicException operation error in logic
	 */
	public SpecificCategoryDictionaryDto create(UserDto userDto, SpecificCategoryDictionaryDto dto) throws LogicException {
		assertArguments(userDto);
		assertArguments(dto);
		accessController.validateRoleIsAdministrator(userDto);
		
		String id = idGenerator.generateId(this.getClass());
		
		SpecificCategoryDictionaryEntity entity = BeanConverter.convert(dto, ENTITY_CLASS);
		entity.setCategoryDictionaryId(id);
		
		entityOperator.insert(entity);
		
		SpecificCategoryDictionaryDto newDto = BeanConverter.convert(entity, DTO_CLASS);
		fillComponentCategoryIdList(newDto, userDto);
		return newDto;
	}
	
	/**
	 * Update data
	 * 
	 * @param userDto the user DTO
	 * @param id the ID to update
	 * @param dto the data to update
	 * @return the updated data
	 * @throws LogicException operation error in logic
	 */
	public SpecificCategoryDictionaryDto update(UserDto userDto, String id, SpecificCategoryDictionaryDto dto) throws LogicException {
		assertArguments(userDto, id);
		assertArguments(dto);
		accessController.validateRoleIsAdministrator(userDto);
		
		SpecificCategoryDictionaryEntity entity = readEntityForcely(userDto, id);

		BeanConverter.copyProperties(dto, entity, ENTITY_CLASS, EXCLUDE_COPY_PROP_NAMES, EXCLUDE_NULL_COPY_PROP_NAMES);
		entity.setCategoryDictionaryId(id);
		
		entityOperator.update(entity);
		
		SpecificCategoryDictionaryDto newDto = BeanConverter.convert(entity, DTO_CLASS);
		fillComponentCategoryIdList(newDto, userDto);
		return newDto;
	}
	
	/**
	 * Delete data.
	 * 
	 * @param userDto the user DTO
	 * @param id the ID to delete
	 * @throws LogicException operation error in logic
	 */
	public void delete(UserDto userDto, String id) throws LogicException {
		assertArguments(userDto, id);
		accessController.validateRoleIsAdministrator(userDto);
		
		SpecificCategoryDictionaryEntity entity = readEntityForcely(userDto, id);
		
		entityOperator.delete(entity);
	}
	
	/**
	 * Read data.
	 * 
	 * @param userDto the user DTO
	 * @param id the ID to read
	 * @return the data
	 * @throws LogicException operation error in logic
	 */
	public SpecificCategoryDictionaryDto read(UserDto userDto, String id) throws LogicException {
		assertArguments(userDto, id);
		
		SpecificCategoryDictionaryEntity entity = readEntityForcely(userDto, id);
		
		SpecificCategoryDictionaryDto newDto = BeanConverter.convert(entity, DTO_CLASS);
		fillComponentCategoryIdList(newDto, userDto);
		return newDto;
	}
	
	/**
	 * Read data.
	 * 
	 * @param userDto the user DTO
	 * @return the data list
	 * @throws LogicException operation error in logic
	 */
	public List<SpecificCategoryDictionaryDto> read(UserDto userDto) throws LogicException {
		assertArguments(userDto);
		
		List<SpecificCategoryDictionaryEntity> entities = entityOperator.selectAll(ENTITY_CLASS);
		List<SpecificCategoryDictionaryDto> dtos = BeanConverter.convert(entities, DTO_CLASS);
		for (SpecificCategoryDictionaryDto dto : dtos) {
			fillComponentCategoryIdList(dto, userDto);
		}
		return dtos;
	}

	/**
	 * Fill componentCategoryIdList to DTO.
	 * 
	 * @param dto the DTO
	 * @param userDto the user DTO
	 * @throws LogicException operation error in logic
	 */
	private void fillComponentCategoryIdList(SpecificCategoryDictionaryDto dto, UserDto userDto) throws LogicException {
		List<String> componentCategoryIdList= new ArrayList<>();
		List<ComponentCategoryDto> componentCategories = componentCategoriesLogic.readList(userDto, dto.getCategoryDictionaryId(), DATA);
		componentCategories.addAll(componentCategoriesLogic.readList(userDto, dto.getCategoryDictionaryId(), PROCESS));
		componentCategories.addAll(componentCategoriesLogic.readList(userDto, dto.getCategoryDictionaryId(), EVENT));
		for (ComponentCategoryDto componentCategoryDto : componentCategories) {
			componentCategoryIdList.add(componentCategoryDto.getComponentCategoryId());
		}
		dto.setComponentCategoryIdList(componentCategoryIdList);
	}

	/**
	 * Obtain entity.
	 * If entity not found, an exception occurs.
	 * 
	 * @param userDto the user DTO
	 * @param categoryDictionaryId the Category Dictionary ID
	 * @return the entity
	 * @throws LogicException operation error in logic
	 */
	private SpecificCategoryDictionaryEntity readEntityForcely(UserDto userDto, String categoryDictionaryId) throws LogicException {
		SpecificCategoryDictionaryEntity entity = entityOperator.selectByPk(ENTITY_CLASS, categoryDictionaryId);
		if (entity == null) {
			throw new LogicException("Data not found.: categoryDictionaryId=" + categoryDictionaryId, Status.NOT_FOUND.getStatusCode());
		}
		return entity;
	}

	/**
	 * Assert arguments
	 * 
	 * @param userDto the user DTO
	 * @throws LogicException Assertion failed.
	 */
	private void assertArguments(UserDto userDto) throws LogicException {
		if (userDto == null) {
			throw new LogicException("userDto is null.", Status.BAD_REQUEST.getStatusCode());
		}
	}

	/**
	 * Assert arguments
	 * 
	 * @param userDto the user DTO
	 * @param id the ID
	 * @throws LogicException Assertion failed.
	 */
	private void assertArguments(UserDto userDto, String id) throws LogicException {
		assertArguments(userDto);
		
		if (id == null) {
			throw new LogicException("ID is null.", Status.BAD_REQUEST.getStatusCode());
		}
	}

	/**
	 * Assert arguments
	 * 
	 * @param dto the DTO
	 * @throws LogicException Assertion failed.
	 */
	private void assertArguments(SpecificCategoryDictionaryDto dto) throws LogicException {
		if (dto == null) {
			throw new LogicException("Invalid data.", Status.BAD_REQUEST.getStatusCode());
		}
	}
}
