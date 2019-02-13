/**
 * SpecificCategoryDictionaryDto.java
 * (C) 2019. Industrial Value Chain Initiative
 */
package jp.ciof_cps.hds.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class is the DTO class for Specific Category Dictionary.
 */
public class SpecificCategoryDictionaryDto {

	@JsonProperty("category_dictionary_id")
	private String categoryDictionaryId;
	
	@JsonProperty("category_dictionary_name")
	private String categoryDictionaryName;
	
	@JsonProperty("category_dictionary_description")
	private String categoryDictionaryDescription;
	
	@JsonProperty("component_category_id_list")
	private List<String> componentCategoryIdList = new ArrayList<>();

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
	 * Get the componentCategoryIdList.
	 * 
	 * @return the componentCategoryIdList
	 */
	public List<String> getComponentCategoryIdList() {
		return componentCategoryIdList;
	}

	/**
	 * Set the componentCategoryIdList.
	 *
	 * @param componentCategoryIdList the componentCategoryIdList to set
	 */
	public void setComponentCategoryIdList(List<String> componentCategoryIdList) {
		this.componentCategoryIdList = componentCategoryIdList;
	}

}
