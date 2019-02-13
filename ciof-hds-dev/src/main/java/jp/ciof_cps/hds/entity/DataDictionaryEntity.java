/**
 * DataDictionaryEntity.java
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
 * The persistent class for the Data Dictionary database table.
 */
@Entity
@Table(name="data_dictionary")
@NamedQuery(name="DataDictionaryEntity.findAll", query="SELECT d FROM DataDictionaryEntity d")
public class DataDictionaryEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final int COLUMN_LENGTH = 256;
	
	/** The Data Dictionary ID. */
	@Id
	@Column(name="data_dictionary_id", unique=true, nullable=false, length=COLUMN_LENGTH)
	private String dataDictionaryId;

	/** The Data Dictionary Description. */
	@Column(name="data_dictionary_description", nullable=false, length=COLUMN_LENGTH)
	private String dataDictionaryDescription;

	/** The Data Dictionary Name. */
	@Column(name="data_dictionary_name", nullable=false, length=COLUMN_LENGTH)
	private String dataDictionaryName;

	/** The Data Dictionary Type. */
	@Column(name="data_dictionary_type", nullable=false, length=COLUMN_LENGTH)
	private String dataDictionaryType;

	/** The Owner ID. */
	@Column(name="owner_id", nullable=false, length=COLUMN_LENGTH)
	private String ownerId;

	/** The Previous Data Dictionary ID. */
	@Column(name="previous_data_dictionary_id", nullable=true, length=COLUMN_LENGTH)
	private String previousDataDictionaryId;

	/** The Version. */
	@Column(nullable=false)
	private Integer version;

	/**
	 * The constructor.
	 */
	public DataDictionaryEntity() {
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
	 * Get the dataDictionaryDescription.
	 * 
	 * @return the dataDictionaryDescription
	 */
	public String getDataDictionaryDescription() {
		return dataDictionaryDescription;
	}

	/**
	 * Set the dataDictionaryDescription.
	 *
	 * @param dataDictionaryDescription the dataDictionaryDescription to set
	 */
	public void setDataDictionaryDescription(String dataDictionaryDescription) {
		this.dataDictionaryDescription = dataDictionaryDescription;
	}

	/**
	 * Get the dataDictionaryName.
	 * 
	 * @return the dataDictionaryName
	 */
	public String getDataDictionaryName() {
		return dataDictionaryName;
	}

	/**
	 * Set the dataDictionaryName.
	 *
	 * @param dataDictionaryName the dataDictionaryName to set
	 */
	public void setDataDictionaryName(String dataDictionaryName) {
		this.dataDictionaryName = dataDictionaryName;
	}

	/**
	 * Get the dataDictionaryType.
	 * 
	 * @return the dataDictionaryType
	 */
	public String getDataDictionaryType() {
		return dataDictionaryType;
	}

	/**
	 * Set the dataDictionaryType.
	 *
	 * @param dataDictionaryType the dataDictionaryType to set
	 */
	public void setDataDictionaryType(String dataDictionaryType) {
		this.dataDictionaryType = dataDictionaryType;
	}

	/**
	 * Get the ownerId.
	 * 
	 * @return the ownerId
	 */
	public String getOwnerId() {
		return ownerId;
	}

	/**
	 * Set the ownerId.
	 *
	 * @param ownerId the ownerId to set
	 */
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	/**
	 * Get the previousDataDictionaryId.
	 * 
	 * @return the previousDataDictionaryId
	 */
	public String getPreviousDataDictionaryId() {
		return previousDataDictionaryId;
	}

	/**
	 * Set the previousDataDictionaryId.
	 *
	 * @param previousDataDictionaryId the previousDataDictionaryId to set
	 */
	public void setPreviousDataDictionaryId(String previousDataDictionaryId) {
		this.previousDataDictionaryId = previousDataDictionaryId;
	}

	/**
	 * Get the version.
	 * 
	 * @return the version
	 */
	public Integer getVersion() {
		return version;
	}

	/**
	 * Set the version.
	 *
	 * @param version the version to set
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}

}
