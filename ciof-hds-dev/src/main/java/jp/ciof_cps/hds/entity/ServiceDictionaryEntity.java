/**
 * ServiceDictionaryEntity.java
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
 * The persistent class for the Service Dictionary database table.
 */
@Entity
@Table(name="service_dictionary")
@NamedQuery(name="ServiceDictionaryEntity.findAll", query="SELECT s FROM ServiceDictionaryEntity s")
public class ServiceDictionaryEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final int COLUMN_LENGTH = 256;

	/** The Service Dictionary ID. */
	@Id
	@Column(name="service_dictionary_id", unique=true, nullable=false, length=COLUMN_LENGTH)
	private String serviceDictionaryId;

	/** The Owner ID. */
	@Column(name="owner_id", nullable=false, length=COLUMN_LENGTH)
	private String ownerId;

	/** The Previous Service Dictionary ID. */
	@Column(name="previous_service_dictionary_id", nullable=true, length=COLUMN_LENGTH)
	private String previousServiceDictionaryId;

	/** The Service Dictionary Description. */
	@Column(name="service_dictionary_description", nullable=false, length=COLUMN_LENGTH)
	private String serviceDictionaryDescription;

	/** The Service Dictionary Name. */
	@Column(name="service_dictionary_name", nullable=false, length=COLUMN_LENGTH)
	private String serviceDictionaryName;

	/** The Service Dictionary Type. */
	@Column(name="service_dictionary_type", nullable=false, length=COLUMN_LENGTH)
	private String serviceDictionaryType;

	/** The Version. */
	@Column(nullable=false)
	private Integer version;

	/**
	 * The constructor.
	 */
	public ServiceDictionaryEntity() {
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
	 * Get the previousServiceDictionaryId.
	 * 
	 * @return the previousServiceDictionaryId
	 */
	public String getPreviousServiceDictionaryId() {
		return previousServiceDictionaryId;
	}

	/**
	 * Set the previousServiceDictionaryId.
	 *
	 * @param previousServiceDictionaryId the previousServiceDictionaryId to set
	 */
	public void setPreviousServiceDictionaryId(String previousServiceDictionaryId) {
		this.previousServiceDictionaryId = previousServiceDictionaryId;
	}

	/**
	 * Get the serviceDictionaryDescription.
	 * 
	 * @return the serviceDictionaryDescription
	 */
	public String getServiceDictionaryDescription() {
		return serviceDictionaryDescription;
	}

	/**
	 * Set the serviceDictionaryDescription.
	 *
	 * @param serviceDictionaryDescription the serviceDictionaryDescription to set
	 */
	public void setServiceDictionaryDescription(String serviceDictionaryDescription) {
		this.serviceDictionaryDescription = serviceDictionaryDescription;
	}

	/**
	 * Get the serviceDictionaryName.
	 * 
	 * @return the serviceDictionaryName
	 */
	public String getServiceDictionaryName() {
		return serviceDictionaryName;
	}

	/**
	 * Set the serviceDictionaryName.
	 *
	 * @param serviceDictionaryName the serviceDictionaryName to set
	 */
	public void setServiceDictionaryName(String serviceDictionaryName) {
		this.serviceDictionaryName = serviceDictionaryName;
	}

	/**
	 * Get the serviceDictionaryType.
	 * 
	 * @return the serviceDictionaryType
	 */
	public String getServiceDictionaryType() {
		return serviceDictionaryType;
	}

	/**
	 * Set the serviceDictionaryType.
	 *
	 * @param serviceDictionaryType the serviceDictionaryType to set
	 */
	public void setServiceDictionaryType(String serviceDictionaryType) {
		this.serviceDictionaryType = serviceDictionaryType;
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
