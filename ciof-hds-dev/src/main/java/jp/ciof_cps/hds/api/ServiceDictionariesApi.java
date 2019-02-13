/**
 * ServiceDictionariesApi.java
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jp.ciof_cps.hds.dto.ServiceDictionaryDto;
import jp.ciof_cps.hds.dto.UserDto;
import jp.ciof_cps.hds.logic.AuthUtil;
import jp.ciof_cps.hds.logic.LogicException;
import jp.ciof_cps.hds.logic.ServiceDictionariesLogic;


/**
 * This class is API for Service Dictionaries Logic
 */
@Path("/service_dictionaries/")
public class ServiceDictionariesApi {
	static final Log LOG = LogFactory.getLog(DataDictionariesApi.class);
	@Inject
	ServiceDictionariesLogic serviceDictionariesLogic;

	/**
	 * @param requestBodyDto The RequestBody Data Transfer Object
	 * @param securityContext The security context
	 * @return Return a Response object
	 */
	@POST
	@Path("/")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response post(ServiceDictionaryDto requestBodyDto, @Context SecurityContext securityContext) {
		UserDto userDto = AuthUtil.extractUserDto(securityContext);
		return ApiWrapper.callLogic(userDto, new JaxrsLogicExecutor() {
			
			@Override
			public Response execute() throws LogicException {
				ServiceDictionaryDto newDto = serviceDictionariesLogic.create(userDto, requestBodyDto);
				return ApiWrapper.buildResponse(Status.OK, newDto);
			}
		});
	}

	/**
	 * @param serviceDictionaryId ID of service dictionary
	 * @param requestBodyDto The RequestBody Data Transfer Object
	 * @param securityContext The security context
	 * @return Return a Response object
	 */
	@PUT
	@Path("/{serviceDictionaryId}")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response put(@PathParam("serviceDictionaryId") String serviceDictionaryId, ServiceDictionaryDto requestBodyDto,
			@Context SecurityContext securityContext) {
		UserDto userDto = AuthUtil.extractUserDto(securityContext);
		return ApiWrapper.callLogic(userDto, new JaxrsLogicExecutor() {
			
			@Override
			public Response execute() throws LogicException {
				ServiceDictionaryDto newDto = serviceDictionariesLogic.update(userDto, serviceDictionaryId, requestBodyDto);
				return ApiWrapper.buildResponse(Status.OK, newDto);
			}
		});
	}

	/**
	 * @param serviceDictionaryId ID of service dictionary
	 * @param securityContext The security context
	 * @return Return a Response object
	 */
	@DELETE
	@Path("/{serviceDictionaryId}")
	public Response delete(@PathParam("serviceDictionaryId") String serviceDictionaryId,
			@Context SecurityContext securityContext) {
		UserDto userDto = AuthUtil.extractUserDto(securityContext);
		return ApiWrapper.callLogic(userDto, new JaxrsLogicExecutor() {
			
			@Override
			public Response execute() throws LogicException {
				serviceDictionariesLogic.delete(userDto, serviceDictionaryId);
				return Response.status(Status.OK).build();
			}
		});
	}

	/**
	 * @param serviceDictionaryId ID of service dictionary
	 * @param securityContext The security context
	 * @return Return a Response object
	 */
	@GET
	@Path("/{serviceDictionaryId}")
	@Produces({ "application/json" })
	public Response get(@PathParam("serviceDictionaryId") String serviceDictionaryId,
			@Context SecurityContext securityContext) {
		UserDto userDto = AuthUtil.extractUserDto(securityContext);
		return ApiWrapper.callLogic(userDto, new JaxrsLogicExecutor() {
			
			@Override
			public Response execute() throws LogicException {
				ServiceDictionaryDto newDto = serviceDictionariesLogic.read(userDto, serviceDictionaryId);
				return ApiWrapper.buildResponse(Status.OK, newDto);
			}
		});
	}

	/**
	 * @param serviceDictionaryType Type of service dictionary
	 * @param securityContext The security context
	 * @return Return a Response object
	 */
	@GET
	@Path("/")
	@Produces({ "application/json" })
	public Response getList(@QueryParam("service_dictionary_type") String serviceDictionaryType,
			@Context SecurityContext securityContext) {
		UserDto userDto = AuthUtil.extractUserDto(securityContext);
		return ApiWrapper.callLogic(userDto, new JaxrsLogicExecutor() {
			
			@Override
			public Response execute() throws LogicException {
				List<ServiceDictionaryDto> newDtos = serviceDictionariesLogic.readList(userDto, serviceDictionaryType);
				return ApiWrapper.buildResponse(Status.OK, newDtos);
			}
		});
	}
}
