/**
 * DataRelationModelsLogic.java
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

import jp.ciof_cps.hds.dto.DataRelationModelAdditionDto;
import jp.ciof_cps.hds.dto.DataRelationModelDto;
import jp.ciof_cps.hds.dto.DataRelationModelItemDto;
import jp.ciof_cps.hds.dto.UserDto;
import jp.ciof_cps.hds.entity.DataRelationModelEntity;

/**
 * This class is the logic class for Data Relation Model (DRM).
 */
@ApplicationScoped
@Transactional
public class DataRelationModelsLogic {
	static final Log LOG = LogFactory.getLog(DataRelationModelsLogic.class);
	
	private static final Class<DataRelationModelDto> DTO_CLASS = DataRelationModelDto.class;

	private static final Class<DataRelationModelEntity> ENTITY_CLASS = DataRelationModelEntity.class;
	
	private static final String[] EXCLUDE_COPY_PROP_NAMES = {
			"drmId",
			"dataDictionaryId"
			};
	
	private static final String[] EXCLUDE_NULL_COPY_PROP_NAMES = {
			"dcmId",
			"dpdNameList",
			"targetDcmId",
			"targetDpdNameList",
			"additionalDpdNameList"
			};
	
	@Inject
	IdGenerator idGenerator;
	
	@Inject
	EntityOperator entityOperator;
	
	@Inject
	DataRelationModelItemsLogic itemsLogic;
	
	@Inject
	DataRelationModelAdditionsLogic additionsLogic;

	@Inject
	HdsAccessController accessController;

	/**
	 * Create data.
	 * 
	 * @param userDto the user DTO
	 * @param dataDictionaryId the Data Dictionary ID
	 * @param dto the data to create
	 * @return the created data
	 * @throws LogicException operation error in logic
	 */
	public DataRelationModelDto create(UserDto userDto, String dataDictionaryId, DataRelationModelDto dto) throws LogicException {
		assertArguments(userDto, dataDictionaryId);
		assertArguments(dto);
				
		accessController.validateDataDictionaryOwner(userDto, dataDictionaryId);
		
		String drmId = idGenerator.generateId(this.getClass());
		
		DataRelationModelEntity entity = BeanConverter.convert(dto, ENTITY_CLASS);
		entity.setDrmId(drmId);
		entity.setDataDictionaryId(dataDictionaryId);
		
		entityOperator.insert(entity);
		
		DataRelationModelDto newDto = BeanConverter.convert(entity, DTO_CLASS);
		
		List<String> dpdNameList = dto.getDpdNameList();
		List<String> targetDpdNameList = dto.getTargetDpdNameList();
		if (dpdNameList == null) {
			dpdNameList = new ArrayList<>();
		}
		if (targetDpdNameList == null) {
			targetDpdNameList = new ArrayList<>();
		}
		
		List<DataRelationModelItemDto> itemDtos = itemsLogic.createObjects(
				userDto, drmId, 
				dto.getDcmId(), dpdNameList, 
				dto.getTargetDcmId(), targetDpdNameList);
		setItems(newDto, itemDtos);
		
		List<String> additionalDpdNameList = dto.getAdditionalDpdNameList();
		if (additionalDpdNameList == null) {
			additionalDpdNameList = new ArrayList<>();
		}
		List<DataRelationModelAdditionDto> additionDtos = additionsLogic.createObjects(
				userDto, drmId, 
				dto.getTargetDcmId(), additionalDpdNameList);
		setAdditions(newDto, additionDtos);
		
		return newDto;
	}
	
