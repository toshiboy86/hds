/**
 * DataComponentModelEntity.java
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
 * The persistent class for the Data Component Model (DCM) database table.
 */
@Entity
@Table(name="data_component_model")
@NamedQuery(name="DataComponentModelEntity.findAll", query="SELECT d FROM DataComponentModelEntity d")
public class DataComponentModelEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final int COLUMN_LENGTH = 256;

	/** The Data Component Model (DCM) ID. */
	@Id
	@Column(name="dcm_id", unique=true, nullable=false, length=COLUMN_LENGTH)
	private String dcmId;

	/** The Author. */
	@Column(nullable=false, length=COLUMN_LENGTH)
	private String author;

	/** The Category Dictionary ID. */
	@Column(name="category_dictionary_id", nullable=false, length=COLUMN_LENGTH)
	private String categoryDictionaryId;

	/** The Component Category ID. */
	@Column(name="component_category_id", nullable=false, length=COLUMN_LENGTH)
	private String componentCategoryId;

	/** The Creation Date. */
	@Column(name="creation_date", nullable=false, length=COLUMN_LENGTH)
	private String creationDate;

	/** The Data Dictionary ID. */
	@Column(name="data_dictionary_id", nullable=false, length=COLUMN_LENGTH)
	private String dataDictionaryId;

	/** The Data Component Model (DCM) Description. */
	@Column(name="dcm_description", nullable=false, length=COLUMN_LENGTH)
	private String dcmDescription;

	/** The Data Component Model (DCM) Name. */
	@Column(name="dcm_name", nullable=false, length=COLUMN_LENGTH)
	private String dcmName;

	/**
	 * The constructor.
	 */
	public DataComponentModelEntity() {
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
	 * Get the author.
	 * 
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * Set the author.
	 *
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * Get the categoryDictionaryId.
	 * 
	 * @return the categoryDictionaryId
	 */
	public String getCategoryDictionaryId() {
		return categoryDictionaryId;
	}

	/**
	 * Set the categoryDictionaryId.
	 *
	 * @param categoryDictionaryId the categoryDictionaryId to set
	 */
	public void setCategoryDictionaryId(String categoryDictionaryId) {
		this.categoryDictionaryId = categoryDictionaryId;
	}

	/**
	 * Get the componentCategoryId.
	 * 
	 * @return the componentCategoryId
	 */
	public String getComponentCategoryId() {
		return componentCategoryId;
	}

	/**
	 * Set the componentCategoryId.
	 *
	 * @param componentCategoryId the componentCategoryId to set
	 */
	public void setComponentCategoryId(String componentCategoryId) {
		this.componentCategoryId = componentCategoryId;
	}

	/**
	 * Get the creationDate.
	 * 
	 * @return the creationDate
	 */
	public String getCreationDate() {
		return creationDate;
	}

	/**
	 * Set the creationDate.
	 *
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
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
	 * Get the dcmDescription.
	 * 
	 * @return the dcmDescription
	 */
	public String getDcmDescription() {
		return dcmDescription;
	}

	/**
	 * Set the dcmDescription.
	 *
	 * @param dcmDescription the dcmDescription to set
	 */
	public void setDcmDescription(String dcmDescription) {
		this.dcmDescription = dcmDescription;
	}

	/**
	 * Get the dcmName.
	 * 
	 * @return the dcmName
	 */
	public String getDcmName() {
		return dcmName;
	}

	/**
	 * Set the dcmName.
	 *
	 * @param dcmName the dcmName to set
	 */
	public void setDcmName(String dcmName) {
		this.dcmName = dcmName;
	}

}
