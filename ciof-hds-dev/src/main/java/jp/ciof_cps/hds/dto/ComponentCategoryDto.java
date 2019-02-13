/**
 * ComponentCategoryDto.java
 * (C) 2019. Industrial Value Chain Initiative
 */
package jp.ciof_cps.hds.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *  The class is the DTO class for Component Category.
 */
public class ComponentCategoryDto {

	@JsonProperty("component_category_id")
	private String componentCategoryId;

	@JsonProperty("category_dictionary_id")
	private String categoryDictionaryId;
	
	@JsonProperty("component_category_type")
	private String componentCategoryType;
	
	@JsonProperty("component_category_name")
	private String componentCategoryName;
	
	@JsonProperty("component_category_description")
	private String componentCategoryDescription;

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

	
}
