/**
 * ProcessEventDto.java
 * (C) 2019. Industrial Value Chain Initiative
 */
package jp.ciof_cps.hds.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class is the DTO class for Process Event.
 */
public class ProcessEventDto {
	
	/** The Process Component Model (PCM) ID. */
	@JsonProperty("pcm_id")
	private String pcmId;
	
	/** The Process Event Type. */
	@JsonProperty("process_event_type")
	private String processEventType;
	
	/** The Condition. */
	@JsonProperty("condition")
	private String condition;
	
	/** The Target Data. */
	@JsonProperty("target_data")
	private String targetData;

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
	 * Get the processEventType.
	 * 
	 * @return the processEventType
	 */
	public String getProcessEventType() {
		return processEventType;
	}

	/**
	 * Set the processEventType.
	 *
	 * @param processEventType the processEventType to set
	 */
	public void setProcessEventType(String processEventType) {
		this.processEventType = processEventType;
	}

	/**
	 * Get the condition.
	 * 
	 * @return the condition
	 */
	public String getCondition() {
		return condition;
	}

	/**
	 * Set the condition.
	 *
	 * @param condition the condition to set
	 */
	public void setCondition(String condition) {
		this.condition = condition;
	}

	/**
	 * Get the targetData.
	 * 
	 * @return the targetData
	 */
	public String getTargetData() {
		return targetData;
	}

	/**
	 * Set the targetData.
	 *
	 * @param targetData the targetData to set
	 */
	public void setTargetData(String targetData) {
		this.targetData = targetData;
	}

}
