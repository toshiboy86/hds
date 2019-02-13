/**
 * UserOperationEventDto.java
 * (C) 2019. Industrial Value Chain Initiative
 */
package jp.ciof_cps.hds.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class is the DTO class for User Operation Event.
 */
public class UserOperationEventDto {
	
	@JsonProperty("operation_procedure_content")
	private String operationProcedureContent;
	
	@JsonProperty("target_object")
	private String targetObject;
	
	/**
	 * Get the operationProcedureContent
	 * 
	 * @return the operationProcedureContent
	 */
	public String getOperationProcedureContent() {
		return operationProcedureContent;
	}

	/**
	 * Set the operationProcedureContent
	 * 
	 * @param operationProcedureContent the operationProcedureContent to set
	 */
	public void setOperationProcedureContent(String operationProcedureContent) {
		this.operationProcedureContent = operationProcedureContent;
	}

	/**
	 * Get the targetObject
	 * 
	 * @return the targetObject
	 */
	public String getTargetObject() {
		return targetObject;
	}

	/**
	 * Set the targetObject
	 * 
	 * @param targetObject the targetObject to set
	 */
	public void setTargetObject(String targetObject) {
		this.targetObject = targetObject;
	}
	
}
