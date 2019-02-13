/**
 * ServiceDictionariesLogic.java
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
import jp.ciof_cps.hds.dto.ProcessComponentModelDto;
import jp.ciof_cps.hds.dto.ServiceDictionaryDto;
import jp.ciof_cps.hds.dto.UserDto;
import jp.ciof_cps.hds.entity.ServiceDictionaryEntity;

/**
 * This class is the logic class for Service Dictionary.
 */
@ApplicationScoped
@Transactional
public class ServiceDictionariesLogic {
	
	private static final Class<ServiceDictionaryDto> DTO_CLASS = ServiceDictionaryDto.class;
	
	private static final Class<ServiceDictionaryEntity> ENTITY_CLASS = ServiceDictionaryEntity.class;
	
	private static final String[] EXCLUDE_COPY_PROP_NAMES = {
			"serviceDictionaryId",
			"version",
			"previousServiceDictionaryId",
			"ownerId",
			"pcmIdList",
			"ecmIdList"
			};
	
	private static final String[] EXCLUDE_NULL_COPY_PROP_NAMES = {
			"serviceDictionaryName",
			"serviceDictionaryDescription",
			"serviceDictionaryType"
			};
	
	@Inject
	IdGenerator idGenerator;
	
	@Inject
	EntityOperator entityOperator;
	
	@Inject
	EventAndConditionModelsLogic eventAndConditionModelsLogic;
	
	@Inject
	ProcessComponentModelsLogic processComponentModelsLogic;

	@Inject
	HdsAccessController accessController;
	
	static final Log LOG = LogFactory.getLog(ServiceDictionariesLogic.class);

    /**
     * Create data.
     * 
     * @param userDto the user DTO
     * @param dto the data
     * @return the create data
     * @throws LogicException operation error in logic 
     */
    public ServiceDictionaryDto create(UserDto userDto, ServiceDictionaryDto dto) throws LogicException {
    	ServiceDictionaryDto newDto = null;
    	String serviceDictionaryId = idGenerator.generateId(this.getClass());
    	String previousServiceDictionaryId = null;
    	Integer version = 1;
    	ServiceDictionaryEntity previousEntity = null;
    	
    	previousServiceDictionaryId = dto.getPreviousServiceDictionaryId();
    	dto.setServiceDictionaryId(serviceDictionaryId);
    	
    	ServiceDictionaryEntity entity = BeanConverter.convert(dto, ENTITY_CLASS);
    	entity.setOwnerId(userDto.getUserName());
    	
    	if(previousServiceDictionaryId == null) {
    		entity.setVersion(version);
    	}else{
    		previousEntity = entityOperator.selectByPk(ENTITY_CLASS, previousServiceDictionaryId);
    		
    		if(previousEntity != null) {
    			version = previousEntity.getVersion();
    			entity.setVersion(++version);
    		}else{
    			throw new LogicException(String.format("Data not found.: (previousServiceDictionaryId,)=(%s,)", previousServiceDictionaryId), Status.NOT_FOUND.getStatusCode());
    		}
    	}
    	
		entityOperator.insert(entity);
		newDto = BeanConverter.createCopiedInstance(entity, DTO_CLASS);
    	
		return newDto;
    }

    /**
     * Update data.
     * 
     * @param userDto the user DTO 
     * @param serviceDictionaryId the Service Dictionary ID
     * @param dto the data
     * @return the create data
     * @throws LogicException operation error in logic 
     */
    public ServiceDictionaryDto update(UserDto userDto, String serviceDictionaryId, ServiceDictionaryDto dto) throws LogicException {
    	if (serviceDictionaryId != null && !serviceDictionaryId.equals(dto.getServiceDictionaryId())) {
			dto.setServiceDictionaryId(serviceDictionaryId);
		}
    	
		ServiceDictionaryEntity entity = entityOperator.selectByPk(ENTITY_CLASS, serviceDictionaryId);
		
		if (entity == null) {
			throw new LogicException("Failed to update serviceDictionary. serviceDictionary not found.: serviceDictionaryId=" + serviceDictionaryId, Status.NOT_FOUND.getStatusCode());
		}
		accessController.validateDictionaryOwner(userDto, serviceDictionaryId, entity.getOwnerId());
		
		BeanConverter.copyProperties(dto, entity, ENTITY_CLASS, EXCLUDE_COPY_PROP_NAMES, EXCLUDE_NULL_COPY_PROP_NAMES);
		
		entityOperator.update(entity);
		ServiceDictionaryDto newDto = BeanConverter.convert(entity, DTO_CLASS);
		List<String> ecmIdList = new ArrayList<>();
		List<String> pcmIdList = new ArrayList<>();
		
		List<EventAndConditionModelDto> ecmList = eventAndConditionModelsLogic.read(userDto, serviceDictionaryId);
		for (EventAndConditionModelDto ecmDto : ecmList) {
			 ecmIdList.add(ecmDto.getEcmId());
		}
		newDto.setEcmIdList(ecmIdList);
		
		List<ProcessComponentModelDto> pcmList = processComponentModelsLogic.read(userDto, serviceDictionaryId);
		for (ProcessComponentModelDto pcmDto : pcmList) {
			 pcmIdList.add(pcmDto.getPcmId());
		}
		newDto.setPcmIdList(pcmIdList);
		
		return newDto;
    }

