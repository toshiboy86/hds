/**
 * ProcessConditionLogic.java
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

import jp.ciof_cps.hds.dto.ProcessConditionDto;
import jp.ciof_cps.hds.dto.UserDto;
import jp.ciof_cps.hds.entity.ProcessConditionEntity;

/**
 * This class is the logic class for Process Condition.
 */
@ApplicationScoped
@Transactional	
public class ProcessConditionLogic {
	static final Log LOG = LogFactory.getLog(ProcessComponentModelsLogic.class);

	private static final Class<ProcessConditionDto> DTO_CLASS = ProcessConditionDto.class;

	private static final Class<ProcessConditionEntity> ENTITY_CLASS = ProcessConditionEntity.class;

	@Inject
	IdGenerator idGenerator;

	@Inject
	EntityOperator entityOperator;

	/**
	 * This class is the enum class for ProcessConditionDataType 
	 *
	 */
	private enum ProcessConditionDataType {

		PRE_CONDITION(1),
		POST_CONDITION(2);

		private int idx;

		ProcessConditionDataType(int idx) {
			this.idx = idx;
		}

		public int getIdx() {
			return this.idx;
		}
	}

	/**
	 * Create data.
	 * 
	 * @param userDto the user DTO
	 * @param pcmId the Process Component Model (PCM) ID
	 * @param dto the data
	 * @param processConditionDataType the Process Condition Data Type
	 * @return the create data
	 * @throws LogicException operation error in logic 
	 */
	private ProcessConditionDto create(UserDto userDto, String pcmId, ProcessConditionDto dto, ProcessConditionDataType processConditionDataType) throws LogicException {
		if (dto == null) {
			throw new LogicException("Invalid data.", Status.BAD_REQUEST.getStatusCode());
		}
		ProcessConditionEntity entity = BeanConverter.convert(dto, ENTITY_CLASS);
		entity.setPcmId(pcmId);
		entity.setProcessConditionDataType(processConditionDataType.getIdx());
		entityOperator.insert(entity);
		ProcessConditionDto newDto = BeanConverter.convert(entity, DTO_CLASS);
		return newDto;
	}

	/**
	 * Create preCondition data list.
	 * 
	 * @param userDto the user DTO 
	 * @param pcmId the Process Component Model (PCM) ID 
	 * @param dtoList the data list
	 * @return ProcessConditionDto list the Process Condition Dto List
	 * @throws LogicException operation error in logic 
	 */
	public List<ProcessConditionDto> createPreCondition(UserDto userDto, String pcmId, List<ProcessConditionDto> dtoList) throws LogicException {
		List<ProcessConditionDto> newpreconditionList = new ArrayList<>();
		for (ProcessConditionDto dto: dtoList) {
			ProcessConditionDto newDto = create(userDto, pcmId, dto, ProcessConditionDataType.PRE_CONDITION);
			newpreconditionList.add(newDto);
		}
		return newpreconditionList;
	}

	/**
	 * Create postCondition data list.
	 * 
	 * @param userDto the user DTO 
	 * @param pcmId the Process Component Model (PCM) ID 
	 * @param dtoList the data list
	 * @return ProcessConditionDto list the Process Condition Dto List
	 * @throws LogicException operation error in logic 
	 */
	public List<ProcessConditionDto> createPostCondition(UserDto userDto, String pcmId, List<ProcessConditionDto> dtoList) throws LogicException {
		List<ProcessConditionDto> newpreconditionList = new ArrayList<>();
		for (ProcessConditionDto dto: dtoList) {
			ProcessConditionDto newDto = create(userDto, pcmId, dto, ProcessConditionDataType.POST_CONDITION);
			newpreconditionList.add(newDto);
		}
		return newpreconditionList;
	}

	/**
	 * Delete data.
	 * 
	 * @param userDto the user DTO 
	 * @param pcmId the Process Component Model (PCM) ID 
	 * @param processConditionDataType the Process Condition Data Type
	 * @throws LogicException operation error in logic 
	 */
	private void delete(UserDto userDto, String pcmId, ProcessConditionDataType processConditionDataType) throws LogicException {
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("pcmId", pcmId);
		conditions.put("processConditionDataType", processConditionDataType.getIdx());
		List<ProcessConditionEntity> entities = entityOperator.findByConditions(ENTITY_CLASS, conditions);

		entityOperator.delete(entities);
	}
	
	/**
	 * Delete processConditon data.
	 * 
	 * @param userDto the user DTO 
	 * @param pcmId the Process Component Model (PCM) ID 
	 * @throws LogicException operation error in logic 
	 */
	public void deleteProcessCondition(UserDto userDto, String pcmId) throws LogicException {
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("pcmId", pcmId);
		List<ProcessConditionEntity> entities = entityOperator.findByConditions(ENTITY_CLASS, conditions);

		entityOperator.delete(entities);
	}

	/**
	 * Delete preCodition data.
	 * 
	 * @param userDto the user DTO 
	 * @param pcmId the Process Component Model (PCM) ID 
	 * @throws LogicException operation error in logic 
	 */
	public void deletePreCondition(UserDto userDto, String pcmId) throws LogicException {
		delete(userDto, pcmId, ProcessConditionDataType.PRE_CONDITION);
	}

	/**
	 * Delete postCondition data.
	 * 
	 * @param userDto the user DTO 
	 * @param pcmId the Process Component Model (PCM) ID 
	 * @throws LogicException operation error in logic 
	 */
	public void deletePostCondition(UserDto userDto, String pcmId) throws LogicException {
		delete(userDto, pcmId, ProcessConditionDataType.POST_CONDITION);
	}

	/**
	 * Read preCondition data list.
	 * 
	 * @param userDto the user DTO 
	 * @param pcmId the Process Component Model (PCM) ID 
	 * @return the data list
	 * @throws LogicException operation error in logic 
	 */
	public List<ProcessConditionDto> readPreCondition(UserDto userDto, String pcmId) throws LogicException {
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("pcmId", pcmId);
		conditions.put("processConditionDataType", ProcessConditionDataType.PRE_CONDITION.getIdx());
		List<ProcessConditionEntity> entities = entityOperator.findByConditions(ENTITY_CLASS, conditions);

		List<ProcessConditionDto> dtos = BeanConverter.convert(entities, DTO_CLASS);
		return dtos;
	}

	/**
	 * Read postCondition data list.
	 * 
	 * @param userDto the user DTO 
	 * @param pcmId the Process Component Model (PCM) ID 
	 * @return the data list
	 * @throws LogicException operation error in logic 
	 */
	public List<ProcessConditionDto> readPostCondition(UserDto userDto, String pcmId) throws LogicException {
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("pcmId", pcmId);
		conditions.put("processConditionDataType", ProcessConditionDataType.POST_CONDITION.getIdx());
		List<ProcessConditionEntity> entities = entityOperator.findByConditions(ENTITY_CLASS, conditions);

		List<ProcessConditionDto> dtos = BeanConverter.convert(entities, DTO_CLASS);
		return dtos;
	}
}
