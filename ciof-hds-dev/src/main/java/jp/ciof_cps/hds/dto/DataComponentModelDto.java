/**
 * DataComponentModelDto.java
 * (C) 2019. Industrial Value Chain Initiative
 */
package jp.ciof_cps.hds.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class is the DTO class for Data Component Model (DCM).
 */
public class DataComponentModelDto {
	
	/** The Data Component Model (DCM) ID. */
	@JsonProperty("dcm_id")
	private String dcmId;
	
	/** The Data Component Model (DCM) Name. */
	@JsonProperty("dcm_name")
	private String dcmName;
	
	/** The Data Component Model (DCM) Description. */
	@JsonProperty("dcm_description")
	private String dcmDescription;
	
	/** The Data Dictionary ID. */
	@JsonProperty("data_dictionary_id")
	private String dataDictionaryId;
	
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
	
	/** The Process Component Model (PCM) Operation List. */
	@JsonProperty("pcm_operation_list")
	private List<ProcessComponentModelOperationDto> pcmOperationList;
	
	/** The Information Design List. */
	@JsonProperty("information_design_list")
	private List<DcmInformationDesignDto> informationDesignList;
	
	/** The Data Property Definition (DPD) List. */
	@JsonProperty("dpd_list")
	private List<DataPropertyDefinitionDto> dpdList;

	/**
	 * Get the dcmId.
	 * 
	 * @return the dcmId
	 */
	public String getDcmId() {
		return dcmId;
	}

	/**
	 * Set the dcmId.
	 *
	 * @param dcmId the dcmId to set
	 */
	public void setDcmId(String dcmId) {
		this.dcmId = dcmId;
	}

	/**
	 * Get the dcmName.
	 * 
	 * @return the dcmName
	 */
	public String getDcmName() {
		return dcmName;
	}

	/**
	 * Set the dcmName.
	 *
	 * @param dcmName the dcmName to set
	 */
	public void setDcmName(String dcmName) {
		this.dcmName = dcmName;
	}

	/**
	 * Get the dcmDescription.
	 * 
	 * @return the dcmDescription
	 */
	public String getDcmDescription() {
		return dcmDescription;
	}

	/**
	 * Set the dcmDescription.
	 *
	 * @param dcmDescription the dcmDescription to set
	 */
	public void setDcmDescription(String dcmDescription) {
		this.dcmDescription = dcmDescription;
	}

	/**
	 * Get the dataDictionaryId.
	 * 
	 * @return the dataDictionaryId
	 */
	public String getDataDictionaryId() {
		return dataDictionaryId;
	}

	/**
	 * Set the dataDictionaryId.
	 *
	 * @param dataDictionaryId the dataDictionaryId to set
	 */
	public void setDataDictionaryId(String dataDictionaryId) {
		this.dataDictionaryId = dataDictionaryId;
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
	 * Get the pcmOperationList.
	 * 
	 * @return the pcmOperationList
	 */
	public List<ProcessComponentModelOperationDto> getPcmOperationList() {
		return pcmOperationList;
	}

	/**
	 * Set the pcmOperationList.
	 *
	 * @param pcmOperationList the pcmOperationList to set
	 */
	public void setPcmOperationList(List<ProcessComponentModelOperationDto> pcmOperationList) {
		this.pcmOperationList = pcmOperationList;
	}

	/**
	 * Get the informationDesignList.
	 * 
	 * @return the informationDesignList
	 */
	public List<DcmInformationDesignDto> getInformationDesignList() {
		return informationDesignList;
	}

	/**
	 * Set the informationDesignList.
	 *
	 * @param informationDesignList the informationDesignList to set
	 */
	public void setInformationDesignList(List<DcmInformationDesignDto> informationDesignList) {
		this.informationDesignList = informationDesignList;
	}

	/**
	 * Get the dpdList.
	 * 
	 * @return the dpdList
	 */
	public List<DataPropertyDefinitionDto> getDpdList() {
		return dpdList;
	}

	/**
	 * Set the dpdList.
	 *
	 * @param dpdList the dpdList to set
	 */
	public void setDpdList(List<DataPropertyDefinitionDto> dpdList) {
		this.dpdList = dpdList;
	}
	
	
}
