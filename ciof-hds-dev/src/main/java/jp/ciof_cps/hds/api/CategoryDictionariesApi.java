/**
 * CategoryDictionariesApi.java
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

import jp.ciof_cps.hds.dto.SpecificCategoryDictionaryDto;
import jp.ciof_cps.hds.dto.UserDto;
import jp.ciof_cps.hds.logic.AuthUtil;
import jp.ciof_cps.hds.logic.LogicException;
import jp.ciof_cps.hds.logic.SpecificCategoryDictionariesLogic;

/**
 * This class is API for Specific Category Dictionaries Logic
 */
@Path("/scds/")
public class CategoryDictionariesApi {
	static final Log LOG = LogFactory.getLog(CategoryDictionariesApi.class);
	@Inject
	SpecificCategoryDictionariesLogic specificCategoryDictionariesLogic;

	/**
	 * @param requestBodyDto The Request Body Data Transfer Object
	 * @param securityContext The securityContext
	 * @return Return a Response object
	 */
	@POST
	@Path("/")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response post(SpecificCategoryDictionaryDto requestBodyDto, @Context SecurityContext securityContext) {
		UserDto userDto = AuthUtil.extractUserDto(securityContext);
		return ApiWrapper.callLogic(userDto, new JaxrsLogicExecutor() {
			
			@Override
			public Response execute() throws LogicException {
				SpecificCategoryDictionaryDto newDto = specificCategoryDictionariesLogic.create(userDto, requestBodyDto);
				return ApiWrapper.buildResponse(Status.OK, newDto);
			}
		});
	}

	/**
	 * @param categoryDictionaryId ID of category dictionaries 
	 * @param requestBodyDto The Request Body Data Transfer Object
	 * @param securityContext The securityContext
	 * @return Return a Response object
	 */
	@PUT
	@Path("/{categoryDictionaryId}")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response put(@PathParam("categoryDictionaryId") String categoryDictionaryId, SpecificCategoryDictionaryDto requestBodyDto,
			@Context SecurityContext securityContext) {
		UserDto userDto = AuthUtil.extractUserDto(securityContext);
		return ApiWrapper.callLogic(userDto, new JaxrsLogicExecutor() {
			
			@Override
			public Response execute() throws LogicException {
				SpecificCategoryDictionaryDto newDto = specificCategoryDictionariesLogic.update(userDto, categoryDictionaryId, requestBodyDto);
				return ApiWrapper.buildResponse(Status.OK, newDto);
			}
		});
	}

	/**
	 * @param categoryDictionaryId ID of category dictionaries
	 * @param securityContext The securityContext
	 * @return Return a Response object
	 */
	@DELETE
	@Path("/{categoryDictionaryId}")
	public Response delete(@PathParam("categoryDictionaryId") String categoryDictionaryId,
			@Context SecurityContext securityContext) {
		UserDto userDto = AuthUtil.extractUserDto(securityContext);
		return ApiWrapper.callLogic(userDto, new JaxrsLogicExecutor() {
			
			@Override
			public Response execute() throws LogicException {
				specificCategoryDictionariesLogic.delete(userDto, categoryDictionaryId);
				return Response.status(Status.OK).build();
			}
		});
	}

	/**
	 * @param categoryDictionaryId ID of category dictionaries
	 * @param securityContext The securityContext
	 * @return Return a Response object
	 */
	@GET
	@Path("/{categoryDictionaryId}")
	@Produces({ "application/json" })
	public Response get(@PathParam("categoryDictionaryId") String categoryDictionaryId,
			@Context SecurityContext securityContext) {
		UserDto userDto = AuthUtil.extractUserDto(securityContext);
		return ApiWrapper.callLogic(userDto, new JaxrsLogicExecutor() {
			
			@Override
			public Response execute() throws LogicException {
				SpecificCategoryDictionaryDto newDto = specificCategoryDictionariesLogic.read(userDto, categoryDictionaryId);
				return ApiWrapper.buildResponse(Status.OK, newDto);
			}
		});
	}

	/**
	 * @param securityContext The securityContext
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
				List<SpecificCategoryDictionaryDto> newDtos = specificCategoryDictionariesLogic.read(userDto);
				return ApiWrapper.buildResponse(Status.OK, newDtos);
			}
		});
	}
}
