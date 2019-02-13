/**
 * RegularEventEntity.java
 * (C) 2019. Industrial Value Chain Initiative
 */
package jp.ciof_cps.hds.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the Regular Event database table.
 */
@Entity
@Table(name="regular_event")
@NamedQuery(name="RegularEventEntity.findAll", query="SELECT r FROM RegularEventEntity r")
public class RegularEventEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final int COLUMN_LENGTH = 256;

	/** The Regular Event ID. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="regular_event_id", unique=true, nullable=false)
	private Integer regularEventId;

	/** The Daily. */
	@Column(nullable=true, length=COLUMN_LENGTH)
	private String daily;

	/** The Event And Condition Model (ECM) ID. */
	@Column(name="ecm_id", nullable=false, length=COLUMN_LENGTH)
	private String ecmId;

	/** The Monthly. */
	@Column(nullable=true)
	private Integer monthly;

	/** The Repeat Count. */
	@Column(name="repeat_count", nullable=true)
	private Integer repeatCount;

	/** The Repeat End Date Time. */
	@Column(name="repeat_end_date_time", nullable=true, length=COLUMN_LENGTH)
	private String repeatEndDateTime;

	/** The Repeat Interval. */
	@Column(name="repeat_interval", nullable=true)
	private Integer repeatInterval;

	/** The Repeat Interval Unit. */
	@Column(name="repeat_interval_unit", nullable=true, length=COLUMN_LENGTH)
	private String repeatIntervalUnit;

	/** The Weekly. */
	@Column(nullable=true)
	private Integer weekly;

	/**
	 * The constructor.
	 */
	public RegularEventEntity() {
	}

	/**
	 * Get the regularEventId.
	 * 
	 * @return the regularEventId
	 */
	public Integer getRegularEventId() {
		return regularEventId;
	}

	/**
	 * Set the regularEventId.
	 *
	 * @param regularEventId the regularEventId to set
	 */
	public void setRegularEventId(Integer regularEventId) {
		this.regularEventId = regularEventId;
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

}
