/**
 * ProcessEventsLogic.java
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

import jp.ciof_cps.hds.dto.ProcessEventDto;
import jp.ciof_cps.hds.dto.UserDto;
import jp.ciof_cps.hds.entity.ProcessEventEntity;

/**
 * This class is the logic class for Process Event.
 */
@ApplicationScoped
public class ProcessEventsLogic {
	
	static final Log LOG = LogFactory.getLog(ProcessEventsLogic.class);
	
	private static final Class<ProcessEventEntity> ENTITY_CLASS = ProcessEventEntity.class;
	
	private static final Class<ProcessEventDto> DTO_CLASS = ProcessEventDto.class;
	
	@Inject
	EntityOperator entityOperator;
	
	/**
	 * Create data list.
	 * 
	 * @param userDto the use DTO 
	 * @param ecmId the Event and Condition Model (ECM) ID
	 * @param dto the data
	 * @return the list of dto
	 * @throws LogicException operation error in logic 
	 */
	public List<ProcessEventDto> create(UserDto userDto, String ecmId, List<ProcessEventDto> dto) throws LogicException {
	List<ProcessEventEntity> entities = BeanConverter.convert(dto, ENTITY_CLASS);
	
	for(ProcessEventEntity entity : entities) {
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
		List<ProcessEventEntity> entities = entityOperator.findByConditions(ENTITY_CLASS, conditions);
		
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
	public List<ProcessEventDto> read(UserDto userDto, String ecmId) throws LogicException {
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("ecmId", ecmId);
		List<ProcessEventEntity> entities = entityOperator.findByConditions(ENTITY_CLASS, conditions);

		List<ProcessEventDto> dtos = BeanConverter.convert(entities, DTO_CLASS);
		
		return dtos;
	}
}
