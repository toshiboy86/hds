/**
 * DataRelationModelEntity.java
 * (C) 2019. Industrial Value Chain Initiative
 */
package jp.ciof_cps.hds.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the Data Relation Model (DRM) database table.
 */
@Entity
@Table(name="data_relation_model")
@NamedQuery(name="DataRelationModelEntity.findAll", query="SELECT d FROM DataRelationModelEntity d")
public class DataRelationModelEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final int COLUMN_LENGTH = 256;
	
	/** The Data Relation Model (DRM) ID. */
	@Id
	@Column(name="drm_id", unique=true, nullable=false, length=COLUMN_LENGTH)
	private String drmId;

	/** The Data Dictionary ID. */
	@Column(name="data_dictionary_id", nullable=false, length=COLUMN_LENGTH)
	private String dataDictionaryId;

	/** The Data Component Model (DCM) ID. */
	@Column(name="dcm_id", nullable=false, length=COLUMN_LENGTH)
	private String dcmId;

	/** The Target Data Component Model (DCM) ID. */
	@Column(name="target_dcm_id", nullable=false, length=COLUMN_LENGTH)
	private String targetDcmId;

	/**
	 * The constructor.
	 */
	public DataRelationModelEntity() {
	}

	/**
	 * Get the drmId.
	 * 
	 * @return the drmId
	 */
	public String getDrmId() {
		return drmId;
	}

	/**
	 * Set the drmId.
	 *
	 * @param drmId the drmId to set
	 */
	public void setDrmId(String drmId) {
		this.drmId = drmId;
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
	 * Get the targetDcmId.
	 * 
	 * @return the targetDcmId
	 */
	public String getTargetDcmId() {
		return targetDcmId;
	}

	/**
	 * Set the targetDcmId.
	 *
	 * @param targetDcmId the targetDcmId to set
	 */
	public void setTargetDcmId(String targetDcmId) {
		this.targetDcmId = targetDcmId;
	}

}
