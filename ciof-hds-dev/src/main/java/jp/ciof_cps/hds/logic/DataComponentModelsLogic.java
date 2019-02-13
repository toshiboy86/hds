/**
 * DataComponentModelsLogic.java
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

import jp.ciof_cps.hds.dto.DataComponentModelDto;
import jp.ciof_cps.hds.dto.DataPropertyDefinitionDto;
import jp.ciof_cps.hds.dto.DcmInformationDesignDto;
import jp.ciof_cps.hds.dto.ProcessComponentModelOperationDto;
import jp.ciof_cps.hds.dto.UserDto;
import jp.ciof_cps.hds.entity.DataComponentModelEntity;
import jp.ciof_cps.hds.entity.DataPropertyDefinitionEntity;

/**
 * This class is the logic class for Data Component Model (DCM).
 *
 */

@ApplicationScoped
@Transactional
public class DataComponentModelsLogic {
	static final Log LOG = LogFactory.getLog(DataComponentModelsLogic.class);

	private static final Class<DataComponentModelDto> DTO_CLASS = DataComponentModelDto.class;

	private static final Class<DataComponentModelEntity> ENTITY_CLASS = DataComponentModelEntity.class;

	private static final String[] EXCLUDE_COPY_PROP_NAMES = {
			"dcmId",
			"dataDictionaryId"
			};

	private static final String[] EXCLUDE_NULL_COPY_PROP_NAMES = {
			"dcmName",
			"dcmDescription",
			"author",
			"creationDate",
			"categoryDictionaryId",
			"componentCategoryId",
			"pcmOperationList",
			"informationDesignList",
			"dpdList"
			};

	@Inject
	IdGenerator idGenerator;

	@Inject
	EntityOperator entityOperator;

	@Inject
	DataPropertyDefinitionsLogic dpdLogic;

	@Inject
	DcmInformationDesignLogic dcmInformationDesignLogic;

	@Inject
	ProcessComponentModelOperationLogic processComponentModelOperationLogic;

	@Inject
	HdsAccessController accessController;

	/**
	 * Create data.
	 *
	 * @param userDto the user DTO
	 * @param dataDictionaryId the Data Dictionary ID
	 * @param dto the data to create
	 * @return the created data
	 * @throws LogicException operation error in logic
	 */
	public DataComponentModelDto create(UserDto userDto, String dataDictionaryId, DataComponentModelDto dto) throws LogicException {
		assertArguments(userDto, dataDictionaryId);
		assertArguments(dto);
		createAssertArguments(dto);

		accessController.validateDataDictionaryOwner(userDto, dataDictionaryId);

		String dcmId = idGenerator.generateId(DataComponentModelsLogic.class);

		DataComponentModelEntity entity = BeanConverter.convert(dto, ENTITY_CLASS);
		entity.setDcmId(dcmId);
		entity.setDataDictionaryId(dataDictionaryId);
		entityOperator.insert(entity);
		DataComponentModelDto newDto = BeanConverter.convert(entity, DTO_CLASS);

		List<DataPropertyDefinitionDto> dpdList = dto.getDpdList();
		if (dpdList == null) {
			dpdList = new ArrayList<>();
		}
		List<DataPropertyDefinitionDto> newDpdList = new ArrayList<>();
		for (DataPropertyDefinitionDto dpd : dpdList) {
			DataPropertyDefinitionDto newDpd = dpdLogic.create(userDto, dcmId, dpd);
			newDpdList.add(newDpd);
		}
		newDto.setDpdList(newDpdList);

		List<DcmInformationDesignDto> dcmInformationDesignList = dto.getInformationDesignList();
		if (dcmInformationDesignList == null) {
			dcmInformationDesignList = new ArrayList<>();
		}
		List<DcmInformationDesignDto> newDcmInformaitonDesignList = dcmInformationDesignLogic.create(userDto, dcmId, dcmInformationDesignList);
		newDto.setInformationDesignList(newDcmInformaitonDesignList);

		List<ProcessComponentModelOperationDto> pcmOperationList = dto.getPcmOperationList();
		if (pcmOperationList == null) {
			pcmOperationList = new ArrayList<>();
		}
		List<ProcessComponentModelOperationDto> newPcmOperationList = processComponentModelOperationLogic.create(userDto, dcmId, pcmOperationList);
		newDto.setPcmOperationList(newPcmOperationList);

		return newDto;
	}

