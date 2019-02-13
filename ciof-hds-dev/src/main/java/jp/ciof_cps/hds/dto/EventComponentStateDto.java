/**
 * EventComponentStateDto.java
 * (C) 2019. Industrial Value Chain Initiative
 */
package jp.ciof_cps.hds.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class is the DTO class for Event Component State.
 */
public class EventComponentStateDto {
	
	/** The Data Component Model (DCM) ID. */
	@JsonProperty("dcm_id")
	private String dcmId;
	
	/** The State ID. */
	@JsonProperty("state_id")
	private String stateId;
	
	/** The Event and Condition Expression (ECE). */
	@JsonProperty("ece")
	private String ece;
	
	/** The Data Property Definition (DPD) Name. */
	@JsonProperty("dpd_name")
	private String dpdName;
	
	/** The Determination Method. */
	@JsonProperty("determination_method")
	private String determinationMethod;

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
	 * Get the stateId.
	 * 
	 * @return the stateId
	 */
	public String getStateId() {
		return stateId;
	}


	/**
	 * Set the stateId.
	 *
	 * @param stateId the stateId to set
	 */
	public void setStateId(String stateId) {
		this.stateId = stateId;
	}


	/**
	 * Get the ece.
	 * 
	 * @return the ece
	 */
	public String getEce() {
		return ece;
	}


	/**
	 * Set the ece.
	 *
	 * @param ece the ece to set
	 */
	public void setEce(String ece) {
		this.ece = ece;
	}


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
	 * Get the determinationMethod.
	 * 
	 * @return the determinationMethod
	 */
	public String getDeterminationMethod() {
		return determinationMethod;
	}


	/**
	 * Set the determinationMethod.
	 *
	 * @param determinationMethod the determinationMethod to set
	 */
	public void setDeterminationMethod(String determinationMethod) {
		this.determinationMethod = determinationMethod;
	}

}
