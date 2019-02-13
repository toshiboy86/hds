/**
 * DictionaryTranslationMapsLogic.java
 * (C) 2019. Industrial Value Chain Initiative
 */
package jp.ciof_cps.hds.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jp.ciof_cps.hds.dto.DictionaryTranslationMapDto;
import jp.ciof_cps.hds.dto.PropertyTranslationMapDto;
import jp.ciof_cps.hds.dto.UserDto;
import jp.ciof_cps.hds.entity.DataPropertyDefinitionEntity;
import jp.ciof_cps.hds.entity.DictionaryTranslationMapEntity;
import jp.ciof_cps.hds.entity.PropertyTranslationMapEntity;

/**
 * This class is the logic class for Dictionary Translation Map (DTM).
 */
@ApplicationScoped
@Transactional
public class DictionaryTranslationMapsLogic {
	static final Log LOG = LogFactory.getLog(DictionaryTranslationMapsLogic.class);
	
	private static final Class<DictionaryTranslationMapDto> DTM_DTO_CLASS = DictionaryTranslationMapDto.class;

	private static final Class<DictionaryTranslationMapEntity> DTM_ENT_CLASS = DictionaryTranslationMapEntity.class;
	
	private static final Class<PropertyTranslationMapEntity> PTM_ENT_CLASS = PropertyTranslationMapEntity.class;

	private static final String[] EXCLUDE_COPY_PROP_NAMES = {
			"dtmId"
			};
	