	/**
	 * Update data.
	 *
	 * @param userDto the user DTO
	 * @param dataDictionaryId the Data Dictionary ID
	 * @param dcmId the Data Component Model (DCM) ID
	 * @param dto the data to update
	 * @return the updated data
	 * @throws LogicException operation error in logic
	 */
	public DataComponentModelDto update(UserDto userDto, String dataDictionaryId, String dcmId, DataComponentModelDto dto) throws LogicException {
		assertArguments(userDto, dataDictionaryId, dcmId);
		assertArguments(dto);

		accessController.validateDataDictionaryOwner(userDto, dataDictionaryId);

		DataComponentModelEntity entity = readEntityForcely(userDto, dataDictionaryId, dcmId);
		BeanConverter.copyProperties(dto, entity, ENTITY_CLASS, EXCLUDE_COPY_PROP_NAMES, EXCLUDE_NULL_COPY_PROP_NAMES);
		entity.setDcmId(dcmId);
		entity.setDataDictionaryId(dataDictionaryId);

		entityOperator.update(entity);
		DataComponentModelDto newDto = BeanConverter.convert(entity, DTO_CLASS);

		Map<String, Object> conditions = new HashMap<>();
		conditions.put("dcmId", dcmId);

		List<DataPropertyDefinitionDto> dpdList = dto.getDpdList();
		List<DataPropertyDefinitionDto> newDpdList = new ArrayList<>();

		if(dpdList != null) {
			List<DataPropertyDefinitionEntity> dpdEntities = entityOperator.findByConditions(DataPropertyDefinitionEntity.class, conditions);
			entityOperator.delete(dpdEntities);
			entityOperator.flush();

			for (DataPropertyDefinitionDto dpd : dpdList) {
				DataPropertyDefinitionDto newDpd = dpdLogic.create(userDto, dcmId, dpd);
				newDpdList.add(newDpd);
			}
		}else{
			newDpdList = dpdLogic.read(userDto, dcmId);
		}
		newDto.setDpdList(newDpdList);

		List<DcmInformationDesignDto> dcmInformationDesignList = dto.getInformationDesignList();
		List<DcmInformationDesignDto> newDcmInformationDesignList = null;

		if(dcmInformationDesignList != null) {
			dcmInformationDesignLogic.delete(userDto, dcmId);
			newDcmInformationDesignList = dcmInformationDesignLogic.create(userDto, dcmId, dcmInformationDesignList);
		}else{
			newDcmInformationDesignList = dcmInformationDesignLogic.read(userDto, dcmId);
		}
		newDto.setInformationDesignList(newDcmInformationDesignList);


		List<ProcessComponentModelOperationDto> pcmOperationList = dto.getPcmOperationList();
		List<ProcessComponentModelOperationDto> newPcmOperationList = null;

		if(pcmOperationList != null) {
			processComponentModelOperationLogic.delete(userDto, dcmId);
			newPcmOperationList = processComponentModelOperationLogic.create(userDto, dcmId, pcmOperationList);
		}else{
			newPcmOperationList = processComponentModelOperationLogic.read(userDto, dcmId);
		}
		newDto.setPcmOperationList(newPcmOperationList);

		return newDto;
	}

	/**
	 * Delete data.
	 *
	 * @param userDto the user DTO
	 * @param dataDictionaryId the Data Dictionary ID
	 * @param dcmId the Data Component Model (DCM) ID
	 * @throws LogicException operation error in logic
	 */
	public void delete(UserDto userDto, String dataDictionaryId, String dcmId) throws LogicException {
		assertArguments(userDto, dataDictionaryId, dcmId);
		accessController.validateDataDictionaryOwner(userDto, dataDictionaryId);

		DataComponentModelEntity entity = readEntityForcely(userDto, dataDictionaryId, dcmId);

		entityOperator.delete(entity);
	}

	/**
	 * Read data.
	 *
	 * @param userDto the user DTO
	 * @param dataDictionaryId the Data Dictionary ID
	 * @param dcmId the Data Component Model (DCM) ID
	 * @return the data
	 * @throws LogicException operation error in logic
	 */
	public DataComponentModelDto read(UserDto userDto, String dataDictionaryId, String dcmId) throws LogicException {
		assertArguments(userDto, dataDictionaryId, dcmId);
		accessController.validateDataDictionaryReadPermission(userDto, dataDictionaryId);

		DataComponentModelEntity entity = readEntityForcely(userDto, dataDictionaryId, dcmId);
		DataComponentModelDto dto = BeanConverter.convert(entity, DTO_CLASS);

		List<DataPropertyDefinitionDto> dpdList = dpdLogic.read(userDto, dcmId);
		dto.setDpdList(dpdList);

		List<DcmInformationDesignDto> dcmInformationDesignList = dcmInformationDesignLogic.read(userDto, dcmId);
		dto.setInformationDesignList(dcmInformationDesignList);

		List<ProcessComponentModelOperationDto> pcmOperationList = processComponentModelOperationLogic.read(userDto, dcmId);
		dto.setPcmOperationList(pcmOperationList);

		return dto;
	}