    /**
     * Delete data.
     * 
     * @param userDto the user DTO 
     * @param serviceDictionaryId the Service Dictionary ID
     * @throws LogicException operation error in logic 
     */
    public void delete(UserDto userDto, String serviceDictionaryId) throws LogicException {
		ServiceDictionaryEntity entity = null;
		
		if (serviceDictionaryId != null) {
			entity = entityOperator.selectByPk(ENTITY_CLASS, serviceDictionaryId);
		}
		
		if (entity == null) {
			throw new LogicException("Failed to delete ServiceDictionary. SataDictionary not found.: ServiceDictionaryId=" + serviceDictionaryId, Status.NOT_FOUND.getStatusCode());
		}
		accessController.validateDictionaryOwner(userDto, serviceDictionaryId, entity.getOwnerId());
		
		entityOperator.delete(entity);
	}


	/**
	 * Read data.
	 * 
	 * @param userDto the user DTO 
	 * @param serviceDictionaryId the Service Dictionary ID
	 * @return the create data
	 * @throws LogicException operation error in logic 
	 */
	public ServiceDictionaryDto read(UserDto userDto, String serviceDictionaryId) throws LogicException {
		ServiceDictionaryDto newDto = null;		
		List<String> ecmIdList = new ArrayList<>();
		List<String> pcmIdList = new ArrayList<>();
		
		ServiceDictionaryEntity entity = readEntityForcely(userDto, serviceDictionaryId);
		accessController.validateDictionaryReadPermission(userDto, serviceDictionaryId, entity.getOwnerId(), entity.getServiceDictionaryType());

		newDto = BeanConverter.convert(entity, DTO_CLASS);
		
		List<EventAndConditionModelDto> ecmList = eventAndConditionModelsLogic.read(userDto, serviceDictionaryId);
		for (EventAndConditionModelDto ecmDto : ecmList) {
			 ecmIdList.add(ecmDto.getEcmId());
		}
		newDto.setEcmIdList(ecmIdList);
		
		List<ProcessComponentModelDto> pcmList = processComponentModelsLogic.read(userDto, serviceDictionaryId);
		for (ProcessComponentModelDto pcmDto : pcmList) {
			 pcmIdList.add(pcmDto.getPcmId());
		}
		newDto.setPcmIdList(pcmIdList);
		
		return newDto;
	}
 
    /**
     * Read data list.
     * 
     * @param userDto the user DTO 
     * @param serviceDictionaryType the Service Dictionary Type
     * @return the data list
     * @throws LogicException operation error in logic 
     */
    public List<ServiceDictionaryDto> readList(UserDto userDto, String serviceDictionaryType) throws LogicException {
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("serviceDictionaryType", serviceDictionaryType);
		if (accessController.isRequiredFilterByOwner(userDto, serviceDictionaryType)) {
			conditions.put("ownerId", userDto.getUserName());
		}
		List<ServiceDictionaryEntity> entities = entityOperator.findByConditions(ENTITY_CLASS, conditions);
		List<ServiceDictionaryDto> dtos = BeanConverter.convert(entities, DTO_CLASS);
		
		for (ServiceDictionaryDto dto : dtos) {
			List<String> ecmIdList = new ArrayList<>();
			List<String> pcmIdList = new ArrayList<>();
			String serviceDictionaryId = dto.getServiceDictionaryId();
			
			List<EventAndConditionModelDto> ecmList = eventAndConditionModelsLogic.read(userDto, serviceDictionaryId);
			for (EventAndConditionModelDto ecmDto : ecmList) {
				 ecmIdList.add(ecmDto.getEcmId());
			}
			dto.setEcmIdList(ecmIdList);
			
			List<ProcessComponentModelDto> pcmList = processComponentModelsLogic.read(userDto, serviceDictionaryId);
			for (ProcessComponentModelDto pcmDto : pcmList) {
				 pcmIdList.add(pcmDto.getPcmId());
			}
			dto.setPcmIdList(pcmIdList);	
		}
		
    	return dtos;
    }
    
	private ServiceDictionaryEntity readEntityForcely(UserDto userDto, String serviceDictionaryId) throws LogicException {
		ServiceDictionaryEntity entity = entityOperator.selectByPk(ENTITY_CLASS, serviceDictionaryId);
		if (entity == null) {
			throw new LogicException(String.format("Data not found.: (serviceDictionaryId,)=(%s,)", serviceDictionaryId), Status.NOT_FOUND.getStatusCode());	
		}
		
		return entity;
	}
}
