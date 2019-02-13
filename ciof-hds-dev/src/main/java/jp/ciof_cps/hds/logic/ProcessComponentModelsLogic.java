/**
 * ProcessComponentModelsLogic.java
 * (C) 2019. Industrial Value Chain Initiative
 */
package jp.ciof_cps.hds.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jp.ciof_cps.hds.dto.DataComponentModelOperationDto;
import jp.ciof_cps.hds.dto.ProcessComponentModelDto;
import jp.ciof_cps.hds.dto.ProcessConditionDto;
import jp.ciof_cps.hds.dto.ProcessFlowDefinitionDto;
import jp.ciof_cps.hds.dto.UserDto;
import jp.ciof_cps.hds.entity.ComponentCategoryEntity;
import jp.ciof_cps.hds.entity.ProcessComponentModelEntity;
import jp.ciof_cps.hds.entity.SpecificCategoryDictionaryEntity;

/**
 * This class is the logic class for Process Component Models (PCM).
 */
@ApplicationScoped
@Transactional
public class ProcessComponentModelsLogic {

	static final Log LOG = LogFactory.getLog(ProcessComponentModelsLogic.class);

	private static final Class<ProcessComponentModelDto> DTO_CLASS = ProcessComponentModelDto.class;

	private static final Class<ProcessComponentModelEntity> ENTITY_CLASS = ProcessComponentModelEntity.class;
	
	private static final Class<SpecificCategoryDictionaryEntity> CATEGORY_DICTIONARY_ENTITY_CLASS = SpecificCategoryDictionaryEntity.class;
	
	private static final Class<ComponentCategoryEntity> COMPONENT_CATEGORY_ENTITY_CLASS = ComponentCategoryEntity.class;

	
	private static final String[] EXCLUDE_COPY_PROP_NAMES = {
			"pcmId",
			"serviceDictionaryId"
			};
	
	private static final String[] EXCLUDE_NULL_COPY_PROP_NAMES = {
			"pcmName",
			"pcmDescription",
			"author",
			"creationDate",
			"categoryDictionaryId",
			"componentCategoryId",
			"dcmOperationList",
			"pfdList",
			"preconditionList",
			"postconditionList",
			"hardwareRequirement",
			"otherRequirement"
			};

	@Inject
	IdGenerator idGenerator;
	
	@Inject
	EntityOperator entityOperator;
	
	@Inject
	DataComponentModelOperationsLogic dcmOperationsLogic;
	
	@Inject
	ProcessFlowDefinitionsLogic pfdLogic;
	
	@Inject
	ProcessConditionLogic processConditionLogic;

	@Inject
	HdsAccessController accessController;

	@Inject
	ComponentCategoriesLogic componentCategoriesLogic;

	/**
	 * Create data.
	 * 
	 * @param userDto the user DTO
	 * @param serviceDictionaryId the Service Dictionary ID
	 * @param dto the data
	 * @return the create data
	 * @throws LogicException operation error in logic 
	 */
	public ProcessComponentModelDto create(UserDto userDto, String serviceDictionaryId, ProcessComponentModelDto dto) throws LogicException {
		if (dto == null) {
			throw new LogicException("Invalid data.", Status.BAD_REQUEST.getStatusCode());
		}

		accessController.validateServiceDictionaryOwner(userDto, serviceDictionaryId);

		String id = idGenerator.generateId(this.getClass());

		ProcessComponentModelEntity entity = BeanConverter.convert(dto, ENTITY_CLASS);
		entity.setPcmId(id);
		entity.setServiceDictionaryId(serviceDictionaryId);

		componentCategoriesLogic.validateComponentDictionary(userDto, entity.getCategoryDictionaryId(), entity.getComponentCategoryId());

		entityOperator.insert(entity);
		ProcessComponentModelDto newDto = BeanConverter.convert(entity, DTO_CLASS);

		List<DataComponentModelOperationDto> dcmOperationList = dto.getDcmOperationList();
		if (dcmOperationList == null) {
			dcmOperationList = new ArrayList<>();
		}
		List<DataComponentModelOperationDto> newDcmOperationList =  dcmOperationsLogic.create(userDto, id, dcmOperationList);
		newDto.setDcmOperationList(newDcmOperationList);

		List<ProcessFlowDefinitionDto> pfdList = dto.getPfdList();
		if (pfdList == null) {
			pfdList = new ArrayList<>();
		}
		List<ProcessFlowDefinitionDto> newPfdList = pfdLogic.create(userDto, id, pfdList);
		newDto.setPfdList(newPfdList);

		List<ProcessConditionDto> preconditionList = dto.getPreconditionList();
		if (preconditionList == null) {
			preconditionList = new ArrayList<>();
		}
		List<ProcessConditionDto> newpreconditionList = processConditionLogic.createPreCondition(userDto, id, preconditionList);
		newDto.setPreconditionList(newpreconditionList);

		List<ProcessConditionDto> postconditionList = dto.getPostconditionList();
		if (postconditionList == null) {
			postconditionList = new ArrayList<>();
		}
		List<ProcessConditionDto> newpostconditionList = processConditionLogic.createPostCondition(userDto, id, postconditionList);
		newDto.setPostconditionList(newpostconditionList);

		return newDto;
	}

