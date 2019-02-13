/**
 * PropertyTranslationMapEntity.java
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
 * The persistent class for the Property Translation Map (PTM) database table.
 */
@Entity
@Table(name="property_translation_map")
@NamedQuery(name="PropertyTranslationMapEntity.findAll", query="SELECT p FROM PropertyTranslationMapEntity p")
public class PropertyTranslationMapEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final int COLUMN_LENGTH = 256;

	/** The Property Translation Map (PTM) ID. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ptm_id", unique=true, nullable=false)
	private Integer ptmId;

	/** The Destination Data Property Definition (DPD) ID. */
	@Column(name="destination_dpd_id", nullable=false)
	private Integer destinationDpdId;

	/** The Data Translation Map (DTM) ID. */
	@Column(name="dtm_id", nullable=false, length=COLUMN_LENGTH)
	private String dtmId;

	/** The Source Data Property Definition (DPD) ID. */
	@Column(name="source_dpd_id", nullable=false)
	private Integer sourceDpdId;

	/**
	 * The constructor.
	 */
	public PropertyTranslationMapEntity() {
	}

	/**
	 * Get the ptmId.
	 * 
	 * @return the ptmId
	 */
	public Integer getPtmId() {
		return ptmId;
	}

	/**
	 * Set the ptmId.
	 *
	 * @param ptmId the ptmId to set
	 */
	public void setPtmId(Integer ptmId) {
		this.ptmId = ptmId;
	}

	/**
	 * Get the destinationDpdId.
	 * 
	 * @return the destinationDpdId
	 */
	public Integer getDestinationDpdId() {
		return destinationDpdId;
	}

	/**
	 * Set the destinationDpdId.
	 *
	 * @param destinationDpdId the destinationDpdId to set
	 */
	public void setDestinationDpdId(Integer destinationDpdId) {
		this.destinationDpdId = destinationDpdId;
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
	 * Get the sourceDpdId.
	 * 
	 * @return the sourceDpdId
	 */
	public Integer getSourceDpdId() {
		return sourceDpdId;
	}

	/**
	 * Set the sourceDpdId.
	 *
	 * @param sourceDpdId the sourceDpdId to set
	 */
	public void setSourceDpdId(Integer sourceDpdId) {
		this.sourceDpdId = sourceDpdId;
	}


}
