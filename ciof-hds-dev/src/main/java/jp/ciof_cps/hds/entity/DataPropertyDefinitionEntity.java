/**
 * DataPropertyDefinitionEntity.java
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
 * The persistent class for the Data Property Definition (DPD) database table.
 */
@Entity
@Table(name="data_property_definition")
@NamedQuery(name="DataPropertyDefinitionEntity.findAll", query="SELECT d FROM DataPropertyDefinitionEntity d")
public class DataPropertyDefinitionEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final int COLUMN_LENGTH = 256;
	
	/** The Data Property Definition (DPD) ID. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="dpd_id", unique=true, nullable=false)
	private Integer dpdId;

	/** The Data Component Model (DCM) ID. */
	@Column(name="dcm_id", nullable=false, length=COLUMN_LENGTH)
	private String dcmId;

	/** The Default Value. */
	@Column(name="default_value", nullable=false, length=COLUMN_LENGTH)
	private String defaultValue;

	/** The Data Property Definition (DPD) Description. */
	@Column(name="dpd_description", nullable=false, length=COLUMN_LENGTH)
	private String dpdDescription;

	/** The Data Property Definition (DPD) Name. */
	@Column(name="dpd_name", nullable=false, length=COLUMN_LENGTH)
	private String dpdName;

	/** The Data Property Definition (DPD) Type. */
	@Column(name="dpd_type", nullable=false, length=COLUMN_LENGTH)
	private String dpdType;

	/** Whether this DPD is list. */
	@Column(name="is_list", nullable=false)
	private Boolean isList;

	/** Whether this DPD is primary key. */
	@Column(name="is_primary_key", nullable=false)
	private Boolean isPrimaryKey;

	/** Whether this DPD is required. */
	@Column(name="is_required", nullable=false)
	private Boolean isRequired;

	/** The Sample Value. */
	@Column(name="sample_value", nullable=false, length=COLUMN_LENGTH)
	private String sampleValue;

	/**
	 * The constructor.
	 */
	public DataPropertyDefinitionEntity() {
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
	 * Get the defaultValue.
	 * 
	 * @return the defaultValue
	 */
	public String getDefaultValue() {
		return defaultValue;
	}

	/**
	 * Set the defaultValue.
	 *
	 * @param defaultValue the defaultValue to set
	 */
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	/**
	 * Get the dpdDescription.
	 * 
	 * @return the dpdDescription
	 */
	public String getDpdDescription() {
		return dpdDescription;
	}

	/**
	 * Set the dpdDescription.
	 *
	 * @param dpdDescription the dpdDescription to set
	 */
	public void setDpdDescription(String dpdDescription) {
		this.dpdDescription = dpdDescription;
	}

	/**
	 * Get the dpdName.
	 * 
	 * @return the dpdName
	 */
	public String getDpdName() {
		return dpdName;
	}

	/**
	 * Set the dpdName.
	 *
	 * @param dpdName the dpdName to set
	 */
	public void setDpdName(String dpdName) {
		this.dpdName = dpdName;
	}

	/**
	 * Get the dpdType.
	 * 
	 * @return the dpdType
	 */
	public String getDpdType() {
		return dpdType;
	}

	/**
	 * Set the dpdType.
	 *
	 * @param dpdType the dpdType to set
	 */
	public void setDpdType(String dpdType) {
		this.dpdType = dpdType;
	}

	/**
	 * Get the isList.
	 * 
	 * @return the isList
	 */
	public Boolean getIsList() {
		return isList;
	}

	/**
	 * Set the isList.
	 *
	 * @param isList the isList to set
	 */
	public void setIsList(Boolean isList) {
		this.isList = isList;
	}

	/**
	 * Get the isPrimaryKey.
	 * 
	 * @return the isPrimaryKey
	 */
	public Boolean getIsPrimaryKey() {
		return isPrimaryKey;
	}

	/**
	 * Set the isPrimaryKey.
	 *
	 * @param isPrimaryKey the isPrimaryKey to set
	 */
	public void setIsPrimaryKey(Boolean isPrimaryKey) {
		this.isPrimaryKey = isPrimaryKey;
	}

	/**
	 * Get the isRequired.
	 * 
	 * @return the isRequired
	 */
	public Boolean getIsRequired() {
		return isRequired;
	}

	/**
	 * Set the isRequired.
	 *
	 * @param isRequired the isRequired to set
	 */
	public void setIsRequired(Boolean isRequired) {
		this.isRequired = isRequired;
	}

	/**
	 * Get the sampleValue.
	 * 
	 * @return the sampleValue
	 */
	public String getSampleValue() {
		return sampleValue;
	}

	/**
	 * Set the sampleValue.
	 *
	 * @param sampleValue the sampleValue to set
	 */
	public void setSampleValue(String sampleValue) {
		this.sampleValue = sampleValue;
	}

}
