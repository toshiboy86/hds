/**
 * DcmInformationDesignDto.java
 * (C) 2019. Industrial Value Chain Initiative
 */
package jp.ciof_cps.hds.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class is the DTO class for Data Component Model (DCM) Information Design.
 */
public class DcmInformationDesignDto {
	
	/** The Information Design Type. */
	@JsonProperty("information_design_type")
	private String informationDesignType;
	
	/** The Information Design ID. */
	@JsonProperty("information_design_id")
	private String informationDesignId;
	
	/** The Information Design Name. */
	@JsonProperty("information_design_name")
	private String informationDesignName;
	
	/** The Remarks. */
	@JsonProperty("remarks")
	private String remarks;

	/**
	 * Get the informationDesignType.
	 * 
	 * @return the informationDesignType
	 */
	public String getInformationDesignType() {
		return informationDesignType;
	}

	/**
	 * Set the informationDesignType.
	 *
	 * @param informationDesignType the informationDesignType to set
	 */
	public void setInformationDesignType(String informationDesignType) {
		this.informationDesignType = informationDesignType;
	}

	/**
	 * Get the informationDesignId.
	 * 
	 * @return the informationDesignId
	 */
	public String getInformationDesignId() {
		return informationDesignId;
	}

	/**
	 * Set the informationDesignId.
	 *
	 * @param informationDesignId the informationDesignId to set
	 */
	public void setInformationDesignId(String informationDesignId) {
		this.informationDesignId = informationDesignId;
	}

	/**
	 * Get the informationDesignName.
	 * 
	 * @return the informationDesignName
	 */
	public String getInformationDesignName() {
		return informationDesignName;
	}

	/**
	 * Set the informationDesignName.
	 *
	 * @param informationDesignName the informationDesignName to set
	 */
	public void setInformationDesignName(String informationDesignName) {
		this.informationDesignName = informationDesignName;
	}

	/**
	 * Get the remarks.
	 * 
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * Set the remarks.
	 *
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}
