/**
 * DictionaryTranslationMapEntity.java
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
 * The persistent class for the Dictionary Translation Map (DTM) database table.
 */
@Entity
@Table(name="dictionary_translation_map")
@NamedQuery(name="DictionaryTranslationMapEntity.findAll", query="SELECT d FROM DictionaryTranslationMapEntity d")
public class DictionaryTranslationMapEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final int COLUMN_LENGTH = 256;
	

	/** The Data Translation Map (DTM) ID. */
	@Id
	@Column(name="dtm_id", unique=true, nullable=false, length=COLUMN_LENGTH)
	private String dtmId;

	/** The Destination Data Dictionary ID. */
	@Column(name="destination_data_dictionary_id", nullable=false, length=COLUMN_LENGTH)
	private String destinationDataDictionaryId;

	/** The Destination Data Component Model (DCM) ID. */
	@Column(name="destination_dcm_id", nullable=false, length=COLUMN_LENGTH)
	private String destinationDcmId;

	/** The Destination Data Relation Model (DRM) ID. */
	@Column(name="destination_drm_id", nullable=true, length=COLUMN_LENGTH)
	private String destinationDrmId;

	/** Whether this DTM is tentative. */
	@Column(name="is_tentative", nullable=false)
	private Boolean isTentative;

	/** The Owner ID. */
	@Column(name="owner_id", nullable=false, length=COLUMN_LENGTH)
	private String ownerId;

	/** The Source Data Dictionary ID. */
	@Column(name="source_data_dictionary_id", nullable=false, length=COLUMN_LENGTH)
	private String sourceDataDictionaryId;

	/** The Source Data Component Model (DCM) ID. */
	@Column(name="source_dcm_id", nullable=false, length=COLUMN_LENGTH)
	private String sourceDcmId;

	/** The Source Data Relation Model (DRM) ID. */
	@Column(name="source_drm_id", nullable=true, length=COLUMN_LENGTH)
	private String sourceDrmId;

	/**
	 * The constructor.
	 */
	public DictionaryTranslationMapEntity() {
	}

	/**
	 * Get the dtmId.
	 * 
	 * @return the dtmId
	 */
	public String getDtmId() {
		return dtmId;
	}

	/**
	 * Set the dtmId.
	 *
	 * @param dtmId the dtmId to set
	 */
	public void setDtmId(String dtmId) {
		this.dtmId = dtmId;
	}

	/**
	 * Get the destinationDataDictionaryId.
	 * 
	 * @return the destinationDataDictionaryId
	 */
	public String getDestinationDataDictionaryId() {
		return destinationDataDictionaryId;
	}

	/**
	 * Set the destinationDataDictionaryId.
	 *
	 * @param destinationDataDictionaryId the destinationDataDictionaryId to set
	 */
	public void setDestinationDataDictionaryId(String destinationDataDictionaryId) {
		this.destinationDataDictionaryId = destinationDataDictionaryId;
	}

	/**
	 * Get the destinationDcmId.
	 * 
	 * @return the destinationDcmId
	 */
	public String getDestinationDcmId() {
		return destinationDcmId;
	}

	/**
	 * Set the destinationDcmId.
	 *
	 * @param destinationDcmId the destinationDcmId to set
	 */
	public void setDestinationDcmId(String destinationDcmId) {
		this.destinationDcmId = destinationDcmId;
	}

	/**
	 * Get the destinationDrmId.
	 * 
	 * @return the destinationDrmId
	 */
	public String getDestinationDrmId() {
		return destinationDrmId;
	}

	/**
	 * Set the destinationDrmId.
	 *
	 * @param destinationDrmId the destinationDrmId to set
	 */
	public void setDestinationDrmId(String destinationDrmId) {
		this.destinationDrmId = destinationDrmId;
	}

	/**
	 * Get the isTentative.
	 * 
	 * @return the isTentative
	 */
	public Boolean getIsTentative() {
		return isTentative;
	}

	/**
	 * Set the isTentative.
	 *
	 * @param isTentative the isTentative to set
	 */
	public void setIsTentative(Boolean isTentative) {
		this.isTentative = isTentative;
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
	 * Get the sourceDataDictionaryId.
	 * 
	 * @return the sourceDataDictionaryId
	 */
	public String getSourceDataDictionaryId() {
		return sourceDataDictionaryId;
	}

	/**
	 * Set the sourceDataDictionaryId.
	 *
	 * @param sourceDataDictionaryId the sourceDataDictionaryId to set
	 */
	public void setSourceDataDictionaryId(String sourceDataDictionaryId) {
		this.sourceDataDictionaryId = sourceDataDictionaryId;
	}

	/**
	 * Get the sourceDcmId.
	 * 
	 * @return the sourceDcmId
	 */
	public String getSourceDcmId() {
		return sourceDcmId;
	}

	/**
	 * Set the sourceDcmId.
	 *
	 * @param sourceDcmId the sourceDcmId to set
	 */
	public void setSourceDcmId(String sourceDcmId) {
		this.sourceDcmId = sourceDcmId;
	}

	/**
	 * Get the sourceDrmId.
	 * 
	 * @return the sourceDrmId
	 */
	public String getSourceDrmId() {
		return sourceDrmId;
	}

	/**
	 * Set the sourceDrmId.
	 *
	 * @param sourceDrmId the sourceDrmId to set
	 */
	public void setSourceDrmId(String sourceDrmId) {
		this.sourceDrmId = sourceDrmId;
	}

}
