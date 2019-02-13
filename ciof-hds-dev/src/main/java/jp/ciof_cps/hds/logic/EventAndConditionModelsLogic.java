/**
 * EventAndConditionModelsLogic.java
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

import jp.ciof_cps.hds.dto.EventAndConditionModelDto;
import jp.ciof_cps.hds.dto.EventComponentStateDto;
import jp.ciof_cps.hds.dto.ProcessEventDto;
import jp.ciof_cps.hds.dto.RegularEventDto;
import jp.ciof_cps.hds.dto.UserDto;
import jp.ciof_cps.hds.dto.UserOperationEventDto;
import jp.ciof_cps.hds.entity.ComponentCategoryEntity;
import jp.ciof_cps.hds.entity.EventAndConditionModelEntity;
import jp.ciof_cps.hds.entity.SpecificCategoryDictionaryEntity;

/**
 * This class is the logic class for Event And Condition Models (ECM).
 */
@ApplicationScoped
@Transactional
public class EventAndConditionModelsLogic {
	
	static final Log LOG = LogFactory.getLog(EventAndConditionModelsLogic.class);
	
	private static final Class<EventAndConditionModelDto> DTO_CLASS = EventAndConditionModelDto.class;

	private static final Class<EventAndConditionModelEntity> ENTITY_CLASS = EventAndConditionModelEntity.class;
	
	private static final Class<SpecificCategoryDictionaryEntity> CATEGORY_DICTIONARY_ENTITY_CLASS = SpecificCategoryDictionaryEntity.class;
	
	private static final Class<ComponentCategoryEntity> COMPONENT_CATEGORY_ENTITY_CLASS = ComponentCategoryEntity.class;

	private static final String[] EXCLUDE_COPY_PROP_NAMES = {
			"ecmId",
			"serviceDictionaryId"
			};
	
	private static final String[] EXCLUDE_NULL_COPY_PROP_NAMES = {
			"ecmName",
			"ecmDescription",
			"author",
			"creationDate",
			"categoryDictionaryId",
			"componentCategoryId",
			"processEventList",
			"ecsList",
			"userOperationEvent",
			"regularEvent",
			"otherRequirement"
			};	
	
	@Inject
	IdGenerator idGenerator;
	
	@Inject
	EntityOperator entityOperator;
	
	@Inject
	RegularEventsLogic regularEventsLogic;
	
	@Inject
	UserOperationEventsLogic userOperationEventsLogic;
	
	@Inject
	EventComponentStatesLogic eventComponentStateLogic;
	
	@Inject
	ProcessEventsLogic processEventLogic;

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
	public EventAndConditionModelDto create(UserDto userDto, String serviceDictionaryId, EventAndConditionModelDto dto) throws LogicException {	
		EventAndConditionModelDto newDto = null;

		accessController.validateServiceDictionaryOwner(userDto, serviceDictionaryId);

		List<RegularEventDto> regularEventList = dto.getRegularEvent();
		List<EventComponentStateDto> ecsList = dto.getEcsList();
		List<ProcessEventDto> processEventList = dto.getProcessEventList();
		List<UserOperationEventDto> userOperationEventList = dto.getUserOperationEvent();
		EventAndConditionModelEntity entity = BeanConverter.convert(dto, ENTITY_CLASS);		
		String ecmId = idGenerator.generateId(this.getClass());
		
		entity.setEcmId(ecmId);
		entity.setServiceDictionaryId(serviceDictionaryId);
		
		componentCategoriesLogic.validateComponentDictionary(userDto, entity.getCategoryDictionaryId(), entity.getComponentCategoryId());
		
		entityOperator.insert(entity);
		
		if (processEventList == null) {
			processEventList = new ArrayList<>();
		}
		if (userOperationEventList == null) {
			userOperationEventList = new ArrayList<>();
		}
		if (regularEventList == null) {
			regularEventList = new ArrayList<>();
		}
		if (ecsList == null) {
			ecsList = new ArrayList<>();
		}
		processEventList = processEventLogic.create(userDto, ecmId, processEventList);
		userOperationEventList = userOperationEventsLogic.create(userDto, ecmId, userOperationEventList);
		regularEventList =  regularEventsLogic.create(userDto, ecmId, regularEventList);
		ecsList = eventComponentStateLogic.create(userDto, ecmId, ecsList);

		newDto = BeanConverter.convert(entity, DTO_CLASS);
		newDto.setRegularEvent(regularEventList);
		newDto.setUserOperationEvent(userOperationEventList);
		newDto.setProcessEventList(processEventList);
		newDto.setEcsList(ecsList);
		
		return newDto;
	}

