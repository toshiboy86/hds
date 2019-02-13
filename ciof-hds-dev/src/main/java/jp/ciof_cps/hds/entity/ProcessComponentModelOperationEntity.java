/**
 * ProcessComponentModelOperationEntity.java
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
 * The persistent class for the Process Component Model (PCM) Operation database table.
 */
@Entity
@Table(name="process_component_model_operation")
@NamedQuery(name="ProcessComponentModelOperationEntity.findAll", query="SELECT p FROM ProcessComponentModelOperationEntity p")
public class ProcessComponentModelOperationEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final int COLUMN_LENGTH = 256;

	/** The Process Component Model (PCM) Operation ID. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pcm_operation_id", unique=true, nullable=false)
	private Integer pcmOperationId;

	/** The Data Component Model (DCM) ID. */
	@Column(name="dcm_id", nullable=false, length=COLUMN_LENGTH)
	private String dcmId;

	/** The Process Component Model (PCM) ID. */
	@Column(name="pcm_id", nullable=false, length=COLUMN_LENGTH)
	private String pcmId;

	/** The Process Component Model (PCM) Operation Type. */
	@Column(name="pcm_operation_type", nullable=false, length=COLUMN_LENGTH)
	private String pcmOperationType;

	/** The Remarks. */
	@Column(nullable=true, length=COLUMN_LENGTH)
	private String remarks;

	/** The Service Dictionary ID. */
	@Column(name="service_dictionary_id", nullable=false, length=COLUMN_LENGTH)
	private String serviceDictionaryId;

	/**
	 * The constructor.
	 */
	public ProcessComponentModelOperationEntity() {
	}

	/**
	 * Get the pcmOperationId.
	 * 
	 * @return the pcmOperationId
	 */
	public Integer getPcmOperationId() {
		return pcmOperationId;
	}

	/**
	 * Set the pcmOperationId.
	 *
	 * @param pcmOperationId the pcmOperationId to set
	 */
	public void setPcmOperationId(Integer pcmOperationId) {
		this.pcmOperationId = pcmOperationId;
	}

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

}