	private static final String[] EXCLUDE_NULL_COPY_PROP_NAMES = {
			"sourceDataDictionaryId",
			"sourceDcmId",
			"sourceDrmId",
			"destinationDataDictionaryId",
			"destinationDcmId",
			"destinationDrmId",
			"isTentative",
			"ptmList"
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
	 * @param dto the data to create
	 * @return the created data
	 * @throws LogicException operation error in logic
	 */
	public DictionaryTranslationMapDto create(UserDto userDto, DictionaryTranslationMapDto dto) throws LogicException {
		assertArguments(userDto);
		assertArguments(dto);
		if (dto.getPtmList() == null) {
			throw new LogicException("Invalid data. The ptm_list is null.", Status.BAD_REQUEST.getStatusCode());
		}
		
		String dtmId = idGenerator.generateId(this.getClass());
		
		DictionaryTranslationMapEntity entity = BeanConverter.createCopiedInstance(dto, DTM_ENT_CLASS);
		entity.setDtmId(dtmId);
		entity.setOwnerId(userDto.getUserName());
		
		entityOperator.insert(entity);
		
		DictionaryTranslationMapDto newDto = BeanConverter.createCopiedInstance(entity, DTM_DTO_CLASS);
		
		List<PropertyTranslationMapDto> ptmList = dto.getPtmList();
		if (ptmList == null) {
			ptmList = new ArrayList<>();
		}
		for (PropertyTranslationMapDto ptmDto : ptmList) {
			String srcDcmId = dto.getSourceDcmId();
			String dstDcmId = dto.getDestinationDcmId();
			PropertyTranslationMapEntity ptmEntity = constructPropertyTranslationMapEntity(dtmId, srcDcmId, dstDcmId, ptmDto);
			
			entityOperator.insert(ptmEntity);
		}
		newDto.setPtmList(ptmList);
		
		return newDto;
	}

	/**
	 * Update data.
	 * 
	 * @param userDto the user DTO
	 * @param dtmId the Dictionary Translation Map (DTM) ID
	 * @param dto the data to update
	 * @return the updated data
	 * @throws LogicException operation error in logic
	 */
	public DictionaryTranslationMapDto update(UserDto userDto, String dtmId, DictionaryTranslationMapDto dto) throws LogicException {
		assertArguments(userDto, dtmId);
		assertArguments(dto);
		boolean isSourceDataChanged = isSourceDataChanged(dto.getSourceDataDictionaryId(), dto.getSourceDrmId(), dto.getSourceDcmId());
		boolean isDestinationDataChanged = isDestinationDataChanged(dto.getDestinationDataDictionaryId(), dto.getDestinationDrmId(), dto.getDestinationDcmId());
		
		DictionaryTranslationMapEntity entity = readEntityForcely(userDto, dtmId);
		accessController.validateDtmOwner(userDto, dtmId, entity.getOwnerId());

		BeanConverter.copyProperties(dto, entity, DTM_ENT_CLASS, EXCLUDE_COPY_PROP_NAMES, EXCLUDE_NULL_COPY_PROP_NAMES);
		entity.setDtmId(dtmId);

		// Force to update source drm id, when source data are changed.
		if (isSourceDataChanged) {
			entity.setSourceDrmId(dto.getSourceDrmId());
		}
		// Force to update destination drm id, when destination data are changed.
		if (isDestinationDataChanged) {
			entity.setDestinationDrmId(dto.getDestinationDrmId());
		}
		
		entityOperator.update(entity);
		DictionaryTranslationMapDto newDto = BeanConverter.convert(entity, DTM_DTO_CLASS);
		
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("dtmId", dtmId);
		List<PropertyTranslationMapEntity> ptmEntities = entityOperator.findByConditions(PTM_ENT_CLASS, conditions);

		List<PropertyTranslationMapDto> ptmList = dto.getPtmList();
		if (ptmList != null) {
			entityOperator.delete(ptmEntities);
			
			for (PropertyTranslationMapDto ptmDto : ptmList) {
				String srcDcmId = entity.getSourceDcmId();
				String dstDcmId = entity.getDestinationDcmId();
				PropertyTranslationMapEntity ptmEntity = constructPropertyTranslationMapEntity(dtmId, srcDcmId, dstDcmId, ptmDto);
				entityOperator.insert(ptmEntity);
			}
		} else {
			ptmList = readPtmDtos(userDto, dtmId);
		}
		newDto.setPtmList(ptmList);
		return newDto;
	}
	
	/**
	 * Delete data.
	 * 
	 * @param userDto the user DTO
	 * @param dtmId the Dictionary Translation Map (DTM) ID
	 * @throws LogicException operation error in logic
	 */
	public void delete(UserDto userDto, String dtmId) throws LogicException {
		assertArguments(userDto, dtmId);
		
		DictionaryTranslationMapEntity entity = readEntityForcely(userDto, dtmId);
		accessController.validateDtmOwner(userDto, dtmId, entity.getOwnerId());
		
		entityOperator.delete(entity);
	}
	
	/**
	 * Read data.
	 * 
	 * @param userDto the user DTO
	 * @param dtmId the Dictionary Translation Map (DTM) ID
	 * @return the data
	 * @throws LogicException operation error in logic
	 */
	public DictionaryTranslationMapDto read(UserDto userDto, String dtmId) throws LogicException {
		assertArguments(userDto, dtmId);
		
		DictionaryTranslationMapEntity entity = readEntityForcely(userDto, dtmId);
		accessController.validateDtmOwner(userDto, dtmId, entity.getOwnerId());
		DictionaryTranslationMapDto dto = BeanConverter.convert(entity, DTM_DTO_CLASS);
		
		List<PropertyTranslationMapDto> ptmList = readPtmDtos(userDto, dtmId);
		dto.setPtmList(ptmList);
		
		return dto;
	}
	
	/**
	 * Read data.
	 * 
	 * @param userDto the user DTO
	 * @return the data list
	 * @throws LogicException operation error in logic
	 */
	public List<DictionaryTranslationMapDto> read(UserDto userDto) throws LogicException {
		assertArguments(userDto);
		
		Map<String, Object> conditions = new HashMap<>();
		if (!accessController.hasAdministratorRole(userDto)) {
			conditions.put("ownerId", userDto.getUserName());
		}
		List<DictionaryTranslationMapEntity> entities = entityOperator.findByConditions(DTM_ENT_CLASS, conditions);
		List<DictionaryTranslationMapDto> dtos = BeanConverter.convert(entities, DTM_DTO_CLASS);
		
		for (DictionaryTranslationMapDto dto : dtos) {
			List<PropertyTranslationMapDto> ptmList = readPtmDtos(userDto, dto.getDtmId());
			dto.setPtmList(ptmList);
		}
		
		return dtos;
	}

	/**
	 * Construct PropertyTranslationMap entity.
	 * 
	 * @param dtmId the Dictionary Translation Map (DTM) ID
	 * @param srcDcmId the Src Data Component Model (DCM) ID
	 * @param dstDcmId the Dst Data Component Model (DCM) ID
	 * @param ptmDto the Property Translation Map (PTM) Dto
	 * @return the entity
	 * @throws LogicException operation error in logic
	 */
	private PropertyTranslationMapEntity constructPropertyTranslationMapEntity(String dtmId, String srcDcmId, String dstDcmId, PropertyTranslationMapDto ptmDto) throws LogicException {
		DataPropertyDefinitionEntity srcDpdEntity = findDataPropertyDefinitionEntity(srcDcmId, ptmDto.getSourceDpdName());
		if (srcDpdEntity == null) {
			String msg = String.format("sourceDpdName not found.:(dcmId, dpdName)=(%s, %s)", srcDcmId, ptmDto.getSourceDpdName());
			throw new LogicException(msg, Status.BAD_REQUEST.getStatusCode());
		}
		DataPropertyDefinitionEntity dstDpdEntity = findDataPropertyDefinitionEntity(dstDcmId, ptmDto.getDestinationDpdName());
		if (dstDpdEntity == null) {
			String msg = String.format("destinationDpdName not found.:(dcmId, dpdName)=(%s, %s)", dstDcmId, ptmDto.getDestinationDpdName());
			throw new LogicException(msg, Status.BAD_REQUEST.getStatusCode());
		}
		
		PropertyTranslationMapEntity ptmEntity = new PropertyTranslationMapEntity();
		ptmEntity.setDtmId(dtmId);
		ptmEntity.setSourceDpdId(srcDpdEntity.getDpdId());
		ptmEntity.setDestinationDpdId(dstDpdEntity.getDpdId());
		return ptmEntity;
	}
	
	/**
	 * Find DataPropertyDefinition entity.
	 * If entity not found, returns null.
	 * 
	 * @param dcmId the Data Component Model (DCM) ID
	 * @param dpdName the Data Property Definition (DPD) Name
	 * @return the entity
	 * @throws LogicException operation error
	 */
	private DataPropertyDefinitionEntity findDataPropertyDefinitionEntity(String dcmId, String dpdName) throws LogicException {
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("dcmId", dcmId);
		conditions.put("dpdName", dpdName);
		List<DataPropertyDefinitionEntity> dpdEntities = entityOperator.findByConditions(DataPropertyDefinitionEntity.class, conditions);
		DataPropertyDefinitionEntity entity = null;
		if (dpdEntities.size() == 1) {
			entity = dpdEntities.get(0);
		}
		return entity;
	}

	/**
	 * Find PropertyTranslationMap entities, and then create the DTO list.
	 * 
	 * @param userDto the userDto
	 * @param dtmId the Dictionary Translation Map (DTM) ID
	 * @return the DTO list
	 * @throws LogicException operation error
	 */
	private List<PropertyTranslationMapDto> readPtmDtos(UserDto userDto, String dtmId) throws LogicException {
		List<PropertyTranslationMapDto> ptmList = new ArrayList<>();
		
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("dtmId", dtmId);
		List<PropertyTranslationMapEntity> ptmEntities = entityOperator.findByConditions(PTM_ENT_CLASS, conditions);
		for (PropertyTranslationMapEntity ptmEntity : ptmEntities) {
			PropertyTranslationMapDto ptmDto = toDto(ptmEntity);
			ptmList.add(ptmDto);
		}
		return ptmList;
	}

	/**
	 * Convert an entity to a DTO.
	 * 
	 * @param ptmEntity the Property Translation Map (PTM) Entity
	 * @return the DTO operation error
	 * @throws LogicException operation error
	 */
	private PropertyTranslationMapDto toDto(PropertyTranslationMapEntity ptmEntity) throws LogicException {
		Integer srcDpdId = ptmEntity.getSourceDpdId();
		Integer dstDpdId = ptmEntity.getDestinationDpdId();
		
		DataPropertyDefinitionEntity srcDpdEntity = entityOperator.selectByPk(DataPropertyDefinitionEntity.class, srcDpdId);
		DataPropertyDefinitionEntity dstDpdEntity = entityOperator.selectByPk(DataPropertyDefinitionEntity.class, dstDpdId);
		
		PropertyTranslationMapDto ptmDto = new PropertyTranslationMapDto();
		ptmDto.setSourceDpdName(srcDpdEntity.getDpdName());
		ptmDto.setDestinationDpdName(dstDpdEntity.getDpdName());
		return ptmDto;
	}
	
	/**
	 * Assert arguments.
	 * 
	 * @param userDto the user DTO
	 * @throws LogicException Assertion failed
	 */
	private void assertArguments(UserDto userDto) throws LogicException {
		if (userDto == null) {
			throw new LogicException("userDto is null.", Status.BAD_REQUEST.getStatusCode());
		}
	}
	
	/**
	 * Assert arguments.
	 * 
	 * @param userDto the user DTO
	 * @param dtmId the Dictionary Translation Map (DTM) ID
	 * @throws LogicException Assertion failed
	 */
	private void assertArguments(UserDto userDto, String dtmId) throws LogicException {
		assertArguments(userDto);
		
		if (dtmId == null) {
			throw new LogicException("dtmId is null.", Status.BAD_REQUEST.getStatusCode());
		}
	}
	
	/**
	 * Assert arguments.
	 * 
	 * @param dto the data DTO
	 * @throws LogicException Assertion failed
	 */
	private void assertArguments(DictionaryTranslationMapDto dto) throws LogicException {
		if (dto == null) {
			throw new LogicException("Invalid data.", Status.BAD_REQUEST.getStatusCode());
		}
	}
	
	private boolean isSourceDataChanged(String sourceDataDictionaryId, String sourceDrmId, String sourceDcmId) throws LogicException {		
		boolean isChanged = false;
		List<String> sourceIdList = new ArrayList<>(); 
		
		sourceIdList.add(sourceDataDictionaryId);
		sourceIdList.add(sourceDcmId);
		sourceIdList.add(sourceDrmId);
		sourceIdList.removeAll(Collections.singleton(null));
		
		if (sourceIdList.size() > 0) {
			if(sourceDataDictionaryId == null) {
				throw new LogicException("Data not found.: (sourceDataDictionaryId,)=(null,)", Status.BAD_REQUEST.getStatusCode());	
			}
			if(sourceDrmId == null) {
				LOG.info("Source data is changed, and sourceDrmId is null.");
			}
			if(sourceDcmId == null) {
				throw new LogicException("Data not found.: (sourceDcmId,)=(null,)", Status.BAD_REQUEST.getStatusCode());	
			}
			isChanged = true;
		}
		return isChanged;
	}

	private boolean isDestinationDataChanged(String destinationDataDictionaryId, String destinationDrmId, String destinationDcmId) throws LogicException {		
		boolean isChanged = false;
		List<String> destinationIdList = new ArrayList<>();	
		destinationIdList.add(destinationDataDictionaryId);
		destinationIdList.add(destinationDrmId);		
		destinationIdList.add(destinationDcmId);
		destinationIdList.removeAll(Collections.singleton(null));
		
		if(destinationIdList.size() > 0) {
			if(destinationDataDictionaryId == null) {
				throw new LogicException("Data not found.: (destinationDataDictionaryId,)=(null,)", Status.BAD_REQUEST.getStatusCode());	
			}
			if(destinationDrmId == null) {
				LOG.info("Destination data is changed, and destinationDrmId is null. destinationDrmId=null is acceptable.");
			}
			if(destinationDcmId == null) {
				throw new LogicException("Data not found.: (destinationDcmId,)=(null,)", Status.BAD_REQUEST.getStatusCode());	
			}
			isChanged = true;
		}
		return isChanged;
	}	
	/**
	 * Obtain entity.
	 * If entity not found, an exception occurs.
	 * 
	 * @param userDto the user DTO
	 * @param dtmId the Dictionary Translation Map (DTM) ID
	 * @return the entity
	 * @throws LogicException operation error in logic
	 */
	private DictionaryTranslationMapEntity readEntityForcely(UserDto userDto, String dtmId) throws LogicException {
		DictionaryTranslationMapEntity entity = entityOperator.selectByPk(DTM_ENT_CLASS, dtmId);
		if (entity == null) {
			throw new LogicException(String.format("Data not found.: (dtmId,)=(%s,)", dtmId), Status.NOT_FOUND.getStatusCode());	
		}
		return entity;
	}
}