	/**
	 * Update data.
	 * 
	 * @param userDto the user DTO
	 * @param serviceDictionaryId the Service Dictionary ID
	 * @param pcmId the Process Component Model (PCM) ID
	 * @param dto the data
	 * @return the create data
	 * @throws LogicException operation error in logic 
	 */
	public ProcessComponentModelDto update(UserDto userDto, String serviceDictionaryId, String pcmId, ProcessComponentModelDto dto) throws LogicException {	

		accessController.validateServiceDictionaryOwner(userDto, serviceDictionaryId);

		ProcessComponentModelEntity entity = readEntityForcely(userDto, pcmId);
		BeanConverter.copyProperties(dto, entity, ENTITY_CLASS, EXCLUDE_COPY_PROP_NAMES, EXCLUDE_NULL_COPY_PROP_NAMES);

		entity.setPcmId(pcmId);
		entity.setServiceDictionaryId(serviceDictionaryId);

		categoryAssertArguments(dto.getCategoryDictionaryId(), dto.getComponentCategoryId());
		componentCategoriesLogic.validateComponentDictionary(userDto, entity.getCategoryDictionaryId(), entity.getComponentCategoryId());
		entityOperator.update(entity);

		ProcessComponentModelDto newDto = BeanConverter.convert(entity, DTO_CLASS);

		List<DataComponentModelOperationDto> dcmOperationList = dto.getDcmOperationList();
		List<DataComponentModelOperationDto> newDcmOperationList = null;
		if (dcmOperationList != null) {
			dcmOperationsLogic.delete(userDto, pcmId);
			newDcmOperationList =  dcmOperationsLogic.create(userDto, pcmId, dcmOperationList);
		} else {
			newDcmOperationList = dcmOperationsLogic.read(userDto, pcmId);
		}
		newDto.setDcmOperationList(newDcmOperationList);

		List<ProcessFlowDefinitionDto> pfdList = dto.getPfdList();
		List<ProcessFlowDefinitionDto> newPfdList = null;
		if (pfdList != null) {
			pfdLogic.delete(userDto, pcmId);
			newPfdList = pfdLogic.create(userDto, pcmId, pfdList);
		} else {
			newPfdList = pfdLogic.read(userDto, pcmId);
		}
		newDto.setPfdList(newPfdList);

		List<ProcessConditionDto> preconditionList = dto.getPreconditionList();
		List<ProcessConditionDto> newPreconditionList = null;
		if (preconditionList != null) {
			processConditionLogic.deletePreCondition(userDto, pcmId);
			newPreconditionList = processConditionLogic.createPreCondition(userDto, pcmId, preconditionList);
		} else {
			newPreconditionList = processConditionLogic.readPreCondition(userDto, pcmId);
		}
		newDto.setPreconditionList(newPreconditionList);

		
		List<ProcessConditionDto> postconditionList = dto.getPostconditionList();
		List<ProcessConditionDto> newPostconditionList = null;
		if (postconditionList != null) {
			processConditionLogic.deletePostCondition(userDto, pcmId);
			newPostconditionList = processConditionLogic.createPostCondition(userDto, pcmId, postconditionList);
		} else {
			newPostconditionList = processConditionLogic.readPostCondition(userDto, pcmId);
		}
		newDto.setPostconditionList(newPostconditionList);

		return newDto;
	}

	/**
	 * Delete data.
	 * 
	 * @param userDto the user DTO
	 * @param serviceDictionaryId the Service Dictionary ID
	 * @param pcmId the Process Component Model (PCM) ID
	 * @throws LogicException operation error in logic 
	 */
	public void delete(UserDto userDto, String serviceDictionaryId, String pcmId) throws LogicException {
		accessController.validateServiceDictionaryOwner(userDto, serviceDictionaryId);
		ProcessComponentModelEntity entity = readEntityForcely(userDto, pcmId);

		entityOperator.delete(entity);
	}

