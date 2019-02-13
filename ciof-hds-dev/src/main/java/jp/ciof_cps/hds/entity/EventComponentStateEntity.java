/**
 * EventComponentStateEntity.java
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
 * The persistent class for the Event and Condition State (ECS) database table.
 */
@Entity
@Table(name="event_component_state")
@NamedQuery(name="EventComponentStateEntity.findAll", query="SELECT e FROM EventComponentStateEntity e")
public class EventComponentStateEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final int COLUMN_LENGTH = 256;

	/** The Event and Condition State ID. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="event_component_state_id", unique=true, nullable=false)
	private Integer eventComponentStateId;

	/** The Data Component Model ID. */
	@Column(name="dcm_id", nullable=false, length=COLUMN_LENGTH)
	private String dcmId;

	/** The Determination Method. */
	@Column(name="determination_method", nullable=false, length=COLUMN_LENGTH)
	private String determinationMethod;
	
	/** The Data Property Definition (DPD) Name. */
	@Column(name="dpd_name", nullable=false, length=COLUMN_LENGTH)
	private String dpdName;

	/** The Event and Condition Expression (ECE). */
	@Column(nullable=false, length=COLUMN_LENGTH)
	private String ece;

	/** The Event and Condition Model (ECM) ID. */
	@Column(name="ecm_id", nullable=false, length=COLUMN_LENGTH)
	private String ecmId;

	/** The State ID. */
	@Column(name="state_id", nullable=false, length=COLUMN_LENGTH)
	private String stateId;

	/**
	 * The constructor.
	 */
	public EventComponentStateEntity() {
	}

	/**
	 * Get the eventComponentStateId.
	 * 
	 * @return the eventComponentStateId
	 */
	public Integer getEventComponentStateId() {
		return eventComponentStateId;
	}

	/**
	 * Set the eventComponentStateId.
	 *
	 * @param eventComponentStateId the eventComponentStateId to set
	 */
	public void setEventComponentStateId(Integer eventComponentStateId) {
		this.eventComponentStateId = eventComponentStateId;
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
	 * Get the determinationMethod.
	 * 
	 * @return the determinationMethod
	 */
	public String getDeterminationMethod() {
		return determinationMethod;
	}

	/**
	 * Set the determinationMethod.
	 *
	 * @param determinationMethod the determinationMethod to set
	 */
	public void setDeterminationMethod(String determinationMethod) {
		this.determinationMethod = determinationMethod;
	}

	/**
	 * Get the dpdName.
	 * 
	 * @return the dpdName
	 */
	public String getDpdName() {
		return dpdName;
	}

	/**
	 * Set the dpdName.
	 *
	 * @param dpdName the dpdName to set
	 */
	public void setDpdName(String dpdName) {
		this.dpdName = dpdName;
	}

	/**
	 * Get the ece.
	 * 
	 * @return the ece
	 */
	public String getEce() {
		return ece;
	}

	/**
	 * Set the ece.
	 *
	 * @param ece the ece to set
	 */
	public void setEce(String ece) {
		this.ece = ece;
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
	 * Get the stateId.
	 * 
	 * @return the stateId
	 */
	public String getStateId() {
		return stateId;
	}

	/**
	 * Set the stateId.
	 *
	 * @param stateId the stateId to set
	 */
	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

}
