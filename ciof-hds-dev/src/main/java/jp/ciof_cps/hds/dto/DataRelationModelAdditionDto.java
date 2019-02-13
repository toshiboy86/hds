/**
 * DataRelationModelAdditionDto.java
 * (C) 2019. Industrial Value Chain Initiative
 */
package jp.ciof_cps.hds.dto;

/**
 * This class is the DTO class for Data Relation Model (DRM) Addition.
 */
public class DataRelationModelAdditionDto {
	
	/** The Data Relation Model Addition (DRM) Id. */
	private Integer dataRelationModelAdditionId;
	
	/** The Data Relation Model (DRM) ID. */
	private String drmId;
	
	/** The Target Data Component Model (DCM) ID. */
	private String targetDcmId;
	
	/** The Additional Data Property Definition (DPD). */
	private DataPropertyDefinitionDto additionalDpd;

	/**
	 * Get the dataRelationModelAdditionId.
	 * 
	 * @return the dataRelationModelAdditionId
	 */
	public Integer getDataRelationModelAdditionId() {
		return dataRelationModelAdditionId;
	}

	/**
	 * Set the dataRelationModelAdditionId.
	 *
	 * @param dataRelationModelAdditionId the dataRelationModelAdditionId to set
	 */
	public void setDataRelationModelAdditionId(Integer dataRelationModelAdditionId) {
		this.dataRelationModelAdditionId = dataRelationModelAdditionId;
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
	 * Get the additionalDpd.
	 * 
	 * @return the additionalDpd
	 */
	public DataPropertyDefinitionDto getAdditionalDpd() {
		return additionalDpd;
	}

	/**
	 * Set the additionalDpd.
	 *
	 * @param additionalDpd the additionalDpd to set
	 */
	public void setAdditionalDpd(DataPropertyDefinitionDto additionalDpd) {
		this.additionalDpd = additionalDpd;
	}

}
