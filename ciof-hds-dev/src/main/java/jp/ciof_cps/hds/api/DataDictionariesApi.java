/**
 * DataDictionariesApi.java
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

import jp.ciof_cps.hds.dto.DataDictionaryDto;
import jp.ciof_cps.hds.dto.UserDto;
import jp.ciof_cps.hds.logic.AuthUtil;
import jp.ciof_cps.hds.logic.DataDictionariesLogic;
import jp.ciof_cps.hds.logic.LogicException;

/**
 * This class is API for Data Dictionaries Logic
 */
@Path("/data_dictionaries")
public class DataDictionariesApi {
	static final Log LOG = LogFactory.getLog(DataDictionariesApi.class);
	@Inject
	DataDictionariesLogic dataDictionariesLogic;

	/**
	 * @param requestBodyDto The RequestBody Data Transfer Object
	 * @param securityContext The security Context
	 * @return Return a Response object
	 */
	@POST
	@Path("/")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response post(DataDictionaryDto requestBodyDto, @Context SecurityContext securityContext) {
		UserDto userDto = AuthUtil.extractUserDto(securityContext);
		return ApiWrapper.callLogic(userDto, new JaxrsLogicExecutor() {
			
			@Override
			public Response execute() throws LogicException {
				DataDictionaryDto newDto = dataDictionariesLogic.create(userDto, requestBodyDto);
				return ApiWrapper.buildResponse(Status.OK, newDto);
			}
		});
	}

	/**
	 * @param dataDictionaryId ID of data dictionary
	 * @param requestBodyDto The RequestBody Data Transfer Object
	 * @param securityContext The security Context
	 * @return Return a Response object
	 */
	@PUT
	@Path("/{dataDictionaryId}")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response put(@PathParam("dataDictionaryId") String dataDictionaryId, DataDictionaryDto requestBodyDto,
			@Context SecurityContext securityContext) {
		UserDto userDto = AuthUtil.extractUserDto(securityContext);
		return ApiWrapper.callLogic(userDto, new JaxrsLogicExecutor() {
			
			@Override
			public Response execute() throws LogicException {
				DataDictionaryDto newDto = dataDictionariesLogic.update(userDto, dataDictionaryId, requestBodyDto);
				return ApiWrapper.buildResponse(Status.OK, newDto);
			}
		});
	}

	/**
	 * @param dataDictionaryId ID of data dictionary
	 * @param securityContext The security Context
	 * @return Return a Response object
	 */
	@DELETE
	@Path("/{dataDictionaryId}")
	public Response delete(@PathParam("dataDictionaryId") String dataDictionaryId,
			@Context SecurityContext securityContext) {
		UserDto userDto = AuthUtil.extractUserDto(securityContext);
		return ApiWrapper.callLogic(userDto, new JaxrsLogicExecutor() {
			
			@Override
			public Response execute() throws LogicException {
				dataDictionariesLogic.delete(userDto, dataDictionaryId);
				return Response.status(Status.OK).build();
			}
		});
	}

	/**
	 * @param dataDictionaryId ID of data dictionary
	 * @param securityContext The security Context
	 * @return Return a Response object
	 */
	@GET
	@Path("/{dataDictionaryId}")
	@Produces({ "application/json" })
	public Response get(@PathParam("dataDictionaryId") String dataDictionaryId,
			@Context SecurityContext securityContext) {
		UserDto userDto = AuthUtil.extractUserDto(securityContext);
		return ApiWrapper.callLogic(userDto, new JaxrsLogicExecutor() {
			
			@Override
			public Response execute() throws LogicException {
				DataDictionaryDto newDto = dataDictionariesLogic.read(userDto, dataDictionaryId);
				return ApiWrapper.buildResponse(Status.OK, newDto);
			}
		});
	}

	/**
	 * @param dataDictionaryType Type of data dictionary
	 * @param securityContext The security Context
	 * @return Return a Response object
	 */
	@GET
	@Path("/")
	@Produces({ "application/json" })
	public Response getList(@QueryParam("data_dictionary_type") String dataDictionaryType,
			@Context SecurityContext securityContext) {
		UserDto userDto = AuthUtil.extractUserDto(securityContext);
		return ApiWrapper.callLogic(userDto, new JaxrsLogicExecutor() {
			
			@Override
			public Response execute() throws LogicException {
				List<DataDictionaryDto> newDtos = dataDictionariesLogic.readList(userDto, dataDictionaryType);
				return ApiWrapper.buildResponse(Status.OK, newDtos);
			}
		});
	}
}
