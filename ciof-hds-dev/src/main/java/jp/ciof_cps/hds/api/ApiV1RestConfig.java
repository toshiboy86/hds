/**
 * ApiV1RestConfig.java
 * (C) 2019. Industrial Value Chain Initiative
 */
package jp.ciof_cps.hds.api;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import jp.ciof_cps.hds.SpecConstants;

/**
 *  This class is the rest entry point configuration class for /api/v1.
 */
@ApplicationPath(SpecConstants.Api.API_REL_PATH)
public class ApiV1RestConfig extends Application {
	
}
