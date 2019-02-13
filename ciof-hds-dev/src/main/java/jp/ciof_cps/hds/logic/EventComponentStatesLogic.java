/**
 * EventComponentStatesLogic.java
 * (C) 2019. Industrial Value Chain Initiative
 */
package jp.ciof_cps.hds.logic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jp.ciof_cps.hds.dto.EventComponentStateDto;
import jp.ciof_cps.hds.dto.UserDto;
import jp.ciof_cps.hds.entity.EventComponentStateEntity;

/**
 * This class is the logic class for Event Component State.
 *
 */
@ApplicationScoped
@Transactional
public class EventComponentStatesLogic {

	static final Log LOG = LogFactory.getLog(EventComponentStatesLogic.class);

	private static final Class<EventComponentStateEntity> ENTITY_CLASS = EventComponentStateEntity.class;

	private static final Class<EventComponentStateDto> DTO_CLASS = EventComponentStateDto.class;

	@Inject
	EntityOperator entityOperator;

	/**
	 * Create data.
	 *
	 * @param userDto the use DTO
	 * @param ecmId the Event and Condition Model (ECM) ID
	 * @param dto the data
	 * @return the list of DTO
	 * @throws LogicException operation error in logic
	 */
	public List<EventComponentStateDto> create(UserDto userDto, String ecmId, List<EventComponentStateDto> dto) throws LogicException {
		List<EventComponentStateEntity> entities = BeanConverter.convert(dto, ENTITY_CLASS);

		for(EventComponentStateEntity entity : entities) {
			entity.setEcmId(ecmId);

			entityOperator.insert(entity);
		}

		return BeanConverter.convert(entities, DTO_CLASS);
	}

	/**
	 * Delete data.
	 *
	 * @param userDto the use DTO
	 * @param ecmId the Event and Condition Model (ECM) ID
	 * @throws LogicException operation error in logic
	 */
	public void delete(UserDto userDto, String ecmId) throws LogicException {
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("ecmId", ecmId);
		List<EventComponentStateEntity> entities = entityOperator.findByConditions(ENTITY_CLASS, conditions);

		entityOperator.delete(entities);
	}

	/**
	 * Read data list.
	 *
	 * @param userDto the use DTO
	 * @param ecmId the Event and Condition Model (ECM) ID
	 * @return the datas
	 * @throws LogicException operation error in logic
	 */
	public List<EventComponentStateDto> read(UserDto userDto, String ecmId) throws LogicException {
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("ecmId", ecmId);
		List<EventComponentStateEntity> entities = entityOperator.findByConditions(ENTITY_CLASS, conditions);
		List<EventComponentStateDto> dtos = BeanConverter.convert(entities, DTO_CLASS);

		return dtos;
	}
}
