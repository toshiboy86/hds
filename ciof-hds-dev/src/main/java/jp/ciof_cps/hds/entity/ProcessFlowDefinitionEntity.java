/**
 * ProcessFlowDefinitionEntity.java
 * (C) 2019. Industrial Value Chain Initiative
 */
package jp.ciof_cps.hds.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the Process Flow Definition (PFD) database table.
 */
@Entity
@Table(name="process_flow_definition")
@NamedQuery(name="ProcessFlowDefinitionEntity.findAll", query="SELECT p FROM ProcessFlowDefinitionEntity p")
public class ProcessFlowDefinitionEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final int COLUMN_LENGTH = 256;

	/** The Process Flow Definition (PFD) ID. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pfd_id", unique=true, nullable=false)
	private Integer pfdId;

	/** The Operation Content. */
	@Column(name="operation_content", nullable=false, length=COLUMN_LENGTH)
	private String operationContent;

	/** The Process Component Model (PCM) ID. */
	@Column(name="pcm_id", nullable=false, length=COLUMN_LENGTH)
	private String pcmId;

	/** The Target Data. */
	@Column(name="target_data", nullable=false, length=COLUMN_LENGTH)
	private String targetData;

	/**
	 * The constructor.
	 */
	public ProcessFlowDefinitionEntity() {
	}

	/**
	 * Get the pfdId.
	 * 
	 * @return the pfdId
	 */
	public Integer getPfdId() {
		return pfdId;
	}

	/**
	 * Set the pfdId.
	 *
	 * @param pfdId the pfdId to set
	 */
	public void setPfdId(Integer pfdId) {
		this.pfdId = pfdId;
	}

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
