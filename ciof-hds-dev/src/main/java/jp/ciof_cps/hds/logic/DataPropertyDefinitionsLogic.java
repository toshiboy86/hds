/**
 * DataPropertyDefinitionsLogic.java
 * (C) 2019. Industrial Value Chain Initiative
 */
package jp.ciof_cps.hds.logic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jp.ciof_cps.hds.dto.DataPropertyDefinitionDto;
import jp.ciof_cps.hds.dto.UserDto;
import jp.ciof_cps.hds.entity.DataPropertyDefinitionEntity;

/**
 * This class is the logic class for Data Property Definition (DPD).
 */
@ApplicationScoped
public class DataPropertyDefinitionsLogic {
	static final Log LOG = LogFactory.getLog(DataPropertyDefinitionsLogic.class);
	
	private static final Class<DataPropertyDefinitionEntity> ENTITY_CLASS = DataPropertyDefinitionEntity.class;
	
	private static final Class<DataPropertyDefinitionDto> DTO_CLASS = DataPropertyDefinitionDto.class;
	
	@Inject
	EntityOperator entityOperator;
	
	/**
	 * Create data.
	 * 
	 * @param userDto the user DTO
	 * @param dcmId the Data Component Model (DCM) ID
	 * @param dto the data to create
	 * @return the created data
	 * @throws LogicException operation error in logic
	 */
	public DataPropertyDefinitionDto create(UserDto userDto, String dcmId, DataPropertyDefinitionDto dto) throws LogicException {
		assertArguments(userDto, dcmId);
		assertArguments(dto);
		
		DataPropertyDefinitionEntity entity = BeanConverter.convert(dto, ENTITY_CLASS);
		entity.setDcmId(dcmId);
		entityOperator.insert(entity);
		DataPropertyDefinitionDto newDto = BeanConverter.convert(entity, DTO_CLASS);
		return newDto;
	}
	
	/**
	 * Update data.
	 * 
	 * @param userDto the user DTO
	 * @param dcmId the Data Component Model (DCM) ID
	 * @param dpdName the Data Property Definition (DPD) Name
	 * @param dto the data to update
	 * @return the updated data
	 * @throws LogicException operation error in logic
	 */
	public DataPropertyDefinitionDto update(UserDto userDto, String dcmId, String dpdName, DataPropertyDefinitionDto dto) throws LogicException {
		assertArguments(userDto, dcmId, dpdName);
		assertArguments(dto);
		
		DataPropertyDefinitionEntity entity = selectEntityByUnique(userDto, dcmId, dpdName);
		BeanConverter.copyProperties(dto, entity, ENTITY_CLASS);
		entity.setDcmId(dcmId);
		entity.setDpdName(dpdName);
		
		entityOperator.update(entity);
		
		DataPropertyDefinitionDto newDto = BeanConverter.convert(entity, DTO_CLASS);
		return newDto;
	}
	
	/**
	 * Delete data.
	 * 
	 * @param userDto the user DTO
	 * @param dcmId the Data Component Model (DCM) ID
	 * @param dpdName the Data Property Definition (DPD) Name
	 * @throws LogicException operation error in logic
	 */
	public void delete(UserDto userDto, String dcmId, String dpdName) throws LogicException {
		assertArguments(userDto, dcmId, dpdName);
		
		DataPropertyDefinitionEntity entity = selectEntityByUnique(userDto, dcmId, dpdName);
		
		entityOperator.delete(entity);
	}

	/**
	 * Read data.
	 * 
	 * @param userDto the user DTO
	 * @param dcmId the Data Component Model (DCM) ID
	 * @param dpdName the Data Property Definition (DPD) Name
	 * @return the data
	 * @throws LogicException operation error in logic
	 */
	public DataPropertyDefinitionDto read(UserDto userDto, String dcmId, String dpdName) throws LogicException {
		assertArguments(userDto, dcmId, dpdName);
		DataPropertyDefinitionEntity entity = selectEntityByUnique(userDto, dcmId, dpdName);
		return BeanConverter.convert(entity, DTO_CLASS);
	}
	
	/**
	 * Read data list.
	 * 
	 * @param userDto the user DTO
	 * @param dcmId the Data Component Model (DCM) ID
	 * @return the data list
	 * @throws LogicException operation error in logic
	 */
	public List<DataPropertyDefinitionDto> read(UserDto userDto, String dcmId) throws LogicException {
		assertArguments(userDto, dcmId);
		
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("dcmId", dcmId);
		List<DataPropertyDefinitionEntity> entities = entityOperator.findByConditions(ENTITY_CLASS, conditions);

		return BeanConverter.convert(entities, DTO_CLASS);
	}

	/**
	 * Assert arguments.
	 * 
	 * @param userDto the user DTO
	 * @param dcmId the Data Component Model (DCM) ID
	 * @param dpdName the Data Property Definition (DPD) Name
	 * @throws LogicException Assertion failed.
	 */
	private void assertArguments(UserDto userDto, String dcmId, String dpdName) throws LogicException {
		assertArguments(userDto, dcmId);
		
		if (dpdName == null) {
			throw new LogicException("dpdName is null.", Status.BAD_REQUEST.getStatusCode());
		}
	}

	/**
	 * Assert arguments.
	 * 
	 * @param userDto the user DTO
	 * @param dcmId the Data Component Model (DCM) ID
	 * @throws LogicException Assertion failed.
	 */
	private void assertArguments(UserDto userDto, String dcmId) throws LogicException {
		if (userDto == null) {
			throw new LogicException("userDto is null.", Status.BAD_REQUEST.getStatusCode());
		}

		if (dcmId == null) {
			throw new LogicException("dcmId is null.", Status.BAD_REQUEST.getStatusCode());
		}
	}

	/**
	 * Assert arguments.
	 * 
	 * @param dto the data DTO
	 * @throws LogicException Assertion failed.
	 */
	private void assertArguments(DataPropertyDefinitionDto dto) throws LogicException {
		if (dto == null) {
			throw new LogicException("Invalid data.", Status.BAD_REQUEST.getStatusCode());
		}
	}

	/**
	 * find an entity by unique keys.
	 * If entity not found, an exception occurs.
	 * 
	 * @param userDto the user DTO
	 * @param dcmId the Data Component Model (DCM) ID
	 * @param dpdName the Data Property Definition (DPD) Name
	 * @return the entity
	 * @throws LogicException operation error in logic
	 */
	DataPropertyDefinitionEntity selectEntityByUnique(UserDto userDto, String dcmId, String dpdName) throws LogicException {
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("dcmId", dcmId);
		conditions.put("dpdName", dpdName);
		List<DataPropertyDefinitionEntity> entities = entityOperator.findByConditions(ENTITY_CLASS, conditions);
		if (entities.size() == 0) {
			throw new LogicException(String.format("Data not found.: (dcmId, dpdName)=(%s, %s)", dcmId, dpdName), Status.NOT_FOUND.getStatusCode());	
		}
		
		DataPropertyDefinitionEntity entity = entities.get(0);
		return entity;
	}
}
