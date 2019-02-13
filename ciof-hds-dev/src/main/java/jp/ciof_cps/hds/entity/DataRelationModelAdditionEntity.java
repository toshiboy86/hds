/**
 * DataRelationModelAdditionEntity.java
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
 * The persistent class for the Data Relation Model (DRM) Addition database table.
 */
@Entity
@Table(name="data_relation_model_addition")
@NamedQuery(name="DataRelationModelAdditionEntity.findAll", query="SELECT d FROM DataRelationModelAdditionEntity d")
public class DataRelationModelAdditionEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final int COLUMN_LENGTH = 256;
	
	/** The Data Relation Model (DRM) Addition ID. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="data_relation_model_addition_id", unique=true, nullable=false)
	private Integer dataRelationModelAdditionId;

	/** The Additional Data Property Definition (DPD) ID. */
	@Column(name="additional_dpd_id", nullable=false)
	private Integer additionalDpdId;

	/** The Data Relation Model (DRM) ID. */
	@Column(name="drm_id", nullable=false, length=COLUMN_LENGTH)
	private String drmId;

	/**
	 * The constructor.
	 */
	public DataRelationModelAdditionEntity() {
	}

	/**
	 * Get the dataRelationModelAdditionId.
	 * 
	 * @return the dataRelationModelAdditionId
	 */
	public Integer getDataRelationModelAdditionId() {
		return dataRelationModelAdditionId;
	}

	/**
	 * Set the dataRelationModelAdditionId.
	 *
	 * @param dataRelationModelAdditionId the dataRelationModelAdditionId to set
	 */
	public void setDataRelationModelAdditionId(Integer dataRelationModelAdditionId) {
		this.dataRelationModelAdditionId = dataRelationModelAdditionId;
	}

	/**
	 * Get the additionalDpdId.
	 * 
	 * @return the additionalDpdId
	 */
	public Integer getAdditionalDpdId() {
		return additionalDpdId;
	}

	/**
	 * Set the additionalDpdId.
	 *
	 * @param additionalDpdId the additionalDpdId to set
	 */
	public void setAdditionalDpdId(Integer additionalDpdId) {
		this.additionalDpdId = additionalDpdId;
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

}
