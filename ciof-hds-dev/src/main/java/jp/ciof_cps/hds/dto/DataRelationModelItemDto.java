/**
 * DataRelationModelItemDto.java
 * (C) 2019. Industrial Value Chain Initiative
 */
package jp.ciof_cps.hds.dto;

/**
 * This class is the DTO class for Data Relation Model (DRM) Item.
 */
public class DataRelationModelItemDto {
	
	/** The Data Relation Model (DRM) Item ID. */
	private Integer dataRelationModelItemId;
	
	/** The Data Relation Model (DRM) ID. */
	private String drmId;
	
	/** The Data Component Model (DCM) ID. */
	private String dcmId;
	
	/** The Data Property Definition (DPD). */
	private DataPropertyDefinitionDto dpd;
	
	/** The Target Data Component Model (DCM) ID. */
	private String targetDcmId;
	
	/** The Target Data Property Definition (DPD). */
	private DataPropertyDefinitionDto targetDpd;

	/**
	 * Get the dataRelationModelItemId.
	 * 
	 * @return the dataRelationModelItemId
	 */
	public Integer getDataRelationModelItemId() {
		return dataRelationModelItemId;
	}

	/**
	 * Set the dataRelationModelItemId.
	 *
	 * @param dataRelationModelItemId the dataRelationModelItemId to set
	 */
	public void setDataRelationModelItemId(Integer dataRelationModelItemId) {
		this.dataRelationModelItemId = dataRelationModelItemId;
	}

	/**
	 * Get the drmId.
	 * 
	 * @return the drmId
	 */
	public String getDrmId() {
		return drmId;
	}

	/**
	 * Set the drmId.
	 *
	 * @param drmId the drmId to set
	 */
	public void setDrmId(String drmId) {
		this.drmId = drmId;
	}

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
	 * Get the dpd.
	 * 
	 * @return the dpd
	 */
	public DataPropertyDefinitionDto getDpd() {
		return dpd;
	}

	/**
	 * Set the dpd.
	 *
	 * @param dpd the dpd to set
	 */
	public void setDpd(DataPropertyDefinitionDto dpd) {
		this.dpd = dpd;
	}

	/**
	 * Get the targetDcmId.
	 * 
	 * @return the targetDcmId
	 */
	public String getTargetDcmId() {
		return targetDcmId;
	}

	/**
	 * Set the targetDcmId.
	 *
	 * @param targetDcmId the targetDcmId to set
	 */
	public void setTargetDcmId(String targetDcmId) {
		this.targetDcmId = targetDcmId;
	}

	/**
	 * Get the targetDpd.
	 * 
	 * @return the targetDpd
	 */
	public DataPropertyDefinitionDto getTargetDpd() {
		return targetDpd;
	}

	/**
	 * Set the targetDpd.
	 *
	 * @param targetDpd the targetDpd to set
	 */
	public void setTargetDpd(DataPropertyDefinitionDto targetDpd) {
		this.targetDpd = targetDpd;
	}

}
