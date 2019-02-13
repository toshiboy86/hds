/**
 * CustomJsonProvider.java
 * (C) 2019. Industrial Value Chain Initiative
 */
package jp.ciof_cps.hds.api;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

import jp.ciof_cps.hds.logic.Serdes;

/**
 * This class is the provider class for JSON content type.
 */
@Provider
@Produces({MediaType.APPLICATION_JSON})
public class CustomJsonProvider extends JacksonJaxbJsonProvider {
	private static ObjectMapper commonMapper = Serdes.getObjectMapper();

	/** A constructor. */
	public CustomJsonProvider() {
		super.setMapper(commonMapper);
	}
}
