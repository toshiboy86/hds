/**
 * ApiErrorResponseDto.java
 * (C) 2019. Industrial Value Chain Initiative
 */
package jp.ciof_cps.hds.dto;

/**
 * This class is the DTO class for HTTP error response.
 */
public class ApiErrorResponseDto {

	private Integer code;
	
	private String message;
	
	private String detail;

	/**
	 * Get the code.
	 * 
	 * @return the code
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * Set the code.
	 *
	 * @param code the code to set
	 */
	public void setCode(Integer code) {
		this.code = code;
	}

	/**
	 * Get the message.
	 * 
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Set the message.
	 *
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Get the detail.
	 * 
	 * @return the detail
	 */
	public String getDetail() {
		return detail;
	}

	/**
	 * Set the detail.
	 *
	 * @param detail the detail to set
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	
}
