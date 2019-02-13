/**
 * PropertyTranslationMapDto.java
 * (C) 2019. Industrial Value Chain Initiative
 */
package jp.ciof_cps.hds.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class is the DTO class for Property Translation Map (PTM).
 */
public class PropertyTranslationMapDto {
	
	/** The Source Data Property Definition (DPD) Name. */
	@JsonProperty("source_dpd_name")
	private String sourceDpdName;
	
	/** The Destination Data Property Definition (DPD) Name. */
	@JsonProperty("destination_dpd_name")
	private String destinationDpdName;

	/**
	 * Get the sourceDpdName.
	 * 
	 * @return the sourceDpdName
	 */
	public String getSourceDpdName() {
		return sourceDpdName;
	}

	/**
	 * Set the sourceDpdName.
	 *
	 * @param sourceDpdName the sourceDpdName to set
	 */
	public void setSourceDpdName(String sourceDpdName) {
		this.sourceDpdName = sourceDpdName;
	}

	/**
	 * Get the destinationDpdName.
	 * 
	 * @return the destinationDpdName
	 */
	public String getDestinationDpdName() {
		return destinationDpdName;
	}

	/**
	 * Set the destinationDpdName.
	 *
	 * @param destinationDpdName the destinationDpdName to set
	 */
	public void setDestinationDpdName(String destinationDpdName) {
		this.destinationDpdName = destinationDpdName;
	}

}
