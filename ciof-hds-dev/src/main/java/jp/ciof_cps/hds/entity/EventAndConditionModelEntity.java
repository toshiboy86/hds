/**
 * EventAndConditionModelEntity.java
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
 * The persistent class for the Event and Condition Model (ECM) database table.
 */
@Entity
@Table(name="event_and_condition_model")
@NamedQuery(name="EventAndConditionModelEntity.findAll", query="SELECT e FROM EventAndConditionModelEntity e")
public class EventAndConditionModelEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final int COLUMN_LENGTH = 256;

	/** The Event and Condition Model (ECM) ID. */
	@Id
	@Column(name="ecm_id", unique=true, nullable=false, length=COLUMN_LENGTH)
	private String ecmId;

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

	/** The Event and Condition Model (ECM) Description. */
	@Column(name="ecm_description", nullable=false, length=COLUMN_LENGTH)
	private String ecmDescription;

	/** The Event and Condition Model (ECM) Name. */
	@Column(name="ecm_name", nullable=false, length=COLUMN_LENGTH)
	private String ecmName;

	/** The Other Requirement. */
	@Column(name="other_requirement", nullable=false, length=COLUMN_LENGTH)
	private String otherRequirement;

	/** The Service Dictionary ID. */
	@Column(name="service_dictionary_id", nullable=false, length=COLUMN_LENGTH)
	private String serviceDictionaryId;

	/**
	 * The constructor.
	 */
	public EventAndConditionModelEntity() {
	}

	/**
	 * Get the ecmId.
	 * 
	 * @return the ecmId
	 */
	public String getEcmId() {
		return ecmId;
	}

	/**
	 * Set the ecmId.
	 *
	 * @param ecmId the ecmId to set
	 */
	public void setEcmId(String ecmId) {
		this.ecmId = ecmId;
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
	 * Get the ecmDescription.
	 * 
	 * @return the ecmDescription
	 */
	public String getEcmDescription() {
		return ecmDescription;
	}

	/**
	 * Set the ecmDescription.
	 *
	 * @param ecmDescription the ecmDescription to set
	 */
	public void setEcmDescription(String ecmDescription) {
		this.ecmDescription = ecmDescription;
	}

	/**
	 * Get the ecmName.
	 * 
	 * @return the ecmName
	 */
	public String getEcmName() {
		return ecmName;
	}

	/**
	 * Set the ecmName.
	 *
	 * @param ecmName the ecmName to set
	 */
	public void setEcmName(String ecmName) {
		this.ecmName = ecmName;
	}

	/**
	 * Get the otherRequirement.
	 * 
	 * @return the otherRequirement
	 */
	public String getOtherRequirement() {
		return otherRequirement;
	}

	/**
	 * Set the otherRequirement.
	 *
	 * @param otherRequirement the otherRequirement to set
	 */
	public void setOtherRequirement(String otherRequirement) {
		this.otherRequirement = otherRequirement;
	}

	/**
	 * Get the serviceDictionaryId.
	 * 
	 * @return the serviceDictionaryId
	 */
	public String getServiceDictionaryId() {
		return serviceDictionaryId;
	}

	/**
	 * Set the serviceDictionaryId.
	 *
	 * @param serviceDictionaryId the serviceDictionaryId to set
	 */
	public void setServiceDictionaryId(String serviceDictionaryId) {
		this.serviceDictionaryId = serviceDictionaryId;
	}

}
