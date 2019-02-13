/**
 * SpecificCategoryDictionaryEntity.java
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
 * The persistent class for the Specific Category Dictionary database table.
 */
@Entity
@Table(name="specific_category_dictionary")
@NamedQuery(name="SpecificCategoryDictionaryEntity.findAll", query="SELECT s FROM SpecificCategoryDictionaryEntity s")
public class SpecificCategoryDictionaryEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final int COLUMN_LENGTH = 256;

	/** The Category Dictionary ID. */
	@Id
	@Column(name="category_dictionary_id", unique=true, nullable=false, length=COLUMN_LENGTH)
	private String categoryDictionaryId;

	/** The Category Dictionary Description. */
	@Column(name="category_dictionary_description", nullable=false, length=COLUMN_LENGTH)
	private String categoryDictionaryDescription;

	/** The Category Dictionary Name. */
	@Column(name="category_dictionary_name", nullable=false, length=COLUMN_LENGTH)
	private String categoryDictionaryName;

	/**
	 * The constructor.
	 */
	public SpecificCategoryDictionaryEntity() {
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
	 * Get the categoryDictionaryDescription.
	 * 
	 * @return the categoryDictionaryDescription
	 */
	public String getCategoryDictionaryDescription() {
		return categoryDictionaryDescription;
	}

	/**
	 * Set the categoryDictionaryDescription.
	 *
	 * @param categoryDictionaryDescription the categoryDictionaryDescription to set
	 */
	public void setCategoryDictionaryDescription(String categoryDictionaryDescription) {
		this.categoryDictionaryDescription = categoryDictionaryDescription;
	}

	/**
	 * Get the categoryDictionaryName.
	 * 
	 * @return the categoryDictionaryName
	 */
	public String getCategoryDictionaryName() {
		return categoryDictionaryName;
	}

	/**
	 * Set the categoryDictionaryName.
	 *
	 * @param categoryDictionaryName the categoryDictionaryName to set
	 */
	public void setCategoryDictionaryName(String categoryDictionaryName) {
		this.categoryDictionaryName = categoryDictionaryName;
	}

}
