/**
 * DataDictionariesLogic.java
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

import jp.ciof_cps.hds.dto.DataComponentModelDto;
import jp.ciof_cps.hds.dto.DataDictionaryDto;
import jp.ciof_cps.hds.dto.DataRelationModelDto;
import jp.ciof_cps.hds.dto.UserDto;
import jp.ciof_cps.hds.entity.DataDictionaryEntity;

/**
 * This class is the logic class for Data Dictionary.
 */
@ApplicationScoped
@Transactional
public class DataDictionariesLogic {
	static final Log LOG = LogFactory.getLog(DataDictionariesLogic.class);

	private static final Class<DataDictionaryDto> DATA_DICTIONARY_DTO_CLASS = DataDictionaryDto.class;

	private static final Class<DataDictionaryEntity> DATA_DICTIONARY_ENT_CLASS = DataDictionaryEntity.class;
	
	private static final String[] EXCLUDE_COPY_PROP_NAMES = {
			"dataDictionaryId",
			"version",
			"ownerId",
			"previousDataDictionaryId",
			"dcmIdList",
			"drmIdList"
			};
	
	
	private static final String[] EXCLUDE_NULL_COPY_PROP_NAMES = {
			"dataDictionaryName",
			"dataDictionaryDescription",
			"dataDictionaryType"
			};
	
	@Inject
	IdGenerator idGenerator;
	
	@Inject
	EntityOperator entityOperator;
	
	@Inject
	DataComponentModelsLogic dcmLogic;
	
	@Inject
	DataRelationModelsLogic drmLogic;
	
	@Inject
	HdsAccessController accessController;
	
	/**
	 * Create data.
	 * 
	 * @param userDto ehe user DTO
	 * @param dto the data
	 * @return the create data
	 * @throws LogicException operation error in logic 
	 */
	public DataDictionaryDto create(UserDto userDto, DataDictionaryDto dto) throws LogicException {
		assertArguments(dto);
		
		DataDictionaryDto newDto = null;
		Integer version = 1;
		DataDictionaryEntity previousEntity = null;
		
		String dataDictionaryId = idGenerator.generateId(this.getClass());
		dto.setDataDictionaryId(dataDictionaryId);
		
		String previousDataDictionaryId = dto.getPreviousDataDictionaryId();
		
		DataDictionaryEntity entity = BeanConverter.createCopiedInstance(dto, DATA_DICTIONARY_ENT_CLASS);
		
		entity.setOwnerId(userDto.getUserName());
		
		if(previousDataDictionaryId == null) {
			entity.setVersion(version);
			
		}else{
			previousEntity = entityOperator.selectByPk(DATA_DICTIONARY_ENT_CLASS, previousDataDictionaryId);
			
			if(previousEntity != null) {
				version = previousEntity.getVersion();
				entity.setVersion(++version);
			}else {
				throw new LogicException(String.format("Data not found.: (previousDataDictionaryId,)=(%s,)", previousDataDictionaryId), Status.NOT_FOUND.getStatusCode());
			}
		}
		
		entityOperator.insert(entity);
		newDto = BeanConverter.createCopiedInstance(entity, DATA_DICTIONARY_DTO_CLASS);

		return newDto;
	}

	/**
	 * Update data.
	 * 
	 * @param userDto the user DTO
	 * @param dataDictionaryId the Data Dictionary ID
	 * @param dto the data
	 * @return the create data
	 * @throws LogicException operation error in logic 
	 */
	public DataDictionaryDto update(UserDto userDto, String dataDictionaryId, DataDictionaryDto dto) throws LogicException {
		DataDictionaryEntity entity = null;
		if (dataDictionaryId != null && !dataDictionaryId.equals(dto.getDataDictionaryId())) {
			dto.setDataDictionaryId(dataDictionaryId);
		}
		
		if(dataDictionaryId != null){
			entity = entityOperator.selectByPk(DATA_DICTIONARY_ENT_CLASS, dataDictionaryId);
		}
	
		if (entity == null) {
			throw new LogicException("Failed to update DataDictionary. DataDictionary not found.: dataDictionaryId=" + dataDictionaryId, Status.NOT_FOUND.getStatusCode());
		}
		
		accessController.validateDictionaryOwner(userDto, dataDictionaryId, entity.getOwnerId());
		
		BeanConverter.copyProperties(dto, entity, DATA_DICTIONARY_ENT_CLASS, EXCLUDE_COPY_PROP_NAMES, EXCLUDE_NULL_COPY_PROP_NAMES);
		
		entityOperator.update(entity);
		DataDictionaryDto newDto = BeanConverter.convert(entity, DATA_DICTIONARY_DTO_CLASS);
		List<String> dcmIdList = new ArrayList<>();
		List<String> drmIdList = new ArrayList<>();
		
		List<DataComponentModelDto> dcmList = dcmLogic.read(userDto, dto.getDataDictionaryId());
		for (DataComponentModelDto dcmDto : dcmList) {
			 dcmIdList.add(dcmDto.getDcmId());
		}
		newDto.setDcmIdList(dcmIdList);
		
		List<DataRelationModelDto> drmDtos = drmLogic.read(userDto, dto.getDataDictionaryId());
		for (DataRelationModelDto drmDto : drmDtos) {
			 drmIdList.add(drmDto.getDrmId());
		}
		newDto.setDrmIdList(drmIdList);
		
		return newDto;
	}
	
