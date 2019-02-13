/**
 * AppPropertyConstants.java
 * (C) 2019. Industrial Value Chain Initiative
 */
package jp.ciof_cps.hds.logic;

/**
 * This is the constant-definition class for Application Properties.
 *
 */
public interface AppPropertyConstants {

	/*---------------------------------------------------------------
	 * Files.
	 */

	/**
	 * Default property filename.
	 */
	String DEFAULT_FILENAME = "default.ciof-hds.properties";

	/**
	 * Custom property filepath.
	 */
	String CUSTOM_FILEPATH = "/etc/ciof/ciof-hds.properties";

	/*---------------------------------------------------------------
	 * Prefixes.
	 */

	/**
	 * Prefix of property key.
	 */
	String PREFIX_PROP_KEY = "ciof-hds.props.";

	/*---------------------------------------------------------------
	 * Property keys.
	 */

	/*
	 * General settings
	 */
	
	/** Property key for module specific value. */
	String PROP_KEY_MODULE_SPECIFIC_VALUE = PREFIX_PROP_KEY + "moduleSpecificValue";
}