	/**
	 * Update data.
	 * 
	 * @param userDto the user DTO
	 * @param serviceDictionaryId the Service Dictionary ID
	 * @param ecmId the Event and Condition Model (ECM) ID
	 * @param dto the data
	 * @return the create data
	 * @throws LogicException operation error in logic 
	 */
	public EventAndConditionModelDto update(UserDto userDto, String serviceDictionaryId, String ecmId, EventAndConditionModelDto dto) throws LogicException {
		accessController.validateServiceDictionaryOwner(userDto, serviceDictionaryId);

		EventAndConditionModelEntity entity = readEntityForcely(userDto, ecmId);
		BeanConverter.copyProperties(dto, entity, ENTITY_CLASS, EXCLUDE_COPY_PROP_NAMES, EXCLUDE_NULL_COPY_PROP_NAMES);
		entity.setEcmId(ecmId);
		entity.setServiceDictionaryId(serviceDictionaryId);
		categoryAssertArguments(dto.getCategoryDictionaryId(), dto.getComponentCategoryId());
		
		componentCategoriesLogic.validateComponentDictionary(userDto, entity.getCategoryDictionaryId(), entity.getComponentCategoryId());
		
		entityOperator.update(entity);
		EventAndConditionModelDto newDto = BeanConverter.convert(entity, DTO_CLASS);
		
		List<ProcessEventDto> processEventList = dto.getProcessEventList();
		List<ProcessEventDto> newProcessEventList = null;
		if(processEventList != null) {
			processEventLogic.delete(userDto, ecmId);
			newProcessEventList = processEventLogic.create(userDto, ecmId, processEventList);
			
		}else{
			newProcessEventList = processEventLogic.read(userDto, ecmId);
		}
		newDto.setProcessEventList(newProcessEventList);

		List<EventComponentStateDto> ecsList = dto.getEcsList();
		List<EventComponentStateDto> newEcsList = null;
		
		if(ecsList != null) {
			eventComponentStateLogic.delete(userDto, ecmId);
			newEcsList = eventComponentStateLogic.create(userDto, ecmId, ecsList);
		}else{
			newEcsList = eventComponentStateLogic.read(userDto, ecmId);
		}
		newDto.setEcsList(newEcsList);

		List<UserOperationEventDto> userOperationEventList = dto.getUserOperationEvent();
		List<UserOperationEventDto> newUserOperationList = null;
		
		if(userOperationEventList != null) {
			userOperationEventsLogic.delete(userDto, ecmId);
			newUserOperationList = userOperationEventsLogic.create(userDto, ecmId, userOperationEventList);
		}else{
			newUserOperationList = userOperationEventsLogic.read(userDto, ecmId);
		}
		newDto.setUserOperationEvent(newUserOperationList);
		
		List<RegularEventDto> regularEventList = dto.getRegularEvent();
		List<RegularEventDto> newRegularEventList = null;		
		if(regularEventList != null) {
			regularEventsLogic.delete(userDto, ecmId);
			newRegularEventList = regularEventsLogic.create(userDto, ecmId, regularEventList);
		}else{
			newRegularEventList = regularEventsLogic.read(userDto, ecmId);
		}
		newDto.setRegularEvent(newRegularEventList);
		
		return newDto;
	}
	
	/**
	 * Delete data.
	 * 
	 * @param userDto the userDTO
	 * @param serviceDictionaryId the Service Dictionary ID 
	 * @param ecmId the Event and Condition Model (ECM) ID
	 * @throws LogicException operation error in logic 
	 */
	public void delete(UserDto userDto, String serviceDictionaryId, String ecmId) throws LogicException {
		assertArguments(userDto, serviceDictionaryId, ecmId);
		accessController.validateServiceDictionaryOwner(userDto, serviceDictionaryId);
		
		EventAndConditionModelEntity entity = readEntityForcely(userDto, ecmId);
		
		entityOperator.delete(entity);
	}
	
