/**
 * ProcessComponentModelOperationDto.java
 * (C) 2019. Industrial Value Chain Initiative
 */
package jp.ciof_cps.hds.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class is the DTO class for Process Component Model (PCM) Operation.
 */
public class ProcessComponentModelOperationDto {
	
	/** The Process Component Model (PCM) Operation Type. */
	@JsonProperty("pcm_operation_type")
	private String pcmOperationType;
	
	/** The Process Component Model (PCM) ID. */
	@JsonProperty("pcm_id")
	private String pcmId;
	
	/** The Service Dictionary ID. */
	@JsonProperty("service_dictionary_id")
	private String serviceDictionaryId;
	
	/** The Remarks. */
	@JsonProperty("remarks")
	private String remarks;

	/**
	 * Get the pcmOperationType.
	 * 
	 * @return the pcmOperationType
	 */
	public String getPcmOperationType() {
		return pcmOperationType;
	}

	/**
	 * Set the pcmOperationType.
	 *
	 * @param pcmOperationType the pcmOperationType to set
	 */
	public void setPcmOperationType(String pcmOperationType) {
		this.pcmOperationType = pcmOperationType;
	}

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
	 * @param remarks
	 *            the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}
