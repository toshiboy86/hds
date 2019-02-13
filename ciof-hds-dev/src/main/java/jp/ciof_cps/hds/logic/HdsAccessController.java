/**
 * HdsAccessController.java
 * (C) 2019. Industrial Value Chain Initiative
 */
package jp.ciof_cps.hds.logic;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response.Status;

import jp.ciof_cps.hds.SpecConstants.HdsRoles;
import jp.ciof_cps.hds.dto.UserDto;
import jp.ciof_cps.hds.entity.DataDictionaryEntity;
import jp.ciof_cps.hds.entity.ServiceDictionaryEntity;

/**
 * This is the class which provides the Access Control of this Application.
 *
 */
@ApplicationScoped
public class HdsAccessController {

	private static final Class<DataDictionaryEntity> DATA_DICTIONARY_ENT_CLASS = DataDictionaryEntity.class;

	private static final Class<ServiceDictionaryEntity> SERVICE_DICTIONARY_ENT_CLASS = ServiceDictionaryEntity.class;

	private static final String DICTIONARY_TYPE_SPECIFIC = "specific";

	@Inject
	EntityOperator entityOperator;

	/**
	 * Check if the user has administrator role.
	 * 
	 * @param userDto user DTO to be validated
	 * @return true if the user has administrator role, false otherwise.
	 * @throws LogicException if the user is invalid
	 */
	public boolean hasAdministratorRole(UserDto userDto) throws LogicException {
		assertUserDto(userDto);
		boolean hasAdministratorRole = userDto.getRoles().contains(HdsRoles.ADMINISTRATOR);
		return hasAdministratorRole;
	}

	/**
	 * Validate if the user has an administrator role.
	 * 
	 * @param userDto user DTO to be validated
	 * @throws LogicException if the user does not have administrator role
	 */
	public void validateRoleIsAdministrator(UserDto userDto) throws LogicException {
		assertUserDto(userDto);
		if (!hasAdministratorRole(userDto)) {
			throw new LogicException("You don't have permission.", Status.FORBIDDEN.getStatusCode());
		}
	}

	/**
	 * Validate if the user has read permission for the specified dictionary.
	 * 
	 * @param userDto the user DTO to be validated
	 * @param dictionaryId the Dictionary ID
	 * @param ownerId the Owner ID of the Dictionary
	 * @param dictionaryType the Type of the Dictionary
	 * @throws LogicException if the user does not have read permission for the dictionary
	 */
	public void validateDictionaryReadPermission(UserDto userDto, String dictionaryId, String ownerId, String dictionaryType) throws LogicException {
		assertUserDto(userDto);
		if (hasAdministratorRole(userDto)) {
			return;
		} else if (DICTIONARY_TYPE_SPECIFIC.equals(dictionaryType)) {
			return;
		} else if (userDto.getUserName().equals(ownerId)) {
			return;
		} else {
			throw new LogicException("You don't have permission.: dictionaryId=" + dictionaryId, Status.FORBIDDEN.getStatusCode());
		}
	}

	/**
	 * Validate if the user has updated or delete permission for the specified dictionary.
	 * 
	 * @param userDto the user DTO to be validated
	 * @param dictionaryId the Data Dictionary ID
	 * @param ownerId the Owner ID of the Dictionary
	 * @throws LogicException if the user does not have valid permission for the dictionary
	 */
	public void validateDictionaryOwner(UserDto userDto, String dictionaryId, String ownerId) throws LogicException {
		assertUserDto(userDto);
		if (hasAdministratorRole(userDto)) {
			return;
		} else if (userDto.getUserName().equals(ownerId)) {
			return;
		} else {
			throw new LogicException("You don't have permission.: dictionaryId=" + dictionaryId, Status.FORBIDDEN.getStatusCode());
		}
	}

	/**
	 * Validate if the user has read permission for the specified dictionary.
	 * This method will access to the database to retrieve the dictionary information.
	 * 
	 * @param userDto user DTO to be validated
	 * @param dataDictionaryId the Data Dictionary ID
	 * @throws LogicException if the user does not have read permission for the dictionary, or the dictionary does not exist
	 */
	public void validateDataDictionaryReadPermission(UserDto userDto, String dataDictionaryId) throws LogicException {
		assertUserDto(userDto);
		DataDictionaryEntity entity = readDataEntityForcely(dataDictionaryId);
		validateDictionaryReadPermission(userDto, dataDictionaryId, entity.getOwnerId(), entity.getDataDictionaryType());
	}

