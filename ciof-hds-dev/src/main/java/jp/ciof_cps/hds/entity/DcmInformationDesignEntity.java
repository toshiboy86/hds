/**
 * DcmInformationDesignEntity.java
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
 * The persistent class for the Data Component Model (DCM) Information Design database table.
 */
@Entity
@Table(name="dcm_information_design")
@NamedQuery(name="DcmInformationDesignEntity.findAll", query="SELECT d FROM DcmInformationDesignEntity d")
public class DcmInformationDesignEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final int COLUMN_LENGTH = 256;
	
	/** The Data Component Model (DCM) Information Design ID. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="dcm_information_design_id", unique=true, nullable=false)
	private Integer dcmInformationDesignId;

	/** The Data Component Model (DCM) ID. */
	@Column(name="dcm_id", nullable=false, length=COLUMN_LENGTH)
	private String dcmId;

	/** The Information Design ID. */
	@Column(name="information_design_id", nullable=false, length=COLUMN_LENGTH)
	private String informationDesignId;

	/** The information Design Name. */
	@Column(name="information_design_name", nullable=false, length=COLUMN_LENGTH)
	private String informationDesignName;

	/** The Information Design Type. */
	@Column(name="information_design_type", nullable=false, length=COLUMN_LENGTH)
	private String informationDesignType;

	/** The Remarks. */
	@Column(nullable=true, length=COLUMN_LENGTH)
	private String remarks;

	/**
	 * The constructor.
	 */
	public DcmInformationDesignEntity() {
	}

	/**
	 * Get the dcmInformationDesignId.
	 * 
	 * @return the dcmInformationDesignId
	 */
	public Integer getDcmInformationDesignId() {
		return dcmInformationDesignId;
	}

	/**
	 * Set the dcmInformationDesignId.
	 *
	 * @param dcmInformationDesignId the dcmInformationDesignId to set
	 */
	public void setDcmInformationDesignId(Integer dcmInformationDesignId) {
		this.dcmInformationDesignId = dcmInformationDesignId;
	}

	/**
	 * Get the dcmId.
	 * 
	 * @return the dcmId
	 */
	public String getDcmId() {
		return dcmId;
	}

	/**
	 * Set the dcmId.
	 *
	 * @param dcmId the dcmId to set
	 */
	public void setDcmId(String dcmId) {
		this.dcmId = dcmId;
	}

	/**
	 * Get the informationDesignId.
	 * 
	 * @return the informationDesignId
	 */
	public String getInformationDesignId() {
		return informationDesignId;
	}

	/**
	 * Set the informationDesignId.
	 *
	 * @param informationDesignId the informationDesignId to set
	 */
	public void setInformationDesignId(String informationDesignId) {
		this.informationDesignId = informationDesignId;
	}

	/**
	 * Get the informationDesignName.
	 * 
	 * @return the informationDesignName
	 */
	public String getInformationDesignName() {
		return informationDesignName;
	}

	/**
	 * Set the informationDesignName.
	 *
	 * @param informationDesignName the informationDesignName to set
	 */
	public void setInformationDesignName(String informationDesignName) {
		this.informationDesignName = informationDesignName;
	}

	/**
	 * Get the informationDesignType.
	 * 
	 * @return the informationDesignType
	 */
	public String getInformationDesignType() {
		return informationDesignType;
	}

	/**
	 * Set the informationDesignType.
	 *
	 * @param informationDesignType the informationDesignType to set
	 */
	public void setInformationDesignType(String informationDesignType) {
		this.informationDesignType = informationDesignType;
	}

	/**
	 * Get the remarks.
	 * 
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * Set the remarks.
	 *
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
