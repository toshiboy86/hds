/**
 * UserOperationEventEntity.java
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
 * The persistent class for the User Operation Event database table.
 */
@Entity
@Table(name="user_operation_event")
@NamedQuery(name="UserOperationEventEntity.findAll", query="SELECT u FROM UserOperationEventEntity u")
public class UserOperationEventEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final int COLUMN_LENGTH = 256;

	/** The User Operation Event ID. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_operation_event_id", unique=true, nullable=false)
	private Integer userOperationEventId;

	/** The Event and Condition Model (ECM) ID. */
	@Column(name="ecm_id", nullable=false, length=COLUMN_LENGTH)
	private String ecmId;

	/** The Operation Procedure Content. */
	@Column(name="operation_procedure_content", nullable=false, length=COLUMN_LENGTH)
	private String operationProcedureContent;

	/** The Target Object. */
	@Column(name="target_object", nullable=false, length=COLUMN_LENGTH)
	private String targetObject;

	/**
	 * The constructor.
	 */
	public UserOperationEventEntity() {
	}

	/**
	 * Get the userOperationEventId.
	 * 
	 * @return the userOperationEventId
	 */
	public Integer getUserOperationEventId() {
		return userOperationEventId;
	}

	/**
	 * Set the userOperationEventId.
	 *
	 * @param userOperationEventId the userOperationEventId to set
	 */
	public void setUserOperationEventId(Integer userOperationEventId) {
		this.userOperationEventId = userOperationEventId;
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
	 * Get the operationProcedureContent.
	 * 
	 * @return the operationProcedureContent
	 */
	public String getOperationProcedureContent() {
		return operationProcedureContent;
	}

	/**
	 * Set the operationProcedureContent.
	 *
	 * @param operationProcedureContent the operationProcedureContent to set
	 */
	public void setOperationProcedureContent(String operationProcedureContent) {
		this.operationProcedureContent = operationProcedureContent;
	}

	/**
	 * Get the targetObject.
	 * 
	 * @return the targetObject
	 */
	public String getTargetObject() {
		return targetObject;
	}

	/**
	 * Set the targetObject.
	 *
	 * @param targetObject the targetObject to set
	 */
	public void setTargetObject(String targetObject) {
		this.targetObject = targetObject;
	}

}
