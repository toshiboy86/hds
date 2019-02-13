/**
 * DataComponentModelsApi.java
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

import jp.ciof_cps.hds.dto.DataComponentModelDto;
import jp.ciof_cps.hds.dto.UserDto;
import jp.ciof_cps.hds.logic.AuthUtil;
import jp.ciof_cps.hds.logic.DataComponentModelsLogic;
import jp.ciof_cps.hds.logic.LogicException;

/**
 * This class is API for Data Component Models Logic
 */
@Path("/data_dictionaries/{dataDictionaryId}/dcms")
public class DataComponentModelsApi {
	static final Log LOG = LogFactory.getLog(DataComponentModelsApi.class);

	@Inject
	DataComponentModelsLogic dataComponentModelsLogic;

	/**
	 * @param dataDictionaryId The ID of data dictionary
	 * @param requestBodyDto The RequestBody Data Transfer Object
	 * @param securityContext The security context
	 * @return Return a Response object
	 */
	@POST
	@Path("/")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response post(@PathParam("dataDictionaryId") String dataDictionaryId, DataComponentModelDto requestBodyDto,
			@Context SecurityContext securityContext) {
		UserDto userDto = AuthUtil.extractUserDto(securityContext);
		return ApiWrapper.callLogic(userDto, new JaxrsLogicExecutor() {
			
			@Override
			public Response execute() throws LogicException {
				DataComponentModelDto newDto = dataComponentModelsLogic.create(userDto, dataDictionaryId,
						requestBodyDto);
				return ApiWrapper.buildResponse(Status.OK, newDto);
			}
		});
	}

	/**
	 * @param dataDictionaryId The ID of data dictionary
	 * @param dcmId The ID of DataComponentModelOperation
	 * @param requestBodyDto The RequestBody Data Transfer Object
	 * @param securityContext The security context
	 * @return Return a Response object
	 */
	@PUT
	@Path("/{dcmId}")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response put(@PathParam("dataDictionaryId") String dataDictionaryId, @PathParam("dcmId") String dcmId,
			DataComponentModelDto requestBodyDto, @Context SecurityContext securityContext) {
		UserDto userDto = AuthUtil.extractUserDto(securityContext);
		return ApiWrapper.callLogic(userDto, new JaxrsLogicExecutor() {
			
			@Override
			public Response execute() throws LogicException {
				DataComponentModelDto newDto = dataComponentModelsLogic.update(userDto, dataDictionaryId, dcmId,
						requestBodyDto);
				return ApiWrapper.buildResponse(Status.OK, newDto);
			}
		});
	}

	/**
	 * @param dataDictionaryId The ID of data dictionary
	 * @param dcmId The ID of DataComponentModelOperation
	 * @param securityContext The security context
	 * @return Return a Response object
	 */
	@DELETE
	@Path("/{dcmId}")
	public Response delete(@PathParam("dataDictionaryId") String dataDictionaryId, @PathParam("dcmId") String dcmId,
			@Context SecurityContext securityContext) {
		UserDto userDto = AuthUtil.extractUserDto(securityContext);
		return ApiWrapper.callLogic(userDto, new JaxrsLogicExecutor() {
			
			@Override
			public Response execute() throws LogicException {
				dataComponentModelsLogic.delete(userDto, dataDictionaryId, dcmId);
				return Response.status(Status.OK).build();
			}
		});
	}

	/**
	 * @param dataDictionaryId The ID of data dictionary
	 * @param dcmId The ID of DataComponentModelOperation
	 * @param securityContext The security context
	 * @return Return a Response object
	 */
	@GET
	@Path("/{dcmId}")
	@Produces({ "application/json" })
	public Response get(@PathParam("dataDictionaryId") String dataDictionaryId, @PathParam("dcmId") String dcmId,
			@Context SecurityContext securityContext) {
		UserDto userDto = AuthUtil.extractUserDto(securityContext);
		return ApiWrapper.callLogic(userDto, new JaxrsLogicExecutor() {
			
			@Override
			public Response execute() throws LogicException {
				DataComponentModelDto newDto = dataComponentModelsLogic.read(userDto, dataDictionaryId, dcmId);
				return ApiWrapper.buildResponse(Status.OK, newDto);
			}
		});
	}

	/**
	 * @param dataDictionaryId The ID of data dictionary
	 * @param securityContext The security context
	 * @return Return a Response object
	 */
	@GET
	@Path("/")
	@Produces({ "application/json" })
	public Response getList(@PathParam("dataDictionaryId") String dataDictionaryId,
			@Context SecurityContext securityContext) {
		UserDto userDto = AuthUtil.extractUserDto(securityContext);
		return ApiWrapper.callLogic(userDto, new JaxrsLogicExecutor() {
			
			@Override
			public Response execute() throws LogicException {
				List<DataComponentModelDto> newDtos = dataComponentModelsLogic.read(userDto, dataDictionaryId);
				return ApiWrapper.buildResponse(Status.OK, newDtos);
			}
		});
	}
}
