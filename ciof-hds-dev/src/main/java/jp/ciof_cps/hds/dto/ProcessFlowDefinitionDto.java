/**
 * ProcessFlowDefinitionDto.java
 * (C) 2019. Industrial Value Chain Initiative
 */
package jp.ciof_cps.hds.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class is the DTO class for Process Flow Definition (PFD).
 */
public class ProcessFlowDefinitionDto {

	@JsonProperty("operation_content")
	private String operationContent;

	@JsonProperty("target_data")
	private String targetData;

	/**
	 * Get the operationContent.
	 * 
	 * @return the operationContent
	 */
	public String getOperationContent() {
		return operationContent;
	}

	/**
	 * Set the operationContent.
	 *
	 * @param operationContent the operationContent to set
	 */
	public void setOperationContent(String operationContent) {
		this.operationContent = operationContent;
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

	