	/**
	 * Read list.
	 * 
	 * @param userDto the user DTO
	 * @param serviceDictionaryId the Service Dictionary ID
	 * @param pcmId the Process Component Model (PCM) ID
	 * @return the data
	 * @throws LogicException operation error in logic 
	 */
	public ProcessComponentModelDto read(UserDto userDto, String serviceDictionaryId, String pcmId) throws LogicException {
		accessController.validateServiceDictionaryReadPermission(userDto, serviceDictionaryId);

		ProcessComponentModelEntity entity = readEntityForcely(userDto, pcmId);
		ProcessComponentModelDto dto = BeanConverter.convert(entity, DTO_CLASS);

		List<DataComponentModelOperationDto> dcmOperationList = dcmOperationsLogic.read(userDto, pcmId);
		dto.setDcmOperationList(dcmOperationList);

		List<ProcessFlowDefinitionDto> pfdList = pfdLogic.read(userDto, pcmId);
		dto.setPfdList(pfdList);

		List<ProcessConditionDto> preconditionList = processConditionLogic.readPreCondition(userDto, pcmId);
		dto.setPreconditionList(preconditionList);

		List<ProcessConditionDto> postconditionList = processConditionLogic.readPostCondition(userDto, pcmId);
		dto.setPostconditionList(postconditionList);

		return dto;
	}
	
	/**
	 * Read data list.
	 * 
	 * @param userDto the user DTO
	 * @param serviceDictionaryId the Service Dictionary ID
	 * @return dtos the data list
	 * @throws LogicException operation error in logic 
	 */
	public List<ProcessComponentModelDto> read(UserDto userDto, String serviceDictionaryId) throws LogicException  {
		accessController.validateServiceDictionaryReadPermission(userDto, serviceDictionaryId);

		Map<String, Object> conditions = new HashMap<>();
		conditions.put("serviceDictionaryId", serviceDictionaryId);
		List<ProcessComponentModelEntity> entities = entityOperator.findByConditions(ENTITY_CLASS, conditions);
		List<ProcessComponentModelDto> dtos = BeanConverter.convert(entities, DTO_CLASS);

		for (ProcessComponentModelDto dto : dtos) {
			String pcmId = dto.getPcmId();

			List<DataComponentModelOperationDto> dcmOperationList = dcmOperationsLogic.read(userDto, pcmId);
			dto.setDcmOperationList(dcmOperationList);

			List<ProcessFlowDefinitionDto> pfdList = pfdLogic.read(userDto, pcmId);
			dto.setPfdList(pfdList);

			List<ProcessConditionDto> preconditionList = processConditionLogic.readPreCondition(userDto, pcmId);
			dto.setPreconditionList(preconditionList);

			List<ProcessConditionDto> postconditionList = processConditionLogic.readPostCondition(userDto, pcmId);
			dto.setPostconditionList(postconditionList);
		}

		return dtos;
	}
	
	private void categoryAssertArguments(String categoryDictionaryId, String componentCategoryId) throws LogicException  {
		if(categoryDictionaryId != null || componentCategoryId != null) {
			if(categoryDictionaryId == null) {
				throw new LogicException("categoryDictionaryId is null.", Status.BAD_REQUEST.getStatusCode());
			}
			
			if(componentCategoryId == null) {
				throw new LogicException("componentCategoryId is null.", Status.BAD_REQUEST.getStatusCode());
			}
		}
		
		if(categoryDictionaryId != null && componentCategoryId != null) {
			SpecificCategoryDictionaryEntity categoryDictionaryEntity = null;
			categoryDictionaryEntity = entityOperator.selectByPk(CATEGORY_DICTIONARY_ENTITY_CLASS, categoryDictionaryId);
			
			if(categoryDictionaryEntity == null) {
				throw new LogicException(String.format("Data not found.: (categoryDictionaryId,)=(%s,)", categoryDictionaryId), Status.BAD_REQUEST.getStatusCode());
			}
			
			ComponentCategoryEntity componentCategoryEntity = null;
			componentCategoryEntity = entityOperator.selectByPk(COMPONENT_CATEGORY_ENTITY_CLASS, componentCategoryId); 
			
			if(componentCategoryEntity == null) {
				throw new LogicException(String.format("Data not found.: (componentCategoryId,)=(%s,)", componentCategoryId), Status.BAD_REQUEST.getStatusCode());
			}
			
			Map<String, Object> conditions = new HashMap<>();
			conditions.put("componentCategoryId", componentCategoryId);
			List<ComponentCategoryEntity> entities = entityOperator.findByConditions(COMPONENT_CATEGORY_ENTITY_CLASS, conditions);
			if(entities == null) {
				throw new LogicException(String.format("Data not found.: (componentCategoryId,)=(%s,)", componentCategoryId), Status.BAD_REQUEST.getStatusCode());
			}
		}
	}
	
	private ProcessComponentModelEntity readEntityForcely(UserDto userDto, String pcmId) throws LogicException {
		ProcessComponentModelEntity entity = entityOperator.selectByPk(ENTITY_CLASS, pcmId);
		if (entity == null) {
			throw new LogicException(String.format("Data not found.: (pcmId,)=(%s,)", pcmId), Status.NOT_FOUND.getStatusCode());
		}
		return entity;
	}
}
