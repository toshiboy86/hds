/**
 * ComponentCategoryEntity.java
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
 * The persistent class for the Component Category database table.
 */
@Entity
@Table(name="component_category")
@NamedQuery(name="ComponentCategoryEntity.findAll", query="SELECT c FROM ComponentCategoryEntity c")
public class ComponentCategoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final int COLUMN_LENGTH = 256;

	/** The Component Category ID. */
	@Id
	@Column(name="component_category_id", unique=true, nullable=false, length=COLUMN_LENGTH)
	private String componentCategoryId;

	/** The Component Category Description. */
	@Column(name="component_category_description", nullable=false, length=COLUMN_LENGTH)
	private String componentCategoryDescription;
	
	/** The Category Dictionary ID. */
	@Column(name="category_dictionary_id", nullable=false, length=COLUMN_LENGTH)
	private String categoryDictionaryId;

	/** The Component Category Name. */
	@Column(name="component_category_name", nullable=false, length=COLUMN_LENGTH)
	private String componentCategoryName;

	/** The Component Category Type. */
	@Column(name="component_category_type", nullable=false, length=COLUMN_LENGTH)
	private String componentCategoryType;

	/**
	 * The constructor.
	 */
	public ComponentCategoryEntity() {
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
	 * Get the componentCategoryDescription.
	 * 
	 * @return the componentCategoryDescription
	 */
	public String getComponentCategoryDescription() {
		return componentCategoryDescription;
	}

	/**
	 * Set the componentCategoryDescription.
	 *
	 * @param componentCategoryDescription the componentCategoryDescription to set
	 */
	public void setComponentCategoryDescription(String componentCategoryDescription) {
		this.componentCategoryDescription = componentCategoryDescription;
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
	 * Get the componentCategoryName.
	 * 
	 * @return the componentCategoryName
	 */
	public String getComponentCategoryName() {
		return componentCategoryName;
	}

	/**
	 * Set the componentCategoryName.
	 *
	 * @param componentCategoryName the componentCategoryName to set
	 */
	public void setComponentCategoryName(String componentCategoryName) {
		this.componentCategoryName = componentCategoryName;
	}

	/**
	 * Get the componentCategoryType.
	 * 
	 * @return the componentCategoryType
	 */
	public String getComponentCategoryType() {
		return componentCategoryType;
	}

	/**
	 * Set the componentCategoryType.
	 *
	 * @param componentCategoryType the componentCategoryType to set
	 */
	public void setComponentCategoryType(String componentCategoryType) {
		this.componentCategoryType = componentCategoryType;
	}

}
