/**
 * ApiWrapper.java
 * (C) 2019. Industrial Value Chain Initiative
 */
package jp.ciof_cps.hds.api;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jp.ciof_cps.hds.dto.UserDto;
import jp.ciof_cps.hds.dto.ApiErrorResponseDto;
import jp.ciof_cps.hds.logic.LogicException;

/**
 * This class is an utility class for Api.
 */
final class ApiWrapper {
	static final Log LOG = LogFactory.getLog(ApiWrapper.class);

	/**
	 * Wrap API stereo-type procedures.
	 * @param userDto user information 
	 * @param jaxrsLogicExecutor executor of logic
	 * @return the jax-rs response.
	 */
	static Response callLogic(UserDto userDto, JaxrsLogicExecutor jaxrsLogicExecutor) {
		LOG.info("callLogic() called.:");
		
		Response response = null;
		try {
			response = jaxrsLogicExecutor.execute();
		} catch (LogicException e) {
			LOG.warn("Failed to call logic.", e);
			
			Status status = Status.fromStatusCode(e.getCode());
			
			ApiErrorResponseDto error = new ApiErrorResponseDto();
			error.setCode(e.getCode());
			error.setMessage(e.getMessage());
			error.setDetail(e.getDetail());
			
			response = buildResponse(status, error);
		}
		
		return response;
	}
	
	/**
	 * Construct ResponseBuilder instance.
	 * @param status HTTP status
	 * @param entity HTTP body entity
	 * @return the instance.
	 */
	static ResponseBuilder constructResponseBuilder(Status status, Object entity) {
		return Response.status(status).entity(entity);
	}
	
	/**
	 * Build Response instance.
	 * @param status HTTP status
	 * @param entity HTTP body entity
	 * @return the instance
	 */
	static Response buildResponse(Status status, Object entity) {
		return constructResponseBuilder(status, entity).build();
	}
	
	/** A constructor (private). */
	private ApiWrapper() {
		super();
	}
}

/**
 * This is the executor interface.
 */
interface JaxrsLogicExecutor {

	/**
	 * Executes logic.
	 * @return the response
	 * @throws LogicException Error occurs in logic layer.
	 */
	Response execute() throws LogicException;
}
