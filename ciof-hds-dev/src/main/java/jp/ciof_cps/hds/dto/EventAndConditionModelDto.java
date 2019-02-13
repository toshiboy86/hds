/**
 * EventAndConditionModelDto.java
 * (C) 2019. Industrial Value Chain Initiative
 */
package jp.ciof_cps.hds.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class is the DTO class for Event and Condition Model (ECM).
 */
public class EventAndConditionModelDto {
	
	/** The Event and Condition Model (ECM) ID. */
	@JsonProperty("ecm_id")
	private String ecmId;
	
	/** The Service Dictionary ID. */
	@JsonProperty("service_dictionary_id")
	private String serviceDictionaryId;
	
	/** The Event and Condition Model (ECM) Name. */
	@JsonProperty("ecm_name")
	private String ecmName;
	
	/** The Event and Condition Model (ECM) Description. */
	@JsonProperty("ecm_description")
	private String ecmDescription;
	
	/** The Author. */
	@JsonProperty("author")
	private String author;
	
	/** The Creation Date. */
	@JsonProperty("creation_date")
	private String creationDate;
	
	/** The Category Dictionary ID. */
	@JsonProperty("category_dictionary_id")
	private String categoryDictionaryId;
	
	/** The Component Category ID. */
	@JsonProperty("component_category_id")
	private String componentCategoryId;
	
	/** The Process Event List. */
	@JsonProperty("process_event_list")
	private List<ProcessEventDto> processEventList;
	
	/** The Event and Condition State (ECS) List. */
	@JsonProperty("ecs_list")
	private List<EventComponentStateDto> ecsList;
	
	/** The User Operation Event. */
	@JsonProperty("user_operation_event")
	private List<UserOperationEventDto> userOperationEvent;
	
	/** The Regular Event. */
	@JsonProperty("regular_event")
	private List<RegularEventDto> regularEvent;
	
	/** The Other Requirement. */
	@JsonProperty("other_requirement")
	private String otherRequirement;

	/**
	 * Get the ecmId.
	 * 
	 * @return the ecmId
	 */
	public String getEcmId() {
		return ecmId;
	}

	/**
	 * Set the ecmId.
	 *
	 * @param ecmId the ecmId to set
	 */
	public void setEcmId(String ecmId) {
		this.ecmId = ecmId;
	}

	/**
	 * Get the serviceDictionaryId.
	 * 
	 * @return the serviceDictionaryId
	 */
	public String getServiceDictionaryId() {
		return serviceDictionaryId;
	}

	/**
	 * Set the serviceDictionaryId.
	 *
	 * @param serviceDictionaryId the serviceDictionaryId to set
	 */
	public void setServiceDictionaryId(String serviceDictionaryId) {
		this.serviceDictionaryId = serviceDictionaryId;
	}

	/**
	 * Get the ecmName.
	 * 
	 * @return the ecmName
	 */
	public String getEcmName() {
		return ecmName;
	}

	/**
	 * Set the ecmName.
	 *
	 * @param ecmName the ecmName to set
	 */
	public void setEcmName(String ecmName) {
		this.ecmName = ecmName;
	}

	/**
	 * Get the ecmDescription.
	 * 
	 * @return the ecmDescription
	 */
	public String getEcmDescription() {
		return ecmDescription;
	}

	/**
	 * Set the ecmDescription.
	 *
	 * @param ecmDescription the ecmDescription to set
	 */
	public void setEcmDescription(String ecmDescription) {
		this.ecmDescription = ecmDescription;
	}

	/**
	 * Get the author.
	 * 
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * Set the author.
	 *
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * Get the creationDate.
	 * 
	 * @return the creationDate
	 */
	public String getCreationDate() {
		return creationDate;
	}

	/**
	 * Set the creationDate.
	 *
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * Get the categoryDictionaryId.
	 * 
	 * @return the categoryDictionaryId
	 */
	public String getCategoryDictionaryId() {
		return categoryDictionaryId;
	}

	/**
	 * Set the categoryDictionaryId.
	 *
	 * @param categoryDictionaryId the categoryDictionaryId to set
	 */
	public void setCategoryDictionaryId(String categoryDictionaryId) {
		this.categoryDictionaryId = categoryDictionaryId;
	}

	/**
	 * Get the componentCategoryId.
	 * 
	 * @return the componentCategoryId
	 */
	public String getComponentCategoryId() {
		return componentCategoryId;
	}

	/**
	 * Set the componentCategoryId.
	 *
	 * @param componentCategoryId the componentCategoryId to set
	 */
	public void setComponentCategoryId(String componentCategoryId) {
		this.componentCategoryId = componentCategoryId;
	}

	/**
	 * Get the processEventList.
	 * 
	 * @return the processEventList
	 */
	public List<ProcessEventDto> getProcessEventList() {
		return processEventList;
	}

	/**
	 * Set the processEventList.
	 *
	 * @param processEventList the processEventList to set
	 */
	public void setProcessEventList(List<ProcessEventDto> processEventList) {
		this.processEventList = processEventList;
	}

	/**
	 * Get the ecsList.
	 * 
	 * @return the ecsList
	 */
	public List<EventComponentStateDto> getEcsList() {
		return ecsList;
	}

	/**
	 * Set the ecsList.
	 *
	 * @param ecsList the ecsList to set
	 */
	public void setEcsList(List<EventComponentStateDto> ecsList) {
		this.ecsList = ecsList;
	}

	/**
	 * Get the userOperationEvent.
	 * 
	 * @return the userOperationEvent
	 */
	public List<UserOperationEventDto> getUserOperationEvent() {
		return userOperationEvent;
	}

	/**
	 * Set the userOperationEvent.
	 *
	 * @param userOperationEvent the userOperationEvent to set
	 */
	public void setUserOperationEvent(List<UserOperationEventDto> userOperationEvent) {
		this.userOperationEvent = userOperationEvent;
	}

	/**
	 * Get the regularEvent.
	 * 
	 * @return the regularEvent
	 */
	public List<RegularEventDto> getRegularEvent() {
		return regularEvent;
	}

	/**
	 * Set the regularEvent.
	 *
	 * @param regularEvent the regularEvent to set
	 */
	public void setRegularEvent(List<RegularEventDto> regularEvent) {
		this.regularEvent = regularEvent;
	}

	/**
	 * Get the otherRequirement.
	 * 
	 * @return the otherRequirement
	 */
	public String getOtherRequirement() {
		return otherRequirement;
	}

	/**
	 * Set the otherRequirement.
	 *
	 * @param otherRequirement the otherRequirement to set
	 */
	public void setOtherRequirement(String otherRequirement) {
		this.otherRequirement = otherRequirement;
	}
	
}
