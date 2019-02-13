/**
 * LogicException.java
 * (C) 2019. Industrial Value Chain Initiative
 */
package jp.ciof_cps.hds.logic;

/**
 * This class is Thrown to indicate that an error occurs in logic layer.
 */
public class LogicException extends Exception {

	private static final long serialVersionUID = -6287469993676128843L;

	private Integer code;
	
	private String detail;
	
	/**
	 * A constructor.
	 * @param message the message
	 * @param code the error code
	 */
	public LogicException(String message, Integer code) {
		this(message, code, null);
	}
	
	/**
	 * A constructor.
	 * @param message the message
	 * @param code the error code
	 * @param detail the detail message
	 */
	public LogicException(String message, Integer code, String detail) {
		super(message);
		this.code = code;
		this.detail = detail;
	}
	
	/**
	 * A constructor.
	 * @param message the message
	 * @param code the error code
	 * @param detail the detail message
	 * @param cause the cause
	 */
	public LogicException(String message, Integer code, String detail, Throwable cause) {
		super(message, cause);
		this.code = code;
		this.detail = detail;
	}
	
	/**
	 * Return the error code.
	 * @return the value
	 */
	public Integer getCode() {
		return code;
	}
	
	/**
	 * Return the detail message.
	 * @return the value
	 */
	public String getDetail() {
		return detail;
	}
}
