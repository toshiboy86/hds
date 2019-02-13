/**
 * ProcessConditionDto.java
 * (C) 2019. Industrial Value Chain Initiative
 */
package jp.ciof_cps.hds.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class is the DTO class for Process Condition.
 */
public class ProcessConditionDto {

	@JsonProperty("condition_type")
	private String conditionType;

	@JsonProperty("condition")
	private String condition;

	@JsonProperty("target_data")
	private String targetData;

	/**
	 * Get the conditionType.
	 * 
	 * @return the conditionType
	 */
	public String getConditionType() {
		return conditionType;
	}

	/**
	 * Set the conditionType.
	 *
	 * @param conditionType the conditionType to set
	 */
	public void setConditionType(String conditionType) {
		this.conditionType = conditionType;
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
