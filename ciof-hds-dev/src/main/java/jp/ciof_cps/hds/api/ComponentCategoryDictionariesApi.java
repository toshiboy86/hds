/**
 * ComponentCategoryDictionariesApi.java
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

import jp.ciof_cps.hds.dto.ComponentCategoryDto;
import jp.ciof_cps.hds.dto.UserDto;
import jp.ciof_cps.hds.logic.AuthUtil;
import jp.ciof_cps.hds.logic.ComponentCategoriesLogic;
import jp.ciof_cps.hds.logic.LogicException;

/**
 * This class is API for Component Category Logic
 */
@Path("/scds/{scdId}/component_categories")
public class ComponentCategoryDictionariesApi {
	static final Log LOG = LogFactory.getLog(ComponentCategoryDictionariesApi.class);
	@Inject
	ComponentCategoriesLogic componentCategoriesLogic;

	/**
	 * @param scdId Category Dictionary ID
	 * @param requestBodyDto The RequestBody Data Transfer Object
	 * @param securityContext The security Context
	 * @return Return a Response object
	 */
	@POST
	@Path("/")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response post(@PathParam("scdId") String scdId, ComponentCategoryDto requestBodyDto, @Context SecurityContext securityContext) {
		UserDto userDto = AuthUtil.extractUserDto(securityContext);
		return ApiWrapper.callLogic(userDto, new JaxrsLogicExecutor() {
			
			@Override
			public Response execute() throws LogicException {
				ComponentCategoryDto newDto = componentCategoriesLogic.create(userDto, scdId, requestBodyDto);
				return ApiWrapper.buildResponse(Status.OK, newDto);
			}
		});
	}

	/**
	 * @param scdId Category Dictionary ID
	 * @param componentCategoryId The ID of Component Category
	 * @param requestBodyDto The RequesBody of Data Transfer Object
	 * @param securityContext The security Context
	 * @return Return a Response object
	 */
	@PUT
	@Path("/{componentCategoryId}")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response put(@PathParam("scdId") String scdId, @PathParam("componentCategoryId") String componentCategoryId, ComponentCategoryDto requestBodyDto,
			@Context SecurityContext securityContext) {
		UserDto userDto = AuthUtil.extractUserDto(securityContext);
		return ApiWrapper.callLogic(userDto, new JaxrsLogicExecutor() {
			
			@Override
			public Response execute() throws LogicException {
				ComponentCategoryDto newDto = componentCategoriesLogic.update(userDto, scdId, componentCategoryId, requestBodyDto);
				return ApiWrapper.buildResponse(Status.OK, newDto);
			}
		});
	}

	/**
	 * @param scdId Category Dictionary ID
	 * @param componentCategoryId The ID of Component Category
	 * @param securityContext The security Context
	 * @return Return a Response object
	 */
	@DELETE
	@Path("/{componentCategoryId}")
	public Response delete(@PathParam("scdId") String scdId, @PathParam("componentCategoryId") String componentCategoryId,
			@Context SecurityContext securityContext) {
		UserDto userDto = AuthUtil.extractUserDto(securityContext);
		return ApiWrapper.callLogic(userDto, new JaxrsLogicExecutor() {
			
			@Override
			public Response execute() throws LogicException {
				componentCategoriesLogic.delete(userDto, scdId, componentCategoryId);
				return Response.status(Status.OK).build();
			}
		});
	}

	/**
	 * @param scdId Category Dictionary ID
	 * @param componentCategoryId The ID of Component Category
	 * @param securityContext The security Context
	 * @return Return a Response object
	 */
	@GET
	@Path("/{componentCategoryId}")
	@Produces({ "application/json" })
	public Response get(@PathParam("scdId") String scdId, @PathParam("componentCategoryId") String componentCategoryId,
			@Context SecurityContext securityContext) {
		UserDto userDto = AuthUtil.extractUserDto(securityContext);
		return ApiWrapper.callLogic(userDto, new JaxrsLogicExecutor() {
			
			@Override
			public Response execute() throws LogicException {
				ComponentCategoryDto newDto = componentCategoriesLogic.read(userDto, scdId, componentCategoryId);
				return ApiWrapper.buildResponse(Status.OK, newDto);
			}
		});
	}

	/**
	 * @param scdId Category Dictionary ID
	 * @param componentCategoryType Type of component category
	 * @param securityContext The security Context
	 * @return Return a Response object
	 */
	@GET
	@Path("/")
	@Produces({ "application/json" })
	public Response getList(@PathParam("scdId") String scdId, @QueryParam("component_category_type") String componentCategoryType, @Context SecurityContext securityContext) {
		UserDto userDto = AuthUtil.extractUserDto(securityContext);
		return ApiWrapper.callLogic(userDto, new JaxrsLogicExecutor() {
			
			@Override
			public Response execute() throws LogicException {
				List<ComponentCategoryDto> newDtos = componentCategoriesLogic.readList(userDto, scdId, componentCategoryType);
				return ApiWrapper.buildResponse(Status.OK, newDtos);
			}
		});
	}
}
