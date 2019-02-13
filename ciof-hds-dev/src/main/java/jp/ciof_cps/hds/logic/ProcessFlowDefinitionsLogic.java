/**
 * ProcessFlowDefinitionsLogic.java
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

import jp.ciof_cps.hds.dto.ProcessFlowDefinitionDto;
import jp.ciof_cps.hds.dto.UserDto;
import jp.ciof_cps.hds.entity.ProcessFlowDefinitionEntity;

/**
 * This class is the logic class for Process Flow Definition (PFD).
 */
@ApplicationScoped
@Transactional	
public class ProcessFlowDefinitionsLogic {

	static final Log LOG = LogFactory.getLog(ProcessComponentModelsLogic.class);

	private static final Class<ProcessFlowDefinitionDto> DTO_CLASS = ProcessFlowDefinitionDto.class;

	private static final Class<ProcessFlowDefinitionEntity> ENTITY_CLASS = ProcessFlowDefinitionEntity.class;

	@Inject
	IdGenerator idGenerator;
	
	@Inject
	EntityOperator entityOperator;

	/**
	 * Create data.
	 * 
	 * @param userDto the use DTO 
	 * @param pcmId the Process Component Model (PCM) ID
	 * @param dto the data
	 * @return the create data
	 * @throws LogicException operation error in logic 
	 */
	public ProcessFlowDefinitionDto create(UserDto userDto, String pcmId, ProcessFlowDefinitionDto dto) throws LogicException {
		if (dto == null) {
			throw new LogicException("Invalid data.", Status.BAD_REQUEST.getStatusCode());
		}
		ProcessFlowDefinitionEntity entity = BeanConverter.convert(dto, ENTITY_CLASS);
		entity.setPcmId(pcmId);
		entityOperator.insert(entity);
		ProcessFlowDefinitionDto newDto = BeanConverter.convert(entity, DTO_CLASS);
		return newDto;
	}
	
	/**
	 * 
	 * Create data list.
	 * @param userDto the use DTO 
	 * @param pcmId the Process Component Model (PCM) ID
	 * @param dtoList the data list
	 * @return the create data list
	 * @throws LogicException operation error in logic 
	 */
	public List<ProcessFlowDefinitionDto> create(UserDto userDto, String pcmId, List<ProcessFlowDefinitionDto> dtoList) throws LogicException {
		if (dtoList == null) {
			throw new LogicException("Invalid data.", Status.BAD_REQUEST.getStatusCode());
		}
		List<ProcessFlowDefinitionDto> newDtoList = new ArrayList<>();
		for (ProcessFlowDefinitionDto pfdDto: dtoList) {
			ProcessFlowDefinitionDto newDto = create(userDto, pcmId, pfdDto);
			newDtoList.add(newDto);
		}
		return newDtoList;
	}

	/**
	 * Delete data.
	 * 
	 * @param userDto the use DTO 
	 * @param pcmId the Process Component Model (PCM) ID
	 * @throws LogicException operation error in logic 
	 */
	public void delete(UserDto userDto, String pcmId) throws LogicException {
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("pcmId", pcmId);
		List<ProcessFlowDefinitionEntity> entities = entityOperator.findByConditions(ENTITY_CLASS, conditions);
		
		entityOperator.delete(entities);
	}

	/**
	 * Read data list.
	 * 
	 * @param userDto the use DTO 
	 * @param pcmId the Process Component Model (PCM) ID
	 * @return the data list
	 * @throws LogicException operation error in logic 
	 */
	public List<ProcessFlowDefinitionDto> read(UserDto userDto, String pcmId) throws LogicException {
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("pcmId", pcmId);
		List<ProcessFlowDefinitionEntity> entities = entityOperator.findByConditions(ENTITY_CLASS, conditions);

		List<ProcessFlowDefinitionDto> dtos = BeanConverter.convert(entities, DTO_CLASS);
		return dtos;
	}
}