	/**
	 * Read data.
	 * 
	 * @param userDto the userDTO
	 * @param serviceDictionaryId the Service Dictionary ID 
	 * @param ecmId the Event and Condition Model (ECM) ID
	 * @return the data
	 * @throws LogicException operation error in logic 
	 */
	public EventAndConditionModelDto read(UserDto userDto, String serviceDictionaryId, String ecmId) throws LogicException {
		accessController.validateServiceDictionaryReadPermission(userDto, serviceDictionaryId);

		EventAndConditionModelEntity entity = readEntityForcely(userDto, ecmId);
		EventAndConditionModelDto dto = BeanConverter.convert(entity, DTO_CLASS);

		List<ProcessEventDto> processEventList = processEventLogic.read(userDto, ecmId);
		dto.setProcessEventList(processEventList);

		List<EventComponentStateDto> ecsList = eventComponentStateLogic.read(userDto, ecmId);
		dto.setEcsList(ecsList);

		List<UserOperationEventDto> userOperationEventList = userOperationEventsLogic.read(userDto, ecmId);
		dto.setUserOperationEvent(userOperationEventList);

		List<RegularEventDto> regularEventList = regularEventsLogic.read(userDto, ecmId);
		dto.setRegularEvent(regularEventList);

		return dto;
	}
	
	/**
	 * Read data list.
	 * 
	 * @param userDto the userDTO
	 * @param serviceDictionaryId the Service Dictionary ID 
	 * @return dtos the datas
	 * @throws  LogicException assertion failed.
	 */
	public List<EventAndConditionModelDto> read(UserDto userDto, String serviceDictionaryId) throws LogicException {
		accessController.validateServiceDictionaryReadPermission(userDto, serviceDictionaryId);

		Map<String, Object> conditions = new HashMap<>();
		conditions.put("serviceDictionaryId", serviceDictionaryId);
		List<EventAndConditionModelEntity> entities = entityOperator.findByConditions(ENTITY_CLASS, conditions);
		List<EventAndConditionModelDto> dtos = BeanConverter.convert(entities, DTO_CLASS);

		for (EventAndConditionModelDto dto : dtos) {
			String ecmId = dto.getEcmId();

			List<ProcessEventDto> processEventList = processEventLogic.read(userDto, ecmId);
			dto.setProcessEventList(processEventList);

			List<EventComponentStateDto> ecsList = eventComponentStateLogic.read(userDto, ecmId);
			dto.setEcsList(ecsList);

			List<UserOperationEventDto> userOperationEventList = userOperationEventsLogic.read(userDto, ecmId);
			dto.setUserOperationEvent(userOperationEventList);

			List<RegularEventDto> regularEventList = regularEventsLogic.read(userDto, ecmId);
			dto.setRegularEvent(regularEventList);
		}
		
		return dtos;
	}
	
	private void assertArguments(UserDto userDto, String serviceDictionaryId) throws LogicException {
		if (userDto == null) {
			throw new LogicException("userDto is null.", Status.BAD_REQUEST.getStatusCode());
		}
		if (serviceDictionaryId == null) {
			throw new LogicException("serviceDictionaryId is null.", Status.BAD_REQUEST.getStatusCode());
		}
	}
	
	private void assertArguments(UserDto userDto, String serviceDictionaryId, String ecmId) throws LogicException {
		assertArguments(userDto, serviceDictionaryId);
		
		if (ecmId == null) {
			throw new LogicException("ecmId is null.", Status.BAD_REQUEST.getStatusCode());
		}
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
	
	private EventAndConditionModelEntity readEntityForcely(UserDto userDto, String ecmId) throws LogicException {
		EventAndConditionModelEntity entity = entityOperator.selectByPk(ENTITY_CLASS, ecmId);
		if (entity == null) {
			throw new LogicException(String.format("Data not found.: (ecmId,)=(%s,)", ecmId), Status.NOT_FOUND.getStatusCode());	
		}
		
		return entity;
	}
}
