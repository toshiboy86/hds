/**
 * DictionaryTranslationMapDto.java
 * (C) 2019. Industrial Value Chain Initiative
 */
package jp.ciof_cps.hds.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class is the DTO class for Dictionary Translation Map (DTM).
 */
public class DictionaryTranslationMapDto {
	
	/** The Dictionary Translation Map (DTM) ID. */
	@JsonProperty("dtm_id")
	private String dtmId;
	
	/** The Source Data Dictionary Id. */
	@JsonProperty("source_data_dictionary_id")
	private String sourceDataDictionaryId;
	
	/** The Source Data Component Model (DCM) ID. */
	@JsonProperty("source_dcm_id")
	private String sourceDcmId;
	
	/** The Source Data Relation Model (DRM) ID. */
	@JsonProperty("source_drm_id")
	private String sourceDrmId;
	
	/** The Destination Data Dictionary ID. */
	@JsonProperty("destination_data_dictionary_id")
	private String destinationDataDictionaryId;
	
	/** The Destination Data Component Model (DCM) ID. */
	@JsonProperty("destination_dcm_id")
	private String destinationDcmId;
	
	/** The Destination Data Relation Model (DRM) ID. */
	@JsonProperty("destination_drm_id")
	private String destinationDrmId;
	
	/** Whether this DTM is tentative. */
	@JsonProperty("is_tentative")
	private Boolean isTentative;
	
	/** The Property Translation Map (PTM) List. */
	@JsonProperty("ptm_list")
	private List<PropertyTranslationMapDto> ptmList;

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
	 * Get the ptmList.
	 * 
	 * @return the ptmList
	 */
	public List<PropertyTranslationMapDto> getPtmList() {
		return ptmList;
	}

	/**
	 * Set the ptmList.
	 *
	 * @param ptmList the ptmList to set
	 */
	public void setPtmList(List<PropertyTranslationMapDto> ptmList) {
		this.ptmList = ptmList;
	}
	
}
