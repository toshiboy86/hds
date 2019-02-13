/**
 * RegularEventsLogic.java
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

import jp.ciof_cps.hds.dto.RegularEventDto;
import jp.ciof_cps.hds.dto.UserDto;
import jp.ciof_cps.hds.entity.RegularEventEntity;

/**
 * This class is the logic class for Regular Event.
 */
@ApplicationScoped
public class RegularEventsLogic {

	static final Log LOG = LogFactory.getLog(RegularEventsLogic.class);

	private static final Class<RegularEventEntity> ENTITY_CLASS = RegularEventEntity.class;

	private static final Class<RegularEventDto> DTO_CLASS = RegularEventDto.class;

	@Inject
	EntityOperator entityOperator;

	/**
	 * Create data list.
	 *
	 * @param userDto the use DTO
	 * @param ecmId the Event and Condition Model (ECM) ID
	 * @param dto the data
	 * @return the list of DTO
	 * @throws LogicException operation error in logic
	 */
	public List<RegularEventDto> create(UserDto userDto, String ecmId, List<RegularEventDto> dto) throws LogicException {
		List<RegularEventEntity> entities = BeanConverter.convert(dto, ENTITY_CLASS);

		for(RegularEventEntity entity : entities) {
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
		List<RegularEventEntity> entities = entityOperator.findByConditions(ENTITY_CLASS, conditions);

		entityOperator.delete(entities);
	}

	/**
	 * Read data list.
	 *
	 * @param userDto the use DTO
	 * @param ecmId the Event and Condition Model (ECM) ID
	 * @return the data list
	 * @throws LogicException operation error in logic
	 */
	public List<RegularEventDto> read(UserDto userDto, String ecmId) throws LogicException {
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("ecmId", ecmId);
		List<RegularEventEntity> entities = entityOperator.findByConditions(ENTITY_CLASS, conditions);

		List<RegularEventDto> dtos = BeanConverter.convert(entities, DTO_CLASS);
		return dtos;
	}
}
