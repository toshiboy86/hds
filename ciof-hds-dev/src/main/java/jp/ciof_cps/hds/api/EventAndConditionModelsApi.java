/**
 * EventAndConditionModelsApi.java
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

import jp.ciof_cps.hds.dto.EventAndConditionModelDto;
import jp.ciof_cps.hds.dto.UserDto;
import jp.ciof_cps.hds.logic.AuthUtil;
import jp.ciof_cps.hds.logic.EventAndConditionModelsLogic;
import jp.ciof_cps.hds.logic.LogicException;

/**
 * This class is API for Event and Condition Models Logic
 */
@Path("/service_dictionaries/{serviceDictionaryId}/ecms")
public class EventAndConditionModelsApi {
	static final Log LOG = LogFactory.getLog(EventAndConditionModelsApi.class);
	@Inject
	EventAndConditionModelsLogic eventAndConditionModelsLogic;

	/**
	 * @param serviceDictionaryId ID of service dictionary
	 * @param requestBodyDto The RequestBody Data Transfer Object
	 * @param securityContext The security context
	 * @return Return a Response object
	 */
	@POST
	@Path("/")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response post(@PathParam("serviceDictionaryId") String serviceDictionaryId, EventAndConditionModelDto requestBodyDto, @Context SecurityContext securityContext) {
		UserDto userDto = AuthUtil.extractUserDto(securityContext);
		return ApiWrapper.callLogic(userDto, new JaxrsLogicExecutor() {
			
			@Override
			public Response execute() throws LogicException {
				EventAndConditionModelDto newDto = eventAndConditionModelsLogic.create(userDto, serviceDictionaryId, requestBodyDto);
				return ApiWrapper.buildResponse(Status.OK, newDto);
			}
		});
	}

	/**
	 * @param serviceDictionaryId ID of service dictionary
	 * @param ecmId ID of Event and Condition Models
	 * @param requestBodyDto The RequestBody Data Transfer Object
	 * @param securityContext The security context
	 * @return Return a Response object
	 */
	@PUT
	@Path("/{ecmId}")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response put(@PathParam("serviceDictionaryId") String serviceDictionaryId, @PathParam("ecmId") String ecmId, EventAndConditionModelDto requestBodyDto,
			@Context SecurityContext securityContext) {
		UserDto userDto = AuthUtil.extractUserDto(securityContext);
		return ApiWrapper.callLogic(userDto, new JaxrsLogicExecutor() {
			
			@Override
			public Response execute() throws LogicException {
				EventAndConditionModelDto newDto = eventAndConditionModelsLogic.update(userDto, serviceDictionaryId, ecmId, requestBodyDto);
				return ApiWrapper.buildResponse(Status.OK, newDto);
			}
		});
	}

	/**
	 * @param serviceDictionaryId ID of service dictionary
	 * @param ecmId ID of Event and Condition Models
	 * @param securityContext The security context
	 * @return Return a Response object
	 */
	@DELETE
	@Path("/{ecmId}")
	public Response delete(@PathParam("serviceDictionaryId") String serviceDictionaryId, @PathParam("ecmId") String ecmId,
			@Context SecurityContext securityContext) {
		UserDto userDto = AuthUtil.extractUserDto(securityContext);
		return ApiWrapper.callLogic(userDto, new JaxrsLogicExecutor() {
			
			@Override
			public Response execute() throws LogicException {
				eventAndConditionModelsLogic.delete(userDto, serviceDictionaryId, ecmId);
				return Response.status(Status.OK).build();
			}
		});
	}

	/**
	 * @param serviceDictionaryId ID of service dictionary
	 * @param ecmId ID of Event and Condition Models
	 * @param securityContext The security context
	 * @return Return a Response object
	 */
	@GET
	@Path("/{ecmId}")
	@Produces({ "application/json" })
	public Response get(@PathParam("serviceDictionaryId") String serviceDictionaryId, @PathParam("ecmId") String ecmId,
			@Context SecurityContext securityContext) {
		UserDto userDto = AuthUtil.extractUserDto(securityContext);
		return ApiWrapper.callLogic(userDto, new JaxrsLogicExecutor() {
			
			@Override
			public Response execute() throws LogicException {
				EventAndConditionModelDto newDto = eventAndConditionModelsLogic.read(userDto, serviceDictionaryId, ecmId);
				return ApiWrapper.buildResponse(Status.OK, newDto);
			}
		});
	}

	/**
	 * @param serviceDictionaryId ID of service dictionary
	 * @param securityContext The security context
	 * @return Return a Response object
	 */
	@GET
	@Path("/")
	@Produces({ "application/json" })
	public Response get(@PathParam("serviceDictionaryId") String serviceDictionaryId, 
			@Context SecurityContext securityContext) {
		UserDto userDto = AuthUtil.extractUserDto(securityContext);
		return ApiWrapper.callLogic(userDto, new JaxrsLogicExecutor() {
			
			@Override
			public Response execute() throws LogicException {
				List<EventAndConditionModelDto> newDtos = eventAndConditionModelsLogic.read(userDto, serviceDictionaryId);
				return ApiWrapper.buildResponse(Status.OK, newDtos);
			}
		});
	}
}
