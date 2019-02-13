/**
 * DataRelationModelsApi.java
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

import jp.ciof_cps.hds.dto.DataRelationModelDto;
import jp.ciof_cps.hds.dto.UserDto;
import jp.ciof_cps.hds.logic.AuthUtil;
import jp.ciof_cps.hds.logic.DataRelationModelsLogic;
import jp.ciof_cps.hds.logic.LogicException;

/**
 * This class is API for Data Relation Models Logic
 */
@Path("/data_dictionaries/{dataDictionaryId}/drms")
public class DataRelationModelsApi {
	static final Log LOG = LogFactory.getLog(DataRelationModelsApi.class);

	@Inject
	DataRelationModelsLogic dataRelationModelsLogic;

	/**
	 * @param dataDictionaryId ID of data dictionary
	 * @param requestBodyDto The RequestBody Data Transfer Object
	 * @param securityContext The security context
	 * @return Return a Response object
	 */
	@POST
	@Path("/")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response post(@PathParam("dataDictionaryId") String dataDictionaryId, DataRelationModelDto requestBodyDto,
			@Context SecurityContext securityContext) {
		UserDto userDto = AuthUtil.extractUserDto(securityContext);
		return ApiWrapper.callLogic(userDto, new JaxrsLogicExecutor() {
			
			@Override
			public Response execute() throws LogicException {
				DataRelationModelDto newDto = dataRelationModelsLogic.create(userDto, dataDictionaryId,
						requestBodyDto);
				return ApiWrapper.buildResponse(Status.OK, newDto);
			}
		});
	}

	/**
	 * @param dataDictionaryId ID of data dictionary
	 * @param drmId ID of Data Relation Model
	 * @param requestBodyDto The RequestBody Data Transfer Object
	 * @param securityContext The security context
	 * @return Return a Response object
	 */
	@PUT
	@Path("/{drmId}")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response put(@PathParam("dataDictionaryId") String dataDictionaryId, @PathParam("drmId") String drmId,
			DataRelationModelDto requestBodyDto, @Context SecurityContext securityContext) {
		UserDto userDto = AuthUtil.extractUserDto(securityContext);
		return ApiWrapper.callLogic(userDto, new JaxrsLogicExecutor() {
			
			@Override
			public Response execute() throws LogicException {
				DataRelationModelDto newDto = dataRelationModelsLogic.update(userDto, dataDictionaryId, drmId,
						requestBodyDto);
				return ApiWrapper.buildResponse(Status.OK, newDto);
			}
		});
	}

	/**
	 * @param dataDictionaryId ID of data dictionary
	 * @param drmId ID of Data Relation Model
	 * @param securityContext The security context
	 * @return Return a Response object
	 */
	@DELETE
	@Path("/{drmId}")
	public Response delete(@PathParam("dataDictionaryId") String dataDictionaryId, @PathParam("drmId") String drmId,
			@Context SecurityContext securityContext) {
		UserDto userDto = AuthUtil.extractUserDto(securityContext);
		return ApiWrapper.callLogic(userDto, new JaxrsLogicExecutor() {
			
			@Override
			public Response execute() throws LogicException {
				dataRelationModelsLogic.delete(userDto, dataDictionaryId, drmId);
				return Response.status(Status.OK).build();
			}
		});
	}

	/**
	 * @param dataDictionaryId ID of data dictionary
	 * @param drmId ID of Data Relation Model
	 * @param securityContext The security context
	 * @return Return a Response object
	 */
	@GET
	@Path("/{drmId}")
	@Produces({ "application/json" })
	public Response get(@PathParam("dataDictionaryId") String dataDictionaryId, @PathParam("drmId") String drmId,
			@Context SecurityContext securityContext) {
		UserDto userDto = AuthUtil.extractUserDto(securityContext);
		return ApiWrapper.callLogic(userDto, new JaxrsLogicExecutor() {
			
			@Override
			public Response execute() throws LogicException {
				DataRelationModelDto newDto = dataRelationModelsLogic.read(userDto, dataDictionaryId, drmId);
				return ApiWrapper.buildResponse(Status.OK, newDto);
			}
		});
	}

	/**
	 * @param dataDictionaryId ID of data dictionary
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
				List<DataRelationModelDto> newDtos = dataRelationModelsLogic.read(userDto, dataDictionaryId);
				return ApiWrapper.buildResponse(Status.OK, newDtos);
			}
		});
	}
}
