/**
 * DataPropertyDefinitionDto.java
 * (C) 2019. Industrial Value Chain Initiative
 */
package jp.ciof_cps.hds.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class is the DTO class for Data Property Definition (DPD).
 */
public class DataPropertyDefinitionDto {
	
	/** The Data Property Definition (DPD) Name. */
	@JsonProperty("dpd_name")
	private String dpdName;
	
	/** The Data Property Definition (DPD) Description. */
	@JsonProperty("dpd_description")
	private String dpdDescription;
	
	/** The Data Property Definition (DPD) Type. */
	@JsonProperty("dpd_type")
	private String dpdType;
	
	/** Whether this DPD is list. */
	@JsonProperty("is_list")
	private Boolean isList;
	
	/** Whether this DPD is required. */
	@JsonProperty("is_required")
	private Boolean isRequired;
	
	/** Whether this DPD is primary key. */
	@JsonProperty("is_primary_key")
	private Boolean isPrimaryKey;
	
	/** The Default Value. */
	@JsonProperty("default_value")
	private String defaultValue;
	
	/** The Sample Value. */
	@JsonProperty("sample_value")
	private String sampleValue;

	/**
	 * Get the dpdName.
	 * 
	 * @return the dpdName
	 */
	public String getDpdName() {
		return dpdName;
	}

	/**
	 * Set the dpdName.
	 *
	 * @param dpdName the dpdName to set
	 */
	public void setDpdName(String dpdName) {
		this.dpdName = dpdName;
	}

	/**
	 * Get the dpdDescription.
	 * 
	 * @return the dpdDescription
	 */
	public String getDpdDescription() {
		return dpdDescription;
	}

	/**
	 * Set the dpdDescription.
	 *
	 * @param dpdDescription the dpdDescription to set
	 */
	public void setDpdDescription(String dpdDescription) {
		this.dpdDescription = dpdDescription;
	}

	/**
	 * Get the dpdType.
	 * 
	 * @return the dpdType
	 */
	public String getDpdType() {
		return dpdType;
	}

	/**
	 * Set the dpdType.
	 *
	 * @param dpdType the dpdType to set
	 */
	public void setDpdType(String dpdType) {
		this.dpdType = dpdType;
	}

	/**
	 * Get the isList.
	 * 
	 * @return the isList
	 */
	public Boolean getIsList() {
		return isList;
	}

	/**
	 * Set the isList.
	 *
	 * @param isList the isList to set
	 */
	public void setIsList(Boolean isList) {
		this.isList = isList;
	}

	/**
	 * Get the isRequired.
	 * 
	 * @return the isRequired
	 */
	public Boolean getIsRequired() {
		return isRequired;
	}

	/**
	 * Set the isRequired.
	 *
	 * @param isRequired the isRequired to set
	 */
	public void setIsRequired(Boolean isRequired) {
		this.isRequired = isRequired;
	}

	/**
	 * Get the isPrimaryKey.
	 * 
	 * @return the isPrimaryKey
	 */
	public Boolean getIsPrimaryKey() {
		return isPrimaryKey;
	}

	/**
	 * Set the isPrimaryKey.
	 *
	 * @param isPrimaryKey the isPrimaryKey to set
	 */
	public void setIsPrimaryKey(Boolean isPrimaryKey) {
		this.isPrimaryKey = isPrimaryKey;
	}

	/**
	 * Get the defaultValue.
	 * 
	 * @return the defaultValue
	 */
	public String getDefaultValue() {
		return defaultValue;
	}

	/**
	 * Set the defaultValue.
	 *
	 * @param defaultValue the defaultValue to set
	 */
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	/**
	 * Get the sampleValue.
	 * 
	 * @return the sampleValue
	 */
	public String getSampleValue() {
		return sampleValue;
	}

	/**
	 * Set the sampleValue.
	 *
	 * @param sampleValue the sampleValue to set
	 */
	public void setSampleValue(String sampleValue) {
		this.sampleValue = sampleValue;
	}

}
