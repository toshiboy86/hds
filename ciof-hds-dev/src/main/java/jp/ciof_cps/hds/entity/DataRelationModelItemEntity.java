/**
 * DataRelationModelItemEntity.java
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
 * The persistent class for the Data Relation Model (DRM) Item database table.
 */
@Entity
@Table(name="data_relation_model_item")
@NamedQuery(name="DataRelationModelItemEntity.findAll", query="SELECT d FROM DataRelationModelItemEntity d")
public class DataRelationModelItemEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final int COLUMN_LENGTH = 256;
	
	/** The Data Relation Model (DRM) Item ID. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="data_relation_model_item_id", unique=true, nullable=false)
	private Integer dataRelationModelItemId;

	/** The Data Property Definition (DPD) ID. */
	@Column(name="dpd_id", nullable=false)
	private Integer dpdId;

	/** The Data Relation Model (DRM) ID. */
	@Column(name="drm_id", nullable=false, length=COLUMN_LENGTH)
	private String drmId;

	/** The Target Data Property Definition (DPD) ID. */
	@Column(name="target_dpd_id", nullable=false)
	private Integer targetDpdId;

	/**
	 * The constructor.
	 */
	public DataRelationModelItemEntity() {
	}

	/**
	 * Get the dataRelationModelItemId.
	 * 
	 * @return the dataRelationModelItemId
	 */
	public Integer getDataRelationModelItemId() {
		return dataRelationModelItemId;
	}

	/**
	 * Set the dataRelationModelItemId.
	 *
	 * @param dataRelationModelItemId the dataRelationModelItemId to set
	 */
	public void setDataRelationModelItemId(Integer dataRelationModelItemId) {
		this.dataRelationModelItemId = dataRelationModelItemId;
	}

	/**
	 * Get the dpdId.
	 * 
	 * @return the dpdId
	 */
	public Integer getDpdId() {
		return dpdId;
	}

	/**
	 * Set the dpdId.
	 *
	 * @param dpdId the dpdId to set
	 */
	public void setDpdId(Integer dpdId) {
		this.dpdId = dpdId;
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
	 * Get the targetDpdId.
	 * 
	 * @return the targetDpdId
	 */
	public Integer getTargetDpdId() {
		return targetDpdId;
	}

	/**
	 * Set the targetDpdId.
	 *
	 * @param targetDpdId the targetDpdId to set
	 */
	public void setTargetDpdId(Integer targetDpdId) {
		this.targetDpdId = targetDpdId;
	}

}
