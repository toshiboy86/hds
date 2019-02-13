/**
 * ProcessComponentModelEntity.java
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
 * The persistent class for the Process Component Model (PCM) database table.
 */
@Entity
@Table(name="process_component_model")
@NamedQuery(name="ProcessComponentModelEntity.findAll", query="SELECT p FROM ProcessComponentModelEntity p")
public class ProcessComponentModelEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final int COLUMN_LENGTH = 256;

	/** The Process Component Model (PCM) ID. */
	@Id
	@Column(name="pcm_id", unique=true, nullable=false, length=COLUMN_LENGTH)
	private String pcmId;

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

	/** The Hardware Requirement. */
	@Column(name="hardware_requirement", nullable=false, length=COLUMN_LENGTH)
	private String hardwareRequirement;

	/** The Other Requirement. */
	@Column(name="other_requirement", nullable=false, length=COLUMN_LENGTH)
	private String otherRequirement;

	/** The Process Component Model (PCM) Description. */
	@Column(name="pcm_description", nullable=false, length=COLUMN_LENGTH)
	private String pcmDescription;

	/** The Process Component Model (PCM) Name. */
	@Column(name="pcm_name", nullable=false, length=COLUMN_LENGTH)
	private String pcmName;

	/** The Service Dictionary ID. */
	@Column(name="service_dictionary_id", nullable=false, length=COLUMN_LENGTH)
	private String serviceDictionaryId;

	/**
	 * 	The constructor.
	 */
	public ProcessComponentModelEntity() {
	}

	/**
	 * Get the pcmId.
	 * 
	 * @return the pcmId
	 */
	public String getPcmId() {
		return pcmId;
	}

	/**
	 * Set the pcmId.
	 *
	 * @param pcmId the pcmId to set
	 */
	public void setPcmId(String pcmId) {
		this.pcmId = pcmId;
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
	 * Get the hardwareRequirement.
	 * 
	 * @return the hardwareRequirement
	 */
	public String getHardwareRequirement() {
		return hardwareRequirement;
	}

	/**
	 * Set the hardwareRequirement.
	 *
	 * @param hardwareRequirement the hardwareRequirement to set
	 */
	public void setHardwareRequirement(String hardwareRequirement) {
		this.hardwareRequirement = hardwareRequirement;
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
	 * Get the pcmDescription.
	 * 
	 * @return the pcmDescription
	 */
	public String getPcmDescription() {
		return pcmDescription;
	}

	/**
	 * Set the pcmDescription.
	 *
	 * @param pcmDescription the pcmDescription to set
	 */
	public void setPcmDescription(String pcmDescription) {
		this.pcmDescription = pcmDescription;
	}

	/**
	 * Get the pcmName.
	 * 
	 * @return the pcmName
	 */
	public String getPcmName() {
		return pcmName;
	}

	/**
	 * Set the pcmName.
	 *
	 * @param pcmName the pcmName to set
	 */
	public void setPcmName(String pcmName) {
		this.pcmName = pcmName;
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
