/**
 * DataComponentModelOperationsLogic.java
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

import jp.ciof_cps.hds.dto.DataComponentModelOperationDto;
import jp.ciof_cps.hds.dto.UserDto;
import jp.ciof_cps.hds.entity.DataComponentModelOperationEntity;
import jp.ciof_cps.hds.entity.OperationItemEntity;

/**
 * This class is the logic class for Data Component Model (DCM) Operation.
 */
@ApplicationScoped
@Transactional	
public class DataComponentModelOperationsLogic {

	static final Log LOG = LogFactory.getLog(DataComponentModelOperationsLogic.class);

	private static final Class<DataComponentModelOperationDto> DTO_CLASS = DataComponentModelOperationDto.class;

	private static final Class<DataComponentModelOperationEntity> ENTITY_CLASS = DataComponentModelOperationEntity.class;

	@Inject
	IdGenerator idGenerator;
	
	@Inject
	EntityOperator entityOperator;

	/**
	 * Create data.
	 * 
	 * @param userDto the user DTO
	 * @param pcmId the Process Component Model (PCM) ID
	 * @param dto the data to create
	 * @return the create data
	 * @throws LogicException operation error in logic
	 */
	public DataComponentModelOperationDto create(UserDto userDto, String pcmId, DataComponentModelOperationDto dto) throws LogicException {
		if (dto == null) {
			throw new LogicException("Invalid data.", Status.BAD_REQUEST.getStatusCode());
		}
		DataComponentModelOperationEntity dcmOperationEntity = BeanConverter.convert(dto, ENTITY_CLASS);
		dcmOperationEntity.setPcmId(pcmId);
		entityOperator.insert(dcmOperationEntity);

		Integer dcmOperationId = dcmOperationEntity.getDcmOperationId();
		List<String> operationList = dto.getOperationList();
		if (operationList == null) {
			throw new LogicException("Invalid data.", Status.BAD_REQUEST.getStatusCode());
		}
		for (String operation: operationList) {
			OperationItemEntity entity = new OperationItemEntity();
			entity.setDcmOperationId(dcmOperationId);
			entity.setOperation(operation);
			entityOperator.insert(entity);
		}

		DataComponentModelOperationDto newDto = BeanConverter.convert(dcmOperationEntity, DTO_CLASS);
		newDto.setOperationList(operationList);
		return newDto;
	}

	/**
	 * Create data list.
	 * 
	 * @param userDto the user DTO
	 * @param pcmId the Process Component Model (PCM) ID
	 * @param dtoList the data list
	 * @return the create data list
	 * @throws LogicException operation error in logic
	 */
	public List<DataComponentModelOperationDto> create(UserDto userDto, String pcmId, List<DataComponentModelOperationDto> dtoList) throws LogicException {
		List<DataComponentModelOperationDto> newDtoList = new ArrayList<>();
		for (DataComponentModelOperationDto dcmOperationDto: dtoList) {
			DataComponentModelOperationDto newDto = create(userDto, pcmId, dcmOperationDto);
			newDtoList.add(newDto);
		}
		return newDtoList;
	}

	/**
	 * Delete data.
	 * 
	 * @param userDto the user DTO
	 * @param pcmId the Process Component Model (PCM) ID
	 * @throws LogicException operation error in logic 
	 */
	public void delete(UserDto userDto, String pcmId) throws LogicException {
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("pcmId", pcmId);
		List<DataComponentModelOperationEntity> entities = entityOperator.findByConditions(ENTITY_CLASS, conditions);
		
		entityOperator.delete(entities);
	}

	/**
	 * Read data list.
	 * 
	 * @param userDto the user DTO
	 * @param pcmId  the Process Component Model (PCM) ID
	 * @return the data list
	 * @throws LogicException operation error in logic 
	 */
	public List<DataComponentModelOperationDto> read(UserDto userDto, String pcmId) throws LogicException {
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("pcmId", pcmId);
		List<DataComponentModelOperationEntity> entities = entityOperator.findByConditions(ENTITY_CLASS, conditions);

		List<DataComponentModelOperationDto> dtos = new ArrayList<>();
		for (DataComponentModelOperationEntity entity: entities) {
			DataComponentModelOperationDto dto = BeanConverter.convert(entity, DTO_CLASS);
			Map<String, Object> itemConditions = new HashMap<>();
			itemConditions.put("dcmOperationId", entity.getDcmOperationId());
			List<OperationItemEntity> itemEntities = entityOperator.findByConditions(OperationItemEntity.class, itemConditions);

			List<String> operationList = new ArrayList<>();
			for (OperationItemEntity itemEntity: itemEntities) {
				operationList.add(itemEntity.getOperation());
			}
			dto.setOperationList(operationList);
			dtos.add(dto);
		}
		return dtos;
	}
}
