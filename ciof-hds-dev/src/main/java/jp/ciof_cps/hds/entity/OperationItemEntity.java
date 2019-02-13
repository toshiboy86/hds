/**
 * OperationItem.java
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
 * The persistent class for the Operation Item database table.
 */
@Entity
@Table(name="operation_item")
@NamedQuery(name="OperationItemEntity.findAll", query="SELECT o FROM OperationItemEntity o")
public class OperationItemEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/** The Operation Item ID. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="operation_item_id", unique=true, nullable=false)
	private Integer operationItemId;

	/** The Data Component Model (DCM) Operation ID. */
	@Column(name="dcm_operation_id", nullable=false)
	private Integer dcmOperationId;

	/** The Operation. */
	@Column(nullable=false)
	private String operation;

	/**
	 * The constructor.
	 */
	public OperationItemEntity() {
	}

	/**
	 * Get the operationItemId.
	 * 
	 * @return the operationItemId
	 */
	public Integer getOperationItemId() {
		return operationItemId;
	}

	/**
	 * Set the operationItemId.
	 *
	 * @param operationItemId the operationItemId to set
	 */
	public void setOperationItemId(Integer operationItemId) {
		this.operationItemId = operationItemId;
	}

	/**
	 * Get the dcmOperationId.
	 * 
	 * @return the dcmOperationId
	 */
	public Integer getDcmOperationId() {
		return dcmOperationId;
	}

	/**
	 * Set the dcmOperationId.
	 *
	 * @param dcmOperationId the dcmOperationId to set
	 */
	public void setDcmOperationId(Integer dcmOperationId) {
		this.dcmOperationId = dcmOperationId;
	}

	/**
	 * Get the operation.
	 * 
	 * @return the operation
	 */
	public String getOperation() {
		return operation;
	}

	/**
	 * Set the operation.
	 *
	 * @param operation the operation to set
	 */
	public void setOperation(String operation) {
		this.operation = operation;
	}

}
