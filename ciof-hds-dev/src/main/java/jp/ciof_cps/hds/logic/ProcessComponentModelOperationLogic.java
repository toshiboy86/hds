/**
 * ProcessComponentModelOperationLogic.java
 * (C) 2019. Industrial Value Chain Initiative
 */
package jp.ciof_cps.hds.logic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jp.ciof_cps.hds.dto.ProcessComponentModelOperationDto;
import jp.ciof_cps.hds.dto.UserDto;
import jp.ciof_cps.hds.entity.ProcessComponentModelOperationEntity;

/**
 * This class is the logic class for Process Component Model (PCM) Operation.
 */
@ApplicationScoped
public class ProcessComponentModelOperationLogic {

	static final Log LOG = LogFactory.getLog(ProcessComponentModelOperationLogic.class);

	private static final Class<ProcessComponentModelOperationEntity> ENTITY_CLASS = ProcessComponentModelOperationEntity.class;

	private static final Class<ProcessComponentModelOperationDto> DTO_CLASS = ProcessComponentModelOperationDto.class;

	@Inject
	EntityOperator entityOperator;

	/**
	 * Create data list.
	 *
	 * @param userDto the user DTO
	 * @param dcmId the Data Component Model (DCM) ID
	 * @param dto the data
	 * @return the list of DTO
	 * @throws LogicException operation error in logic
	 */
	public List<ProcessComponentModelOperationDto> create(UserDto userDto, String dcmId, List<ProcessComponentModelOperationDto> dto) throws LogicException {
		List<ProcessComponentModelOperationEntity> entities = BeanConverter.convert(dto, ENTITY_CLASS);

		for(ProcessComponentModelOperationEntity entity : entities) {
			entity.setDcmId(dcmId);
			entityOperator.insert(entity);
		}

		return BeanConverter.convert(entities, DTO_CLASS);
	}

	/**
	 * Delete data.
	 *
	 * @param userDto the user DTO
	 * @param dcmId the Data Component Model (DCM) ID
	 * @throws LogicException operation error in logic
	 */
	public void delete(UserDto userDto, String dcmId) throws LogicException {
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("dcmId", dcmId);
		List<ProcessComponentModelOperationEntity> entities = entityOperator.findByConditions(ENTITY_CLASS, conditions);

		entityOperator.delete(entities);
	}

	/**
	 * Read data list.
	 *
	 * @param userDto the user DTO
	 * @param dcmId the Data Component Model (DCM) ID
	 * @return the data list
	 * @throws LogicException operation error in logic
	 */
	public List<ProcessComponentModelOperationDto> read(UserDto userDto, String dcmId) throws LogicException {
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("dcmId", dcmId);
		List<ProcessComponentModelOperationEntity> entities = entityOperator.findByConditions(ENTITY_CLASS, conditions);

		List<ProcessComponentModelOperationDto> dtos = BeanConverter.convert(entities, DTO_CLASS);
		return dtos;
	}
}
