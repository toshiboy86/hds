/**
 * ProcessComponentModelDto.java
 * (C) 2019. Industrial Value Chain Initiative
 */
package jp.ciof_cps.hds.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class is the DTO class for Process Component Model (PCM).
 */
public class ProcessComponentModelDto {
	
	/** The Process Component Model (PCM) ID. */
	@JsonProperty("pcm_id")
	private String pcmId;
	
	/** The Service Dictionary ID. */
	@JsonProperty("service_dictionary_id")
	private String serviceDictionaryId;
	
	/** The Process Component Model (PCM) Name. */
	@JsonProperty("pcm_name")
	private String pcmName;
	
	/** The Process Component Model (PCM) Description. */
	@JsonProperty("pcm_description")
	private String pcmDescription;
	
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
	
	/** The Data Component Model (DCM) Operation List. */
	@JsonProperty("dcm_operation_list")
	private List<DataComponentModelOperationDto> dcmOperationList;
	
	/** The Process Flow Definition (PFD) List. */
	@JsonProperty("pfd_list")
	private List<ProcessFlowDefinitionDto> pfdList;
	
	/** The Precondition List. */
	@JsonProperty("precondition_list")
	private List<ProcessConditionDto> preconditionList;
	
	/** The Postcondition List. */
	@JsonProperty("postcondition_list")
	private List<ProcessConditionDto> postconditionList;
	
	/** The Hardware Requirement. */
	@JsonProperty("hardware_requirement")
	private String hardwareRequirement;
	
	/** The Other Requirement. */
	@JsonProperty("other_requirement")
	private String otherRequirement;

	/**
	 * Get the pcmId.
	 * 
	 * @return the pcmId
	 */
	public String getPcmId() {
		return pcmId;
	}

	/**
	 * Set the pcmId.
	 *
	 * @param pcmId the pcmId to set
	 */
	public void setPcmId(String pcmId) {
		this.pcmId = pcmId;
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
	 * Get the pcmName.
	 * 
	 * @return the pcmName
	 */
	public String getPcmName() {
		return pcmName;
	}

	/**
	 * Set the pcmName.
	 *
	 * @param pcmName the pcmName to set
	 */
	public void setPcmName(String pcmName) {
		this.pcmName = pcmName;
	}

	/**
	 * Get the pcmDescription.
	 * 
	 * @return the pcmDescription
	 */
	public String getPcmDescription() {
		return pcmDescription;
	}

	/**
	 * Set the pcmDescription.
	 *
	 * @param pcmDescription the pcmDescription to set
	 */
	public void setPcmDescription(String pcmDescription) {
		this.pcmDescription = pcmDescription;
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
	 * Get the dcmOperationList.
	 * 
	 * @return the dcmOperationList
	 */
	public List<DataComponentModelOperationDto> getDcmOperationList() {
		return dcmOperationList;
	}

	/**
	 * Set the dcmOperationList.
	 *
	 * @param dcmOperationList the dcmOperationList to set
	 */
	public void setDcmOperationList(List<DataComponentModelOperationDto> dcmOperationList) {
		this.dcmOperationList = dcmOperationList;
	}

	/**
	 * Get the pfdList.
	 * 
	 * @return the pfdList
	 */
	public List<ProcessFlowDefinitionDto> getPfdList() {
		return pfdList;
	}

	/**
	 * Set the pfdList.
	 *
	 * @param pfdList the pfdList to set
	 */
	public void setPfdList(List<ProcessFlowDefinitionDto> pfdList) {
		this.pfdList = pfdList;
	}

	/**
	 * Get the preconditionList.
	 * 
	 * @return the preconditionList
	 */
	public List<ProcessConditionDto> getPreconditionList() {
		return preconditionList;
	}

	/**
	 * Set the preconditionList.
	 *
	 * @param preconditionList the preconditionList to set
	 */
	public void setPreconditionList(List<ProcessConditionDto> preconditionList) {
		this.preconditionList = preconditionList;
	}

	/**
	 * Get the postconditionList.
	 * 
	 * @return the postconditionList
	 */
	public List<ProcessConditionDto> getPostconditionList() {
		return postconditionList;
	}

	/**
	 * Set the postconditionList.
	 *
	 * @param postconditionList the postconditionList to set
	 */
	public void setPostconditionList(List<ProcessConditionDto> postconditionList) {
		this.postconditionList = postconditionList;
	}

	/**
	 * Get the hardwareRequirement.
	 * 
	 * @return the hardwareRequirement
	 */
	public String getHardwareRequirement() {
		return hardwareRequirement;
	}

	/**
	 * Set the hardwareRequirement.
	 *
	 * @param hardwareRequirement the hardwareRequirement to set
	 */
	public void setHardwareRequirement(String hardwareRequirement) {
		this.hardwareRequirement = hardwareRequirement;
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
