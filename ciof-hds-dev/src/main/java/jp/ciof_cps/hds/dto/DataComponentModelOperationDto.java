/**
 * DataCompomentModelOperationDto.java
 * (C) 2019. Industrial Value Chain Initiative
 */
package jp.ciof_cps.hds.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class is the DTO class for Data Component Model (DCM) Operation.
 */
public class DataComponentModelOperationDto {
	
	/** The Data Component Model (DCM) ID. */
	@JsonProperty("dcm_id")
	private String dcmId;
	
	/** The Data Dictionary Data Component Model (DCM) ID. */
	@JsonProperty("data_dictionary_id")
	private String dataDictionaryId;
	
	/** The Operation List. */
	@JsonProperty("operation_list")
	private List<String> operationList;
	
	/** The Remarks. */
	@JsonProperty("remarks")
	private String remarks;

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
	 * Get the dataDictionaryId.
	 * 
	 * @return the dataDictionaryId
	 */
	public String getDataDictionaryId() {
		return dataDictionaryId;
	}

	/**
	 * Set the dataDictionaryId.
	 *
	 * @param dataDictionaryId the dataDictionaryId to set
	 */
	public void setDataDictionaryId(String dataDictionaryId) {
		this.dataDictionaryId = dataDictionaryId;
	}

	/**
	 * Get the operationList.
	 * 
	 * @return the operationList
	 */
	public List<String> getOperationList() {
		return operationList;
	}

	/**
	 * Set the operationList.
	 *
	 * @param operationList the operationList to set
	 */
	public void setOperationList(List<String> operationList) {
		this.operationList = operationList;
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
