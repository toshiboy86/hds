/**
 * ProcessComponentModelsApi.java
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

import jp.ciof_cps.hds.dto.ProcessComponentModelDto;
import jp.ciof_cps.hds.dto.UserDto;
import jp.ciof_cps.hds.logic.AuthUtil;
import jp.ciof_cps.hds.logic.LogicException;
import jp.ciof_cps.hds.logic.ProcessComponentModelsLogic;

/**
 * This class is API for Process Component Models Logic
 */
@Path("/service_dictionaries/{serviceDictionaryId}/pcms")
public class ProcessComponentModelsApi {
	static final Log LOG = LogFactory.getLog(ProcessComponentModelsApi.class);

	@Inject
	ProcessComponentModelsLogic processComponentModelsLogic;

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
	public Response post(@PathParam("serviceDictionaryId") String serviceDictionaryId, ProcessComponentModelDto requestBodyDto, @Context SecurityContext securityContext) {
		UserDto userDto = AuthUtil.extractUserDto(securityContext);
		return ApiWrapper.callLogic(userDto, new JaxrsLogicExecutor() {
			
			@Override
			public Response execute() throws LogicException {
				ProcessComponentModelDto newDto = processComponentModelsLogic.create(userDto, serviceDictionaryId, requestBodyDto);
				return ApiWrapper.buildResponse(Status.OK, newDto);
			}
		});
	}

	/**
	 * @param serviceDictionaryId ID of service dictionary
	 * @param pcmId ID of Process Model
	 * @param requestBodyDto The RequestBody Data Transfer Object
	 * @param securityContext The security context
	 * @return Return a Response object
	 */
	@PUT
	@Path("/{pcmId}")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response put(@PathParam("serviceDictionaryId") String serviceDictionaryId, @PathParam("pcmId") String pcmId, ProcessComponentModelDto requestBodyDto,
			@Context SecurityContext securityContext) {
		UserDto userDto = AuthUtil.extractUserDto(securityContext);
		return ApiWrapper.callLogic(userDto, new JaxrsLogicExecutor() {
			
			@Override
			public Response execute() throws LogicException {
				ProcessComponentModelDto newDto = processComponentModelsLogic.update(userDto, serviceDictionaryId, pcmId, requestBodyDto);
				return ApiWrapper.buildResponse(Status.OK, newDto);
			}
		});
	}

	/**
	 * @param serviceDictionaryId ID of service dictionary
	 * @param pcmId ID of Process Model
	 * @param securityContext The security context
	 * @return Return a Response object
	 */
	@DELETE
	@Path("/{pcmId}")
	public Response delete(@PathParam("serviceDictionaryId") String serviceDictionaryId, @PathParam("pcmId") String pcmId,
			@Context SecurityContext securityContext) {
		UserDto userDto = AuthUtil.extractUserDto(securityContext);
		return ApiWrapper.callLogic(userDto, new JaxrsLogicExecutor() {
			
			@Override
			public Response execute() throws LogicException {
				processComponentModelsLogic.delete(userDto, serviceDictionaryId, pcmId);
				return Response.status(Status.OK).build();
			}
		});
	}

	/**
	 * @param serviceDictionaryId ID of service dictionary
	 * @param pcmId ID of Process Model
	 * @param securityContext The security context
	 * @return Return a Response object
	 */
	@GET
	@Path("/{pcmId}")
	@Produces({ "application/json" })
	public Response get(@PathParam("serviceDictionaryId") String serviceDictionaryId, @PathParam("pcmId") String pcmId,
			@Context SecurityContext securityContext) {
		UserDto userDto = AuthUtil.extractUserDto(securityContext);
		return ApiWrapper.callLogic(userDto, new JaxrsLogicExecutor() {
			
			@Override
			public Response execute() throws LogicException {
				ProcessComponentModelDto newDto = processComponentModelsLogic.read(userDto, serviceDictionaryId, pcmId);
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
				List<ProcessComponentModelDto> newDtos = processComponentModelsLogic.read(userDto, serviceDictionaryId);
				return ApiWrapper.buildResponse(Status.OK, newDtos);
			}
		});
	}
}