	/**
	 * Update data.
	 * 
	 * @param userDto the user DTO
	 * @param dataDictionaryId the Data Dictionary ID
	 * @param drmId the Data Relation Model (DRM) ID
	 * @param dto the data to update
	 * @return the updated data
	 * @throws LogicException operation error in logic
	 */
	public DataRelationModelDto update(UserDto userDto, String dataDictionaryId, String drmId, DataRelationModelDto dto) throws LogicException {
		assertArguments(userDto, dataDictionaryId, drmId);
		assertArguments(dto);
		
		accessController.validateDataDictionaryOwner(userDto, dataDictionaryId);
		
		DataRelationModelEntity entity = readEntityForcely(userDto, drmId);
		BeanConverter.copyProperties(dto, entity, ENTITY_CLASS, EXCLUDE_COPY_PROP_NAMES, EXCLUDE_NULL_COPY_PROP_NAMES);
		entity.setDrmId(drmId);
		entity.setDataDictionaryId(dataDictionaryId);
		
		entityOperator.update(entity);
		DataRelationModelDto newDto = BeanConverter.convert(entity, DTO_CLASS);
		
		List<String> additionalDpdList = dto.getAdditionalDpdNameList();
		
		if(additionalDpdList != null) { 
			additionsLogic.deleteObjects(userDto, drmId);
			List<DataRelationModelAdditionDto> additionDtos = additionsLogic.createObjects(
					userDto, drmId, 
					entity.getTargetDcmId(), additionalDpdList);
			setAdditions(newDto, additionDtos);
			
		}else{
			List<DataRelationModelAdditionDto> additionDtos = additionsLogic.read(userDto, drmId, dto.getTargetDcmId());
			setAdditions(newDto, additionDtos);
		}
		
		List<String> dpdNameList = dto.getDpdNameList();
		List<String> targetDpdNameList = dto.getTargetDpdNameList();
		
		if(dpdNameList != null || targetDpdNameList != null) {
			if(dpdNameList == null) {
				dpdNameList = getDpdNameList(userDto, drmId, entity.getDcmId(), entity.getTargetDcmId());
			}
			
			if(targetDpdNameList == null){
				targetDpdNameList = getTargetDpdNameList(userDto, drmId, entity.getDcmId(), entity.getTargetDcmId());
			}
			
			itemsLogic.deleteObjects(userDto, drmId);
			
			List<DataRelationModelItemDto> itemDtos = itemsLogic.createObjects(
					userDto, drmId, 
					entity.getDcmId(), dpdNameList, 
					entity.getTargetDcmId(), targetDpdNameList);
			setItems(newDto, itemDtos);
		}else{
			List<DataRelationModelItemDto> itemDtos = itemsLogic.read(userDto, drmId, entity.getDcmId(), entity.getTargetDcmId());
			setItems(newDto, itemDtos);
		}
		
		return newDto;
	}
	
	/**
	 * Delete data.
	 * 
	 * @param userDto the user DTO
	 * @param dataDictionaryId the Data Dictionary ID
	 * @param drmId the Data Relation Model (DRM) ID
	 * @throws LogicException operation error in logic
	 */
	public void delete(UserDto userDto, String dataDictionaryId, String drmId) throws LogicException {
		assertArguments(userDto, dataDictionaryId, drmId);

		accessController.validateDataDictionaryOwner(userDto, dataDictionaryId);
		
		DataRelationModelEntity entity = readEntityForcely(userDto, drmId);
		
		entityOperator.delete(entity);
	}
	
	/**
	 * Read data.
	 * 
	 * @param userDto the user DTO
	 * @param dataDictionaryId the Data Dictionary ID
	 * @param drmId the Data Relation Model (DRM) ID
	 * @return the data
	 * @throws LogicException operation error in logic
	 */
	public DataRelationModelDto read(UserDto userDto, String dataDictionaryId, String drmId) throws LogicException {
		assertArguments(userDto, dataDictionaryId, drmId);

		accessController.validateDataDictionaryReadPermission(userDto, dataDictionaryId);
		
		DataRelationModelEntity entity = readEntityForcely(userDto, drmId);
		DataRelationModelDto dto = BeanConverter.convert(entity, DTO_CLASS);
		
		List<DataRelationModelItemDto> itemDtos = itemsLogic.read(userDto, dto.getDrmId(), dto.getDcmId(), dto.getTargetDcmId());
		setItems(dto, itemDtos);
		
		List<DataRelationModelAdditionDto> additionDtos = additionsLogic.read(userDto, dto.getDrmId(), dto.getTargetDcmId());
		setAdditions(dto, additionDtos);

		return dto;
	}
	
	/**
	 * Read data.
	 * 
	 * @param userDto the user DTO
	 * @param dataDictionaryId the Data Dictionary ID
	 * @return the data list
	 * @throws LogicException operation error in logic
	 */
	public List<DataRelationModelDto> read(UserDto userDto, String dataDictionaryId) throws LogicException {
		assertArguments(userDto, dataDictionaryId);
		
		accessController.validateDataDictionaryReadPermission(userDto, dataDictionaryId);
		
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("dataDictionaryId", dataDictionaryId);
		List<DataRelationModelEntity> entities = entityOperator.findByConditions(ENTITY_CLASS, conditions);
		List<DataRelationModelDto> dtos = BeanConverter.convert(entities, DTO_CLASS);
		
		for (DataRelationModelDto dto : dtos) {
			String drmId = dto.getDrmId();
			String dcmId = dto.getDcmId();
			String targetDcmId = dto.getTargetDcmId();
			
			List<DataRelationModelItemDto> itemDtos = itemsLogic.read(userDto, drmId, dcmId, targetDcmId);
			setItems(dto, itemDtos);
		
			List<DataRelationModelAdditionDto> additionDtos = additionsLogic.read(userDto, dto.getDrmId(), dto.getTargetDcmId());
			setAdditions(dto, additionDtos);
		}
		
		return dtos;
	}
	
	/**
	 * Assert arguments.
	 * 
	 * @param userDto the user DTO
	 * @param dataDictionaryId the Data Dictionary ID
	 * @throws LogicException Assertion failed.
	 */
	private void assertArguments(UserDto userDto, String dataDictionaryId) throws LogicException {
		if (userDto == null) {
			throw new LogicException("userDto is null.", Status.BAD_REQUEST.getStatusCode());
		}
		if (dataDictionaryId == null) {
			throw new LogicException("dataDictionaryId is null.", Status.BAD_REQUEST.getStatusCode());
		}
	}
	
