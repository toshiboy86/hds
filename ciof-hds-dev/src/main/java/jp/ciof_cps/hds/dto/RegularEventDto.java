/**
 * RegularEventDto.java
 * (C) 2019. Industrial Value Chain Initiative
 */
package jp.ciof_cps.hds.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class is the DTO class for Regular Event.
 */
public class RegularEventDto {
	
	/** The Repeat Interval. */
	@JsonProperty("repeat_interval")
	private Integer repeatInterval;
	
	/** The Repeat Interval Unit. */
	@JsonProperty("repeat_interval_unit")
	private String repeatIntervalUnit;
	
	/** The Repeat Count. */
	@JsonProperty("repeat_count")
	private Integer repeatCount;
	
	/** The Repeat End Date Time. */
	@JsonProperty("repeat_end_date_time")
	private String repeatEndDateTime;
	
	/** The Daily. */
	@JsonProperty("daily")
	private String daily;
	
	/** The Weekly. */
	@JsonProperty("weekly")
	private Integer weekly;
	
	/** The Monthly. */
	@JsonProperty("monthly")
	private Integer monthly;

	/**
	 * Get the repeatInterval.
	 * 
	 * @return the repeatInterval
	 */
	public Integer getRepeatInterval() {
		return repeatInterval;
	}

	/**
	 * Set the repeatInterval.
	 *
	 * @param repeatInterval the repeatInterval to set
	 */
	public void setRepeatInterval(Integer repeatInterval) {
		this.repeatInterval = repeatInterval;
	}

	/**
	 * Get the repeatIntervalUnit.
	 * 
	 * @return the repeatIntervalUnit
	 */
	public String getRepeatIntervalUnit() {
		return repeatIntervalUnit;
	}

	/**
	 * Set the repeatIntervalUnit.
	 *
	 * @param repeatIntervalUnit the repeatIntervalUnit to set
	 */
	public void setRepeatIntervalUnit(String repeatIntervalUnit) {
		this.repeatIntervalUnit = repeatIntervalUnit;
	}

	/**
	 * Get the repeatCount.
	 * 
	 * @return the repeatCount
	 */
	public Integer getRepeatCount() {
		return repeatCount;
	}

	/**
	 * Set the repeatCount.
	 *
	 * @param repeatCount the repeatCount to set
	 */
	public void setRepeatCount(Integer repeatCount) {
		this.repeatCount = repeatCount;
	}

	/**
	 * Get the repeatEndDateTime.
	 * 
	 * @return the repeatEndDateTime
	 */
	public String getRepeatEndDateTime() {
		return repeatEndDateTime;
	}

	/**
	 * Set the repeatEndDateTime.
	 *
	 * @param repeatEndDateTime the repeatEndDateTime to set
	 */
	public void setRepeatEndDateTime(String repeatEndDateTime) {
		this.repeatEndDateTime = repeatEndDateTime;
	}

	/**
	 * Get the daily.
	 * 
	 * @return the daily
	 */
	public String getDaily() {
		return daily;
	}

	/**
	 * Set the daily.
	 *
	 * @param daily the daily to set
	 */
	public void setDaily(String daily) {
		this.daily = daily;
	}

	/**
	 * Get the weekly.
	 * 
	 * @return the weekly
	 */
	public Integer getWeekly() {
		return weekly;
	}

	/**
	 * Set the weekly.
	 *
	 * @param weekly the weekly to set
	 */
	public void setWeekly(Integer weekly) {
		this.weekly = weekly;
	}

	/**
	 * Get the monthly.
	 * 
	 * @return the monthly
	 */
	public Integer getMonthly() {
		return monthly;
	}

	/**
	 * Set the monthly.
	 *
	 * @param monthly the monthly to set
	 */
	public void setMonthly(Integer monthly) {
		this.monthly = monthly;
	}

}