	/**
	 * Delete data.
	 * 
	 * @param userDto the user DTO
	 * @param dataDictionaryId the Data Dictionary ID
	 * @throws LogicException operation error in logic 
	 */
	public void delete(UserDto userDto, String dataDictionaryId) throws LogicException {
		DataDictionaryEntity entity = null;
		
		if (dataDictionaryId != null) {
			entity = entityOperator.selectByPk(DATA_DICTIONARY_ENT_CLASS, dataDictionaryId);
		}
		
		if (entity == null) {
			throw new LogicException("Failed to delete DataDictionary. DataDictionary not found.: dataDictionaryId=" + dataDictionaryId, Status.NOT_FOUND.getStatusCode());
		}
		accessController.validateDictionaryOwner(userDto, dataDictionaryId, entity.getOwnerId());
		
		entityOperator.delete(entity);
	}

	/**
	 * Read data.
	 * 
	 * @param userDto the user DTO
	 * @param dataDictionaryId the Data Dictionary ID
	 * @return the data
	 * @throws LogicException operation error in logic 
	 */
	public DataDictionaryDto read(UserDto userDto, String dataDictionaryId) throws LogicException {
		DataDictionaryDto dto = null;
		List<String> dcmList = new ArrayList<>();
		List<String> drmList = new ArrayList<>();
		
		DataDictionaryEntity entity = entityOperator.selectByPk(DATA_DICTIONARY_ENT_CLASS, dataDictionaryId);
		dto = BeanConverter.convert(entity, DATA_DICTIONARY_DTO_CLASS);
		if (dto == null) {
			throw new LogicException("DataDictionary not found.: dataDictionaryId=" + dataDictionaryId, Status.NOT_FOUND.getStatusCode());
		}
		accessController.validateDictionaryReadPermission(userDto, dataDictionaryId, entity.getOwnerId(), entity.getDataDictionaryType());
		
		List<DataComponentModelDto> dcmDtos = dcmLogic.read(userDto, dto.getDataDictionaryId());
		for (DataComponentModelDto dcmDto : dcmDtos) {
			 dcmList.add(dcmDto.getDcmId());
		}
		dto.setDcmIdList(dcmList);
		
		List<DataRelationModelDto> drmDtos = drmLogic.read(userDto, dto.getDataDictionaryId());
		for (DataRelationModelDto drmDto : drmDtos) {
			 drmList.add(drmDto.getDrmId());
		}
		dto.setDrmIdList(drmList);
		
		return dto;
	}
	
	/**
	 * Read data list.
	 * 
	 * @param userDto the user DTO
	 * @param dataDictionaryType the Data Dictionary ID
	 * @return the data list
	 * @throws LogicException operation error in logic 
	 */
	public List<DataDictionaryDto> readList(UserDto userDto, String dataDictionaryType) throws LogicException {
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("dataDictionaryType", dataDictionaryType);
		if (accessController.isRequiredFilterByOwner(userDto, dataDictionaryType)) {
			conditions.put("ownerId", userDto.getUserName());
		}
		List<DataDictionaryEntity> entities = entityOperator.findByConditions(DATA_DICTIONARY_ENT_CLASS, conditions);
		List<DataDictionaryDto> dtos = BeanConverter.convert(entities, DATA_DICTIONARY_DTO_CLASS);
		
		for (DataDictionaryDto dto : dtos) {
			List<String> dcmList = new ArrayList<>();
			List<DataComponentModelDto> dcmDtos = dcmLogic.read(userDto, dto.getDataDictionaryId());
			
			for (DataComponentModelDto dcmDto : dcmDtos) {
				 dcmList.add(dcmDto.getDcmId());
			}
			dto.setDcmIdList(dcmList);
		}
		
		for (DataDictionaryDto dto : dtos) {
			List<String> drmList = new ArrayList<>();
			List<DataRelationModelDto> drmDtos = drmLogic.read(userDto, dto.getDataDictionaryId());
			
			for (DataRelationModelDto drmDto : drmDtos) {
				 drmList.add(drmDto.getDrmId());
			}
			dto.setDrmIdList(drmList);
		}
		
		return dtos;
	}
	
	private void assertArguments(DataDictionaryDto dto) throws LogicException {
		if (dto == null) {
			throw new LogicException("Invalid data.", Status.BAD_REQUEST.getStatusCode());
		}
		String dataDictionaryName = dto.getDataDictionaryName();
		String dataDictionaryType = dto.getDataDictionaryType();
		String dataDictionaryDescription = dto.getDataDictionaryDescription();

		if (dataDictionaryName == null) {
			throw new LogicException("dataDictionaryName is null.", Status.BAD_REQUEST.getStatusCode());
		}
		
		if (dataDictionaryType == null) {
			throw new LogicException("dataDictionaryType is null.", Status.BAD_REQUEST.getStatusCode());
		}
		
		if(dataDictionaryDescription == null) { 
			throw new LogicException("dataDictionaryDescription is null.", Status.BAD_REQUEST.getStatusCode());
		}
	}
}
