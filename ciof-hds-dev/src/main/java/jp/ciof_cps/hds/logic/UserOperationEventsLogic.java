/**
 * UserOperationEventsLogic.java
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

import jp.ciof_cps.hds.dto.UserDto;
import jp.ciof_cps.hds.dto.UserOperationEventDto;
import jp.ciof_cps.hds.entity.UserOperationEventEntity;

/**
 * This class is the logic class for User Operation Event.
 */
@ApplicationScoped
public class UserOperationEventsLogic {

	static final Log LOG = LogFactory.getLog(RegularEventsLogic.class);

	private static final Class<UserOperationEventEntity> ENTITY_CLASS = UserOperationEventEntity.class;

	private static final Class<UserOperationEventDto> DTO_CLASS = UserOperationEventDto.class;

	@Inject
	EntityOperator entityOperator;

	/**
	 * Create data.
	 *
	 * @param userDto the user DTO
	 * @param ecmId the Event and Condition Model (ECM) ID
	 * @param dto the data
	 * @return the list of DTO
	 * @throws LogicException operation error in logic
	 */
	public List<UserOperationEventDto> create(UserDto userDto, String ecmId, List<UserOperationEventDto> dto) throws LogicException {
		List<UserOperationEventEntity> entities = BeanConverter.convert(dto, ENTITY_CLASS);

		for(UserOperationEventEntity entity : entities) {
			entity.setEcmId(ecmId);
			entityOperator.insert(entity);
		}

		return BeanConverter.convert(entities, DTO_CLASS);
	}

	/**
	 * Delete data.
	 *
	 * @param userDto the user DTO
	 * @param ecmId the Event and Condition Model (ECM) ID
	 * @throws LogicException operation error in logic
	 */
	public void delete(UserDto userDto, String ecmId) throws LogicException {
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("ecmId", ecmId);
		List<UserOperationEventEntity> entities = entityOperator.findByConditions(ENTITY_CLASS, conditions);

		entityOperator.delete(entities);
	}

	/**
	 * Read data list.
	 *
	 * @param userDto the user DTO
	 * @param ecmId the Event and Condition Model (ECM) ID
	 * @return the data list
	 * @throws LogicException operation error in logic
	 */
	public List<UserOperationEventDto> read(UserDto userDto, String ecmId) throws LogicException {
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("ecmId", ecmId);
		List<UserOperationEventEntity> entities = entityOperator.findByConditions(ENTITY_CLASS, conditions);
		List<UserOperationEventDto> dtos = BeanConverter.convert(entities, DTO_CLASS);

		return dtos;
	}
}