	/**
	 * Read data list.
	 *
	 * @param userDto the user DTO
	 * @param dataDictionaryId the Data Dictionary ID
	 * @return the data list.
	 * @throws LogicException operation error in logic
	 */
	public List<DataComponentModelDto> read(UserDto userDto, String dataDictionaryId) throws LogicException {
		assertArguments(userDto, dataDictionaryId);
		accessController.validateDataDictionaryReadPermission(userDto, dataDictionaryId);

		Map<String, Object> conditions = new HashMap<>();
		conditions.put("dataDictionaryId", dataDictionaryId);
		List<DataComponentModelEntity> entities = entityOperator.findByConditions(ENTITY_CLASS, conditions);
		List<DataComponentModelDto> dtos = BeanConverter.convert(entities, DTO_CLASS);
		for (DataComponentModelDto dto : dtos) {
			List<DataPropertyDefinitionDto> dpdList = dpdLogic.read(userDto, dto.getDcmId());
			dto.setDpdList(dpdList);
		}
		for (DataComponentModelDto dto : dtos) {
			List<DcmInformationDesignDto> dcmInformationDesigndList = dcmInformationDesignLogic.read(userDto, dto.getDcmId());
			dto.setInformationDesignList(dcmInformationDesigndList);
		}
		for (DataComponentModelDto dto : dtos) {
			List<ProcessComponentModelOperationDto> pcmOperationList = processComponentModelOperationLogic.read(userDto, dto.getDcmId());
			dto.setPcmOperationList(pcmOperationList);
		}

		return dtos;
	}

	/**
	 * Assert arguments.
	 *
	 * @param userDto the user DTO
	 * @param dataDictionaryId the Data Dictionary ID
	 * @param dcmId the Data Component Model (DCM) ID
	 * @throws LogicException assertion failed.
	 */
	private void assertArguments(UserDto userDto, String dataDictionaryId, String dcmId) throws LogicException {
		assertArguments(userDto, dataDictionaryId);

		if (dcmId == null) {
			throw new LogicException("dcmId is null.", Status.BAD_REQUEST.getStatusCode());
		}
	}

	/**
	 * Assert arguments.
	 *
	 * @param userDto the user DTO
	 * @param dataDictionaryId the Data Dictionary ID
	 * @throws LogicException assertion failed.
	 */
	private void assertArguments(UserDto userDto, String dataDictionaryId) throws LogicException {
		if (userDto == null) {
			throw new LogicException("userDto is null.", Status.BAD_REQUEST.getStatusCode());
		}
		if (dataDictionaryId == null) {
			throw new LogicException("dataDictionaryId is null.", Status.BAD_REQUEST.getStatusCode());
		}
	}

	/**
	 * Assert arguments.
	 *
	 * @param dto the data DTO
	 * @throws LogicException assertion failed.
	 */
	private void assertArguments(DataComponentModelDto dto) throws LogicException {
		if (dto == null) {
			throw new LogicException("Invalid data.", Status.BAD_REQUEST.getStatusCode());
		}
	}

	/**
	 * Assert arguments.
	 *
	 * @param dto the data DTO
	 * @throws LogicException assertion failed.
	 */
	private void createAssertArguments(DataComponentModelDto dto) throws LogicException {
		if (dto.getDpdList() == null) {
			throw new LogicException("dpdList is null.", Status.BAD_REQUEST.getStatusCode());
		}
	}

	/**
	 * obtain entity.
	 * If entity not found, an exception occurs.
	 *
	 * @param userDto the user DTO
	 * @param dataDictionaryId the Data Dictionary ID
	 * @param dcmId the Data Component Model (DCM) ID
	 * @return the entity
	 * @throws LogicException operation error in logic.
	 */
	private DataComponentModelEntity readEntityForcely(UserDto userDto, String dataDictionaryId, String dcmId) throws LogicException {
		DataComponentModelEntity entity = entityOperator.selectByPk(ENTITY_CLASS, dcmId);
		if (entity == null) {
			throw new LogicException(String.format("Data not found.: (dcmId,)=(%s,)", dcmId), Status.NOT_FOUND.getStatusCode());
		}
		return entity;
	}
}