	/**
	 * Assert arguments.
	 * 
	 * @param userDto the user DTO
	 * @param dataDictionaryId the Data Dictionary ID
	 * @param drmId the Data Relation Model (DRM) ID
	 * @throws LogicException Assertion failed.
	 */
	private void assertArguments(UserDto userDto, String dataDictionaryId, String drmId) throws LogicException {
		assertArguments(userDto, dataDictionaryId);
		
		if (drmId == null) {
			throw new LogicException("drmId is null.", Status.BAD_REQUEST.getStatusCode());
		}
	}
	
	/**
	 * Assert arguments.
	 * 
	 * @param dto the data DTO
	 * @throws LogicException Assertion failed.
	 */
	private void assertArguments(DataRelationModelDto dto) throws LogicException {
		if (dto == null) {
			throw new LogicException("Invalid data.", Status.BAD_REQUEST.getStatusCode());
		}
		if(dto.getDcmId() == null) {
			throw new LogicException("dcmId is null.", Status.BAD_REQUEST.getStatusCode());			
		}
		
		if(dto.getTargetDcmId() == null) {
			throw new LogicException("targetDcmId is null.", Status.BAD_REQUEST.getStatusCode());			
		}
		
		if(dto.getDpdNameList() == null) {
			throw new LogicException("dpdNameList is null.", Status.BAD_REQUEST.getStatusCode());
		}
		
		if(dto.getTargetDpdNameList() == null) {
			throw new LogicException("targetDpdNameList is null.", Status.BAD_REQUEST.getStatusCode());
		}
		
		if(dto.getAdditionalDpdNameList() == null) {
			throw new LogicException("getAdditionalDpdNameList is null.", Status.BAD_REQUEST.getStatusCode());
		}
	}
	
	/**
	 * obtain entity.
	 * If entity not found, an exception occurs.
	 * 
	 * @param userDto the user DTO
	 * @param drmId the Data Relation Model (DRM) ID
	 * @return the entity
	 * @throws LogicException operation error in logic
	 */
	private DataRelationModelEntity readEntityForcely(UserDto userDto, String drmId) throws LogicException {
		DataRelationModelEntity entity = entityOperator.selectByPk(ENTITY_CLASS, drmId);
		if (entity == null) {
			throw new LogicException(String.format("Data not found.: (drmId,)=(%s,)", drmId), Status.NOT_FOUND.getStatusCode());	
		}
		return entity;
	}

	/**
	 * Set items to DTO.
	 * @param dto the DTO
	 * @param itemDtos the items
	 */
	private void setItems(DataRelationModelDto dto, List<DataRelationModelItemDto> itemDtos) {
		List<String> dpdNameList = new ArrayList<>();
		List<String> targetDpdNameList = new ArrayList<>();
		for (DataRelationModelItemDto itemDto : itemDtos) {
			dpdNameList.add(itemDto.getDpd().getDpdName());
			targetDpdNameList.add(itemDto.getTargetDpd().getDpdName());
		}
		dto.setDpdNameList(dpdNameList);
		dto.setTargetDpdNameList(targetDpdNameList);
	}

	/**
	 * Set additions to DTO
	 * @param dto the DTO
	 * @param additionDtos additions
	 */
	private void setAdditions(DataRelationModelDto dto, List<DataRelationModelAdditionDto> additionDtos) {
		List<String> additionalDpdNameList = new ArrayList<>();
		for (DataRelationModelAdditionDto additionDto : additionDtos) {
			additionalDpdNameList.add(additionDto.getAdditionalDpd().getDpdName());
		}
		dto.setAdditionalDpdNameList(additionalDpdNameList);

	}
	
	private List<String> getDpdNameList(UserDto userDto, String drmId, String dcmId, String targetDcmid) throws LogicException  {
		List<DataRelationModelItemDto> itemDtos = itemsLogic.read(userDto, drmId, dcmId, targetDcmid);
		List<String> dpdNameList = new ArrayList<>();
		for(DataRelationModelItemDto itemDto : itemDtos) {
			dpdNameList.add(itemDto.getDpd().getDpdName());
		}
		
		return dpdNameList;
	}
	
	private List<String> getTargetDpdNameList(UserDto userDto, String drmId, String dcmId, String targetDcmid) throws LogicException {
		List<DataRelationModelItemDto> itemDtos = itemsLogic.read(userDto, drmId, dcmId, targetDcmid);
		List<String> targetDpdList = new ArrayList<>();
		for(DataRelationModelItemDto itemDto : itemDtos) {
			targetDpdList.add(itemDto.getTargetDpd().getDpdName());
		}
		
		return targetDpdList;
	}
}
