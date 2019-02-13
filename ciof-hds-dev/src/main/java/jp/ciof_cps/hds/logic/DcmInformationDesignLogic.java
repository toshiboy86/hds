/**
 * DcmInformationDesginLogic.java
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

import jp.ciof_cps.hds.dto.DcmInformationDesignDto;
import jp.ciof_cps.hds.dto.UserDto;
import jp.ciof_cps.hds.entity.DcmInformationDesignEntity;

/**
 * This class is the logic class for Data Component Model (DCM) Information Design.
 */
@ApplicationScoped
public class DcmInformationDesignLogic {

	static final Log LOG = LogFactory.getLog(DcmInformationDesignLogic.class);

	private static final Class<DcmInformationDesignEntity> ENTITY_CLASS = DcmInformationDesignEntity.class;

	private static final Class<DcmInformationDesignDto> DTO_CLASS = DcmInformationDesignDto.class;

	@Inject
	EntityOperator entityOperator;

	/**
	 * Read data list.
	 *
	 * @param userDto the user DTO
	 * @param dcmId the Data Component Model (DCM) ID
	 * @param dto the data
	 * @return the list of DTO
	 * @throws LogicException operation error in logic
	 */
	public List<DcmInformationDesignDto> create(UserDto userDto, String dcmId, List<DcmInformationDesignDto> dto) throws LogicException {
		List<DcmInformationDesignEntity> entities = BeanConverter.convert(dto, ENTITY_CLASS);

		for(DcmInformationDesignEntity entity : entities) {
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
		List<DcmInformationDesignEntity> entities = entityOperator.findByConditions(ENTITY_CLASS, conditions);

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
	public List<DcmInformationDesignDto> read(UserDto userDto, String dcmId) throws LogicException {
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("dcmId", dcmId);
		List<DcmInformationDesignEntity> entities = entityOperator.findByConditions(ENTITY_CLASS, conditions);

		List<DcmInformationDesignDto> dtos = BeanConverter.convert(entities, DTO_CLASS);
		return dtos;
	}
}
