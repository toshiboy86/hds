/**
 * DataRelationModelAdditionsLogic.java
 * (C) 2019. Industrial Value Chain Initiative
 */
package jp.ciof_cps.hds.logic;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jp.ciof_cps.hds.dto.DataPropertyDefinitionDto;
import jp.ciof_cps.hds.dto.DataRelationModelAdditionDto;
import jp.ciof_cps.hds.dto.UserDto;
import jp.ciof_cps.hds.entity.DataPropertyDefinitionEntity;
import jp.ciof_cps.hds.entity.DataRelationModelAdditionEntity;

/**
 * This class is the logic class for Data Relation Model (DRM) Addition.
 */
@ApplicationScoped
public class DataRelationModelAdditionsLogic {
	static final Log LOG = LogFactory.getLog(DataRelationModelAdditionsLogic.class);
	
	@Inject
	EntityOperator entityOperator;

	@Inject
	DataPropertyDefinitionsLogic dpdLogic;
	
	/**
	 * Create data.
	 * 
	 * @param userDto the user DTO.
	 * @param drmId the Data Relation Model (DRM) ID
	 * @param targetDcmId the Target Data Component Model (DCM) ID
	 * @param additionalDpdNameList the Additional Data Property Definition (DPD) Name List
	 * @return the created data list
	 * @throws LogicException operation error in logic
	 */
	public List<DataRelationModelAdditionDto> createObjects(UserDto userDto, String drmId, String targetDcmId, List<String> additionalDpdNameList) throws LogicException {
		List<DataRelationModelAdditionEntity> entities = constructEntities(userDto, drmId, targetDcmId, additionalDpdNameList);
		
		entityOperator.insert(entities);
		
		List<DataRelationModelAdditionDto> dtos = new ArrayList<>();
		for (DataRelationModelAdditionEntity entity : entities) {
			DataRelationModelAdditionDto dto = readDto(userDto, targetDcmId, entity);
			
			dtos.add(dto);
		}
		return dtos;
	}

	/**
	 * Delete data.
	 * 
	 * @param userDto the user DTO.
	 * @param drmId the Data Relation Model (DRM) ID
	 * @throws LogicException operation error in logic
	 */
	public void deleteObjects(UserDto userDto, String drmId) throws LogicException {
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("drmId", drmId);
		List<DataRelationModelAdditionEntity> entities = entityOperator.findByConditions(DataRelationModelAdditionEntity.class, conditions);
		
		entityOperator.delete(entities);
	}
	
	/**
	 * Read data.
	 * 
	 * @param userDto the user DTO.
	 * @param drmId the Data Relation Model (DRM) ID
	 * @param targetDcmId the Target Data Component Model (DCM) ID
	 * @return the data list
	 * @throws LogicException operation error in logic
	 */
	public List<DataRelationModelAdditionDto> read(UserDto userDto, String drmId, String targetDcmId) throws LogicException {
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("drmId", drmId);
		List<DataRelationModelAdditionEntity> entities = entityOperator.findByConditions(DataRelationModelAdditionEntity.class, conditions);
		
		List<DataRelationModelAdditionDto> dtos = new ArrayList<>();
		for (DataRelationModelAdditionEntity entity : entities) {
			DataRelationModelAdditionDto dto = readDto(userDto, targetDcmId, entity);
			dtos.add(dto);
		}
		return dtos;
	}
	
	/**
	 * Construct entities.
	 * 
	 * @param userDto the user DTO.
	 * @param drmId the Data Relation Model (DRM) ID
	 * @param targetDcmId the Target Data Component Model (DCM) ID
	 * @param additionalDpdNameList the Additional Data Property Definition (DPD) Name List
	 * @return the entities
	 * @throws LogicException operation error in logic
	 */
	private List<DataRelationModelAdditionEntity> constructEntities(UserDto userDto, String drmId, String targetDcmId, List<String> additionalDpdNameList) throws LogicException {
		List<DataRelationModelAdditionEntity> entities = new ArrayList<>();
		for (String additionalDpdName : additionalDpdNameList) {
			DataPropertyDefinitionEntity additionalDpd = dpdLogic.selectEntityByUnique(userDto, targetDcmId, additionalDpdName);
			
			DataRelationModelAdditionEntity entity = new DataRelationModelAdditionEntity();
			entity.setDrmId(drmId);
			entity.setAdditionalDpdId(additionalDpd.getDpdId());
			
			entities.add(entity);
		}
		return entities;
	}

	/**
	 * Read DTO data from an entity.
	 * 
	 * @param userDto the user DTO.
	 * @param targetDcmId the Target Data Component Model (DCM) ID
	 * @param entity the entity
	 * @return the DTO
	 * @throws LogicException operation error in logic
	 */
	private DataRelationModelAdditionDto readDto(UserDto userDto, String targetDcmId, DataRelationModelAdditionEntity entity) throws LogicException {
		DataPropertyDefinitionEntity additionalDpdEntity = entityOperator.selectByPk(DataPropertyDefinitionEntity.class, entity.getAdditionalDpdId());
		
		DataPropertyDefinitionDto additionalDpd = dpdLogic.read(userDto, additionalDpdEntity.getDcmId(), additionalDpdEntity.getDpdName());
		
		DataRelationModelAdditionDto dto = new DataRelationModelAdditionDto();
		dto.setDataRelationModelAdditionId(entity.getDataRelationModelAdditionId());
		dto.setDrmId(entity.getDrmId());
		dto.setTargetDcmId(targetDcmId);
		dto.setAdditionalDpd(additionalDpd);
		return dto;
	}
}
