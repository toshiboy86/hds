/**
 * DataRelationModelDto.java
 * (C) 2019. Industrial Value Chain Initiative
 */
package jp.ciof_cps.hds.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class is the DTO class for Data Relation Model (DRM).
 */
public class DataRelationModelDto {
	
	/** The Data Relation Model (DRM) ID. */
	@JsonProperty("drm_id")
	private String drmId;
	
	/** The Data Dictionary ID. */
	@JsonProperty("data_dictionary_id")
	private String dataDictionaryId;
	
	/** The Data Component Model (DCM) ID. */
	@JsonProperty("dcm_id")
	private String dcmId;
	
	/** The Data Property Definition (DPD) Name List. */
	@JsonProperty("dpd_name_list")
	private List<String> dpdNameList;
	
	/** The Target Data Component Model (DCM) ID. */
	@JsonProperty("target_dcm_id")
	private String targetDcmId;
	
	/** The Target Data Property Definition (DPD) Name List. */
	@JsonProperty("target_dpd_name_list")
	private List<String> targetDpdNameList;
	
	/** The Additional Data Property Definition (DPD) Name List. */
	@JsonProperty("additional_dpd_name_list")
	private List<String> additionalDpdNameList;
	
	/**
	 * Get the drmId
	 * 
	 * @return the drmId
	 */
	public String getDrmId() {
		return drmId;
	}
	
	/**
	 * Set the drmId
	 * 
	 * @param drmId the drmId to set
	 */
	public void setDrmId(String drmId) {
		this.drmId = drmId;
	}
	
	/**
	 * Get the dataDictionaryId
	 * 
	 * @return the dataDictionaryId
	 */
	public String getDataDictionaryId() {
		return dataDictionaryId;
	}
	
	/**
	 * Set the dataDictionaryId
	 * 
	 * @param dataDictionaryId the dataDictionaryId to set
	 */
	public void setDataDictionaryId(String dataDictionaryId) {
		this.dataDictionaryId = dataDictionaryId;
	}
	
	/**
	 * Get the dcmId
	 * 
	 * @return the dcmId
	 */
	public String getDcmId() {
		return dcmId;
	}
	
	/**
	 * Set the dcmId
	 * 
	 * @param dcmId the dcmId to set
	 */
	public void setDcmId(String dcmId) {
		this.dcmId = dcmId;
	}
	
	/**
	 * Get the dpdNameList
	 * 
	 * @return the dpdNameList
	 */
	public List<String> getDpdNameList() {
		return dpdNameList;
	}
	
	/**
	 * Set the dpdNameList
	 * 
	 * @param dpdNameList the dpdNameList to set
	 */
	public void setDpdNameList(List<String> dpdNameList) {
		this.dpdNameList = dpdNameList;
	}
	
	/**
	 * Get the targetDcmId
	 * 
	 * @return the targetDcmId
	 */
	public String getTargetDcmId() {
		return targetDcmId;
	}
	
	/**
	 * Set the targetDcmId
	 * 
	 * @param targetDcmId the targetDcmId to set
	 */
	public void setTargetDcmId(String targetDcmId) {
		this.targetDcmId = targetDcmId;
	}
	
	/**
	 * Get the targetDpdNameList
	 * 
	 * @return the targetDpdNameList
	 */
	public List<String> getTargetDpdNameList() {
		return targetDpdNameList;
	}
	
	/**
	 * Set the targetDpdNameList
	 * 
	 * @param targetDpdNameList the targetDpdNameList to set
	 */
	public void setTargetDpdNameList(List<String> targetDpdNameList) {
		this.targetDpdNameList = targetDpdNameList;
	}
	
	/**
	 * Get the additionalDpdNameList
	 * 
	 * @return the additionalDpdNameList
	 */
	public List<String> getAdditionalDpdNameList() {
		return additionalDpdNameList;
	}
	
	/**
	 * Set the additionalDpdNameList
	 * 
	 * @param additionalDpdNameList the additionalDpdNameList to set
	 */
	public void setAdditionalDpdNameList(List<String> additionalDpdNameList) {
		this.additionalDpdNameList = additionalDpdNameList;
	}
	
}