	/**
	 * Validate if the user has updated or delete permission for the specified dictionary.
	 * This method will access to the database to retrieve the dictionary information.
	 * 
	 * @param userDto the user DTO to be validated
	 * @param dataDictionaryId the Data Dictionary ID
	 * @throws LogicException if the user does not have valid permission for the dictionary, or the dictionary does not exist
	 */
	public void validateDataDictionaryOwner(UserDto userDto, String dataDictionaryId) throws LogicException {
		assertUserDto(userDto);
		DataDictionaryEntity entity = readDataEntityForcely(dataDictionaryId);
		validateDictionaryOwner(userDto, dataDictionaryId, entity.getOwnerId());
	}

	/**
	 * Validate if the user has read permission for the specified dictionary.
	 * This method will access to the database to retrieve the dictionary information.
	 * 
	 * @param userDto user DTO to be validated
	 * @param serviceDictionaryId the Service Dictionary ID
	 * @throws LogicException if the user does not have read permission for the dictionary, or the dictionary does not exist
	 */
	public void validateServiceDictionaryReadPermission(UserDto userDto, String serviceDictionaryId) throws LogicException {
		assertUserDto(userDto);
		ServiceDictionaryEntity entity = readServiceEntityForcely(serviceDictionaryId);
		validateDictionaryReadPermission(userDto, serviceDictionaryId, entity.getOwnerId(), entity.getServiceDictionaryType());
	}

	/**
	 * Validate if the user has updated or delete permission for the specified dictionary.
	 * This method will access to the database to retrieve the dictionary information.
	 * 
	 * @param userDto the user DTO to be validated
	 * @param serviceDictionaryId the Data Dictionary ID
	 * @throws LogicException if the user does not have valid permission for the dictionary, or the dictionary does not exist
	 */
	public void validateServiceDictionaryOwner(UserDto userDto, String serviceDictionaryId) throws LogicException {
		assertUserDto(userDto);
		ServiceDictionaryEntity entity = readServiceEntityForcely(serviceDictionaryId);
		validateDictionaryOwner(userDto, serviceDictionaryId, entity.getOwnerId());
	}

	/**
	 * Check if the user need to filter by the owner ID when reading dictionary.
	 * 
	 * @param userDto the user DTO to be checked
	 * @param dictionaryType the Dictionary Type
	 * @return true if the user is required to filter by owner ID, false otherwise.
	 * @throws LogicException if the user is invalid
	 */
	public boolean isRequiredFilterByOwner(UserDto userDto, String dictionaryType) throws LogicException {
		assertUserDto(userDto);
		
		return !hasAdministratorRole(userDto) && !DICTIONARY_TYPE_SPECIFIC.equals(dictionaryType);
			
	}

	/**
	 * Validate if the user has updated or delete permission for the specified dictionary transformation map.
	 * 
	 * @param userDto the user DTO to be validated
	 * @param dtmId the Dictionary Translation Map (DTM) ID
	 * @param ownerId the owner ID of the dictionary transformation map
	 * @throws LogicException if the user does not have valid permission for the dictionary
	 */
	public void validateDtmOwner(UserDto userDto, String dtmId, String ownerId) throws LogicException {
		assertUserDto(userDto);
		if (hasAdministratorRole(userDto)) {
			return;
		} else if (userDto.getUserName().equals(ownerId)) {
			return;
		} else {
			throw new LogicException("You don't have permission.: dtmId=" + dtmId, Status.FORBIDDEN.getStatusCode());
		}
	}

	private DataDictionaryEntity readDataEntityForcely(String dataDictionaryId) throws LogicException {
		DataDictionaryEntity entity = entityOperator.selectByPk(DATA_DICTIONARY_ENT_CLASS, dataDictionaryId);
		if (entity == null) {
			throw new LogicException(String.format("Data not found.: (dataDictionaryId,)=(%s,)", dataDictionaryId), Status.NOT_FOUND.getStatusCode());
		}
		return entity;
	}

	private ServiceDictionaryEntity readServiceEntityForcely(String serviceDictionaryId) throws LogicException {
		ServiceDictionaryEntity entity = entityOperator.selectByPk(SERVICE_DICTIONARY_ENT_CLASS, serviceDictionaryId);
		if (entity == null) {
			throw new LogicException(String.format("Data not found.: (serviceDictionaryId,)=(%s,)", serviceDictionaryId), Status.NOT_FOUND.getStatusCode());
		}
		return entity;
	}

	private void assertUserDto(UserDto userDto) throws LogicException {
		if (userDto == null || userDto.getUserName() == null || userDto.getRoles() == null) {
			throw new LogicException("User data is invalid.", Status.BAD_REQUEST.getStatusCode());
		}
	}
}
