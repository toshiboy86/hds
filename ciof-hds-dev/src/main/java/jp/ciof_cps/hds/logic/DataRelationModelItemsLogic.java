/**
 * DataRelationModelItemsLogic.java
 * (C) 2019. Industrial Value Chain Initiative
 */
package jp.ciof_cps.hds.logic;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jp.ciof_cps.hds.dto.DataPropertyDefinitionDto;
import jp.ciof_cps.hds.dto.DataRelationModelItemDto;
import jp.ciof_cps.hds.dto.UserDto;
import jp.ciof_cps.hds.entity.DataPropertyDefinitionEntity;
import jp.ciof_cps.hds.entity.DataRelationModelItemEntity;

/**
 * This class is the logic class for Data Relation Model (DRM) Item.
 */
@ApplicationScoped
public class DataRelationModelItemsLogic {
	static final Log LOG = LogFactory.getLog(DataRelationModelItemsLogic.class);
	
	@Inject
	EntityOperator entityOperator;

	@Inject
	DataPropertyDefinitionsLogic dpdLogic;
	
	/**
	 * Create data.
	 * 
	 * @param userDto the user DTO
	 * @param drmId the Data Relation Model (DRM) ID
	 * @param dcmId the Data Component Model (DCM) ID
	 * @param dpdNameList the Data Property Definition (DPD) Name List
	 * @param targetDcmId the Target Data Component Model (DCM) ID
	 * @param targetDpdNameList the Target Data Property Definition (DPD) Name List
	 * @return the created data list
	 * @throws LogicException operation error in logic
	 */
	public List<DataRelationModelItemDto> createObjects(UserDto userDto, String drmId, String dcmId, List<String> dpdNameList, String targetDcmId, List<String> targetDpdNameList) throws LogicException {
		if (dpdNameList.size() != targetDpdNameList.size()) {
			throw new LogicException("the sizes of dpdNameList and targetDpdNameList must be equal.", Status.BAD_REQUEST.getStatusCode());
		}
		
		List<DataRelationModelItemEntity> entities = constructEntities(userDto, drmId, dcmId, dpdNameList, targetDcmId, targetDpdNameList);
		
		entityOperator.insert(entities);
		
		List<DataRelationModelItemDto> dtos = new ArrayList<>();
		for (DataRelationModelItemEntity entity : entities) {
			DataRelationModelItemDto dto = readDto(userDto, dcmId, targetDcmId, entity);
			
			dtos.add(dto);
		}
		return dtos;
	}

	/**
	 * Delete data.
	 * 
	 * @param userDto the user DTO
	 * @param drmId the Data Relation Model (DRM) ID
	 * @throws LogicException operation error in logic
	 */
	public void deleteObjects(UserDto userDto, String drmId) throws LogicException {
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("drmId", drmId);
		List<DataRelationModelItemEntity> entities = entityOperator.findByConditions(DataRelationModelItemEntity.class, conditions);
		
		entityOperator.delete(entities);
	}
	
	/**
	 * Read data.
	 * 
	 * @param userDto the user DTO
	 * @param drmId the Data Relation Model (DRM) ID
	 * @param dcmId the Data Component Model (DCM) ID
	 * @param targetDcmId the Target Data Component Model (DCM) ID
	 * @return the data list
	 * @throws LogicException operation error in logic
	 */
	public List<DataRelationModelItemDto> read(UserDto userDto, String drmId, String dcmId, String targetDcmId) throws LogicException {
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("drmId", drmId);
		List<DataRelationModelItemEntity> entities = entityOperator.findByConditions(DataRelationModelItemEntity.class, conditions);
		
		List<DataRelationModelItemDto> dtos = new ArrayList<>();
		for (DataRelationModelItemEntity entity : entities) {
			DataRelationModelItemDto dto = readDto(userDto, dcmId, targetDcmId, entity);
			dtos.add(dto);
		}
		return dtos;
	}
	
	/**
	 * Construct entities.
	 * 
	 * @param userDto the user DTO
	 * @param drmId the Data Relation Model (DRM) ID
	 * @param dcmId the Data Component Model (DCM) ID
	 * @param dpdNameList the Data Property Definition (DPD) Name List
	 * @param targetDcmId the Target Data Component Model (DCM) ID
	 * @param targetDpdNameList the Target Data Property Definition (DPD) Name List
	 * @return the entity list
	 * @throws LogicException operation error in logic
	 */
	private List<DataRelationModelItemEntity> constructEntities(UserDto userDto, String drmId, String dcmId, List<String> dpdNameList, String targetDcmId, List<String> targetDpdNameList) throws LogicException {
		List<DataRelationModelItemEntity> entities = new ArrayList<>();
		for (int idx = 0; idx < dpdNameList.size(); idx += 1) {
			String dpdName = dpdNameList.get(idx);
			String targetDpdName = targetDpdNameList.get(idx);
			
			DataPropertyDefinitionEntity dpd = dpdLogic.selectEntityByUnique(userDto, dcmId, dpdName);
			DataPropertyDefinitionEntity targetDpd = dpdLogic.selectEntityByUnique(userDto, targetDcmId, targetDpdName);
			
			Integer dpdId = dpd.getDpdId();
			Integer targetDpdId = targetDpd.getDpdId();
			
			DataRelationModelItemEntity entity = new DataRelationModelItemEntity();
			entity.setDrmId(drmId);
			entity.setDpdId(dpdId);
			entity.setTargetDpdId(targetDpdId);
			
			entities.add(entity);
		}
		return entities;
	}

	/**
	 * Read a DTO data from an entity.
	 * 
	 * @param userDto the user DTO
	 * @param dcmId the Data Component Model (DCM) ID
	 * @param targetDcmId the Target Data Component Model (DCM) ID
	 * @param entity the entity
	 * @return the DTO data
	 * @throws LogicException operation error in logic
	 */
	private DataRelationModelItemDto readDto(UserDto userDto, String dcmId, String targetDcmId, DataRelationModelItemEntity entity) throws LogicException {
		DataPropertyDefinitionEntity dpdEntity = entityOperator.selectByPk(DataPropertyDefinitionEntity.class, entity.getDpdId());
		DataPropertyDefinitionEntity targetDpdEntity = entityOperator.selectByPk(DataPropertyDefinitionEntity.class, entity.getTargetDpdId());
		
		DataPropertyDefinitionDto dpd = dpdLogic.read(userDto, dpdEntity.getDcmId(), dpdEntity.getDpdName());
		DataPropertyDefinitionDto targetDpd = dpdLogic.read(userDto, targetDpdEntity.getDcmId(), targetDpdEntity.getDpdName());
		
		DataRelationModelItemDto dto = new DataRelationModelItemDto();
		dto.setDataRelationModelItemId(entity.getDataRelationModelItemId());
		dto.setDrmId(entity.getDrmId());
		dto.setDcmId(dcmId);
		dto.setDpd(dpd);
		dto.setTargetDcmId(targetDcmId);
		dto.setTargetDpd(targetDpd);
		return dto;
	}
}
