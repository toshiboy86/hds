/**
 * DataComponentModelOperationEntity.java
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
 * The persistent class for the Data Component Model (DCM) Operation database table.
 */
@Entity
@Table(name="data_component_model_operation")
@NamedQuery(name="DataComponentModelOperationEntity.findAll", query="SELECT d FROM DataComponentModelOperationEntity d")
public class DataComponentModelOperationEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final int COLUMN_LENGTH = 256;
	
	/** The Data Component Model (DCM) Operation ID. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="dcm_operation_id", unique=true, nullable=false)
	private Integer dcmOperationId;

	/** The Data Dictionary ID. */
	@Column(name="data_dictionary_id", nullable=false, length=COLUMN_LENGTH)
	private String dataDictionaryId;

	/** The Data Component Model (DCM) ID. */
	@Column(name="dcm_id", nullable=false, length=COLUMN_LENGTH)
	private String dcmId;

	/** The Process Component Model (PCM) ID. */
	@Column(name="pcm_id", nullable=false, length=COLUMN_LENGTH)
	private String pcmId;

	@Column(nullable=true, length=COLUMN_LENGTH)
	private String remarks;

	/**
	 * The constructor.
	 */
	public DataComponentModelOperationEntity() {
	}

	/**
	 * Get the dcmOperationId.
	 * 
	 * @return the dcmOperationId
	 */
	public Integer getDcmOperationId() {
		return dcmOperationId;
	}

	/**
	 * Set the dcmOperationId.
	 *
	 * @param dcmOperationId the dcmOperationId to set
	 */
	public void setDcmOperationId(Integer dcmOperationId) {
		this.dcmOperationId = dcmOperationId;
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
