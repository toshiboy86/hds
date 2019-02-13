/**
 * RollbackExceptionMapper.java
 * (C) 2019. Industrial Value Chain Initiative
 */
package jp.ciof_cps.hds.api;

import javax.transaction.RollbackException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import jp.ciof_cps.hds.dto.ApiErrorResponseDto;

import org.hibernate.exception.ConstraintViolationException;

/**
 * This class supplements Rollback Exception.
 */
@Provider
public class RollbackExceptionMapper implements ExceptionMapper<RollbackException> {
	
	/**
	 * RollbackException analyze
	 * @param RollbackException e
	 * @return the jax-rs response.
	 */
	@Override
	public Response toResponse(RollbackException e) {
		Status status = null;
		ApiErrorResponseDto error = new ApiErrorResponseDto();
		Throwable cause = e.getCause();
		if (cause.getCause() instanceof ConstraintViolationException) {
			status = Status.fromStatusCode(Status.BAD_REQUEST.getStatusCode());
			error.setCode(Status.BAD_REQUEST.getStatusCode());
			error.setMessage("Failed to operate data.");
			error.setDetail(cause.getMessage());
		} else {
			status = Status.fromStatusCode(Status.INTERNAL_SERVER_ERROR.getStatusCode());	
			error.setCode(Status.INTERNAL_SERVER_ERROR.getStatusCode());
			error.setMessage("Failed to operate data.");
			error.setDetail(cause.getMessage());			
		}
		return  Response.status(status).entity(error).build();
		
	}

}
