/**
 * ProcessEventEntity.java
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
 * The persistent class for the Process Event database table.
 */
@Entity
@Table(name="process_event")
@NamedQuery(name="ProcessEventEntity.findAll", query="SELECT p FROM ProcessEventEntity p")
public class ProcessEventEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final int COLUMN_LENGTH = 256;

	/** The Process Event ID. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="process_event_id", unique=true, nullable=false)
	private Integer processEventId;

	/** The Condition. */
	@Column(nullable=false, length=COLUMN_LENGTH)
	private String condition;

	/** The Event And Condition Mode (ECM) ID. */
	@Column(name="ecm_id", nullable=false, length=COLUMN_LENGTH)
	private String ecmId;

	/** The Process Component Model (PCM) ID. */
	@Column(name="pcm_id", nullable=false, length=COLUMN_LENGTH)
	private String pcmId;

	/** The Process Event Type. */
	@Column(name="process_event_type", nullable=false, length=COLUMN_LENGTH)
	private String processEventType;

	/** The Target Data. */
	@Column(name="target_data", nullable=false, length=COLUMN_LENGTH)
	private String targetData;

	/**
	 * The constructor.
	 */
	public ProcessEventEntity() {
	}

	/**
	 * Get the processEventId.
	 * 
	 * @return the processEventId
	 */
	public Integer getProcessEventId() {
		return processEventId;
	}

	/**
	 * Set the processEventId.
	 *
	 * @param processEventId the processEventId to set
	 */
	public void setProcessEventId(Integer processEventId) {
		this.processEventId = processEventId;
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
	 * Get the ecmId.
	 * 
	 * @return the ecmId
	 */
	public String getEcmId() {
		return ecmId;
	}

	/**
	 * Set the ecmId.
	 *
	 * @param ecmId the ecmId to set
	 */
	public void setEcmId(String ecmId) {
		this.ecmId = ecmId;
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
