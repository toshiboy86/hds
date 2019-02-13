/**
 * DictionaryTranslationMapsApi.java
 * (C) 2019. Industrial Value Chain Initiative
 */
package jp.ciof_cps.hds.api;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jp.ciof_cps.hds.dto.DictionaryTranslationMapDto;
import jp.ciof_cps.hds.dto.UserDto;
import jp.ciof_cps.hds.logic.AuthUtil;
import jp.ciof_cps.hds.logic.DictionaryTranslationMapsLogic;
import jp.ciof_cps.hds.logic.LogicException;

/**
 * This class is API for Dictionary Translation Maps Logic
 */
@Path("/dtms")
public class DictionaryTranslationMapsApi {
	static final Log LOG = LogFactory.getLog(DictionaryTranslationMapsApi.class);
	
	@Inject
	DictionaryTranslationMapsLogic dictionaryTranslationMapsLogic;
	
	/**
	 * @param requestBodyDto The RequestBody Data Transfer Object
	 * @param securityContext The security context
	 * @return Return a Response object
	 */
	@POST
	@Path("/")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response post(DictionaryTranslationMapDto requestBodyDto, @Context SecurityContext securityContext) {
		UserDto userDto = AuthUtil.extractUserDto(securityContext);
		return ApiWrapper.callLogic(userDto, new JaxrsLogicExecutor() {
			
			@Override
			public Response execute() throws LogicException {
				DictionaryTranslationMapDto newDto = dictionaryTranslationMapsLogic.create(userDto, requestBodyDto);
				return ApiWrapper.buildResponse(Status.OK, newDto);
			}
		});
	}
	
	/**
	 * @param dtmId ID of Dictionary Translation Maps
	 * @param requestBodyDto The RequestBody Data Transfer Object
	 * @param securityContext The security context
	 * @return Return a Response object
	 */
	@PUT
	@Path("/{dtmId}")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response put(@PathParam("dtmId") String dtmId, DictionaryTranslationMapDto requestBodyDto, @Context SecurityContext securityContext) {
		UserDto userDto = AuthUtil.extractUserDto(securityContext);
		return ApiWrapper.callLogic(userDto, new JaxrsLogicExecutor() {
			
			@Override
			public Response execute() throws LogicException {
				DictionaryTranslationMapDto newDto = dictionaryTranslationMapsLogic.update(userDto, dtmId, requestBodyDto);
				return ApiWrapper.buildResponse(Status.OK, newDto);
			}
		});
	}
	
	/**
	 * @param dtmId ID of Dictionary Translation Maps
	 * @param securityContext The security context
	 * @return Return a Response object
	 */
	@DELETE
	@Path("/{dtmId}")
	public Response delete(@PathParam("dtmId") String dtmId, @Context SecurityContext securityContext) {
		UserDto userDto = AuthUtil.extractUserDto(securityContext);
		return ApiWrapper.callLogic(userDto, new JaxrsLogicExecutor() {
			
			@Override
			public Response execute() throws LogicException {
				dictionaryTranslationMapsLogic.delete(userDto, dtmId);
				return Response.status(Status.OK).build();
			}
		});
	}
	
	/**
	 * @param dtmId ID of Dictionary Translation Maps
	 * @param securityContext The security context
	 * @return Return a Response object
	 */
	@GET
	@Path("/{dtmId}")
	@Produces({ "application/json" })
	public Response get(@PathParam("dtmId") String dtmId, @Context SecurityContext securityContext) {
		UserDto userDto = AuthUtil.extractUserDto(securityContext);
		return ApiWrapper.callLogic(userDto, new JaxrsLogicExecutor() {
			
			@Override
			public Response execute() throws LogicException {
				DictionaryTranslationMapDto newDto = dictionaryTranslationMapsLogic.read(userDto, dtmId);
				return ApiWrapper.buildResponse(Status.OK, newDto);
			}
		});
	}
	
	/**
	 * @param securityContext The security context
	 * @return Return a Response object
	 */
	@GET
	@Path("/")
	@Produces({ "application/json" })
	public Response getList(@Context SecurityContext securityContext) {
		UserDto userDto = AuthUtil.extractUserDto(securityContext);
		return ApiWrapper.callLogic(userDto, new JaxrsLogicExecutor() {
			
			@Override
			public Response execute() throws LogicException {
				List<DictionaryTranslationMapDto> newDtos = dictionaryTranslationMapsLogic.read(userDto);
				return ApiWrapper.buildResponse(Status.OK, newDtos);
			}
		});
	}
}
