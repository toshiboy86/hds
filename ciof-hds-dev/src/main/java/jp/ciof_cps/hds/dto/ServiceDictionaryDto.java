/**
 * ServiceDictionaryDto.java
 * (C) 2019. Industrial Value Chain Initiative
 */
package jp.ciof_cps.hds.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class is the DTO class for Service Dictionary.
 */
public class ServiceDictionaryDto {
	
	/** The Service Dictionary ID. */
	@JsonProperty("service_dictionary_id")
	private String serviceDictionaryId;
	
	/** The Service Dictionary Name. */
	@JsonProperty("service_dictionary_name")
	private String serviceDictionaryName;
	
	/** The Service Dictionary Description. */
	@JsonProperty("service_dictionary_description")
	private String serviceDictionaryDescription;
	
	/** The Service Dictionary Type. */
	@JsonProperty("service_dictionary_type")
	private String serviceDictionaryType;
	
	/** The Version. */
	@JsonProperty("version")
	private Integer version;
	
	/** The Previous Service Dictionary ID. */
	@JsonProperty("previous_service_dictionary_id")
	private String previousServiceDictionaryId;
	
	/** The Owner ID. */
	@JsonProperty("owner_id")
	private String ownerId;
	
	/** The Process Component Model (PCM) ID List. */
	@JsonProperty("pcm_id_list")
	private List<String> pcmIdList = new ArrayList<>();
	
	/** The Event and Condition Model (ECM) ID List. */
	@JsonProperty("ecm_id_list")
	private List<String> ecmIdList = new ArrayList<>();

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
	 * Get the pcmIdList.
	 * 
	 * @return the pcmIdList
	 */
	public List<String> getPcmIdList() {
		return pcmIdList;
	}

	/**
	 * Set the pcmIdList.
	 *
	 * @param pcmIdList the pcmIdList to set
	 */
	public void setPcmIdList(List<String> pcmIdList) {
		this.pcmIdList = pcmIdList;
	}

	/**
	 * Get the ecmIdList.
	 * 
	 * @return the ecmIdList
	 */
	public List<String> getEcmIdList() {
		return ecmIdList;
	}

	/**
	 * Set the ecmIdList.
	 *
	 * @param ecmIdList the ecmIdList to set
	 */
	public void setEcmIdList(List<String> ecmIdList) {
		this.ecmIdList = ecmIdList;
	}

}
