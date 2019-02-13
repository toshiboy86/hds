/**
 * DataDictionaryDto.java
 * (C) 2019. Industrial Value Chain Initiative
 */
package jp.ciof_cps.hds.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class is the DTO class for Data Dictionary.
 */
public class DataDictionaryDto {
	
	/** The Data Dictionary ID. */
	@JsonProperty("data_dictionary_id")
	private String dataDictionaryId;
	
	/** The Data Dictionary Name. */
	@JsonProperty("data_dictionary_name")
	private String dataDictionaryName;
	
	/** The Data Dictionary Description. */
	@JsonProperty("data_dictionary_description")
	private String dataDictionaryDescription;
	
	/** The Data Dictionary Type. */
	@JsonProperty("data_dictionary_type")
	private String dataDictionaryType;
	
	/** The Version. */
	@JsonProperty("version")
	private Integer version;
	
	/** The Previous Data Dictionary ID. */
	@JsonProperty("previous_data_dictionary_id")
	private String previousDataDictionaryId;
	
	/** The Owner ID. */
	@JsonProperty("owner_id")
	private String ownerId;
	
	/** The Data Component Model (DCM) ID List. */
	@JsonProperty("dcm_id_list")
	private List<String> dcmIdList = new ArrayList<>();
	
	/** The Data Relation Model (DRM) ID List. */
	@JsonProperty("drm_id_list")
	private List<String> drmIdList = new ArrayList<>();

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
	 * Get the dataDictionaryName.
	 * 
	 * @return the dataDictionaryName
	 */
	public String getDataDictionaryName() {
		return dataDictionaryName;
	}

	/**
	 * Set the dataDictionaryName.
	 *
	 * @param dataDictionaryName the dataDictionaryName to set
	 */
	public void setDataDictionaryName(String dataDictionaryName) {
		this.dataDictionaryName = dataDictionaryName;
	}

	/**
	 * Get the dataDictionaryDescription.
	 * 
	 * @return the dataDictionaryDescription
	 */
	public String getDataDictionaryDescription() {
		return dataDictionaryDescription;
	}

	/**
	 * Set the dataDictionaryDescription.
	 *
	 * @param dataDictionaryDescription the dataDictionaryDescription to set
	 */
	public void setDataDictionaryDescription(String dataDictionaryDescription) {
		this.dataDictionaryDescription = dataDictionaryDescription;
	}

	/**
	 * Get the dataDictionaryType.
	 * 
	 * @return the dataDictionaryType
	 */
	public String getDataDictionaryType() {
		return dataDictionaryType;
	}

	/**
	 * Set the dataDictionaryType.
	 *
	 * @param dataDictionaryType the dataDictionaryType to set
	 */
	public void setDataDictionaryType(String dataDictionaryType) {
		this.dataDictionaryType = dataDictionaryType;
	}

	/**
	 * Get the version.
	 * 
	 * @return the version
	 */
	public Integer getVersion() {
		return version;
	}

	/**
	 * Set the version.
	 *
	 * @param version the version to set
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * Get the previousDataDictionaryId.
	 * 
	 * @return the previousDataDictionaryId
	 */
	public String getPreviousDataDictionaryId() {
		return previousDataDictionaryId;
	}

	/**
	 * Set the previousDataDictionaryId.
	 *
	 * @param previousDataDictionaryId the previousDataDictionaryId to set
	 */
	public void setPreviousDataDictionaryId(String previousDataDictionaryId) {
		this.previousDataDictionaryId = previousDataDictionaryId;
	}

	/**
	 * Get the ownerId.
	 * 
	 * @return the ownerId
	 */
	public String getOwnerId() {
		return ownerId;
	}

	/**
	 * Set the ownerId.
	 *
	 * @param ownerId the ownerId to set
	 */
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	/**
	 * Get the dcmIdList.
	 * 
	 * @return the dcmIdList
	 */
	public List<String> getDcmIdList() {
		return dcmIdList;
	}

	/**
	 * Set the dcmIdList.
	 *
	 * @param dcmIdList the dcmIdList to set
	 */
	public void setDcmIdList(List<String> dcmIdList) {
		this.dcmIdList = dcmIdList;
	}

	/**
	 * Get the drmIdList.
	 * 
	 * @return the drmIdList
	 */
	public List<String> getDrmIdList() {
		return drmIdList;
	}

	/**
	 * Set the drmIdList.
	 *
	 * @param drmIdList the drmIdList to set
	 */
	public void setDrmIdList(List<String> drmIdList) {
		this.drmIdList = drmIdList;
	}

	
}
