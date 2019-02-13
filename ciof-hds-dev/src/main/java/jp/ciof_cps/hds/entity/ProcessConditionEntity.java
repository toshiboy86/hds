/**
 * ProcessConditionEntity.java
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
 * The persistent class for the Process Condition database table.
 */
@Entity
@Table(name="process_condition")
@NamedQuery(name="ProcessConditionEntity.findAll", query="SELECT p FROM ProcessConditionEntity p")
public class ProcessConditionEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final int COLUMN_LENGTH = 256;

	/** The Process Condition ID. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="process_condition_id", unique=true, nullable=false)
	private Integer processConditionId;

	/** The Condition. */
	@Column(nullable=false, length=COLUMN_LENGTH)
	private String condition;

	/** The Condition Type. */
	@Column(name="condition_type", nullable=false, length=COLUMN_LENGTH)
	private String conditionType;

	/** The Process Component Model (PCM) ID. */
	@Column(name="pcm_id", nullable=false, length=COLUMN_LENGTH)
	private String pcmId;

	/** The Target Data. */
	@Column(name="target_data", nullable=false, length=COLUMN_LENGTH)
	private String targetData;

	/** The Process Condition Data Type. */
	@Column(name="process_condition_data_type", nullable=false)
	private Integer processConditionDataType;

	/**
	 * The constructor.
	 */
	public ProcessConditionEntity() {
	}

	/**
	 * Get the processConditionId.
	 * 
	 * @return the processConditionId
	 */
	public Integer getProcessConditionId() {
		return processConditionId;
	}

	/**
	 * Set the processConditionId.
	 *
	 * @param processConditionId the processConditionId to set
	 */
	public void setProcessConditionId(Integer processConditionId) {
		this.processConditionId = processConditionId;
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

	/**
	 * Get the processConditionDataType.
	 * 
	 * @return the processConditionDataType
	 */
	public Integer getProcessConditionDataType() {
		return processConditionDataType;
	}

	/**
	 * Set the processConditionDataType.
	 *
	 * @param processConditionDataType the processConditionDataType to set
	 */
	public void setProcessConditionDataType(Integer processConditionDataType) {
		this.processConditionDataType = processConditionDataType;
	}

	
}
