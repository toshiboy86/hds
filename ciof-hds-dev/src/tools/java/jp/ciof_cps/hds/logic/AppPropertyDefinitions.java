/**
 * AppPropertyDefinitions.java
 * (C) 2019. Industrial Value Chain Initiative
 */
package jp.ciof_cps.hds.logic;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class is the definition class of application property default values.
 */
class AppPropertyDefinitions implements AppPropertyConstants {
	private static final Log LOG = LogFactory.getLog(AppPropertyDefinitions.class);

	private static final String DEFAULT_PROPERTY_FILEDIR = "src/main/resources/jp/ciof_cps/hds/logic";

	private static final String DEFAULT_PROPERTY_FILENAME = DEFAULT_FILENAME;

	private AppPropertyDefinitions() {
		super();
	}

	/**
	 * Execute default property file.
	 * @param args the arguments.
	 */
	public static void main(String[] args) {
		try {
			File propsFile = new File(DEFAULT_PROPERTY_FILEDIR, DEFAULT_PROPERTY_FILENAME);
			Map<String, String> props = new LinkedHashMap<String, String>();
			defineProps(props);
			AppPropertyProvider.storeProps(AppPropertyDefinitions.class, propsFile, props);
		} catch (IOException e) {
			LOG.error("Failed to store properties.", e);
		}
	}

	/**
	 * Define property default values.
	 * @param props the properties
	 */
	static void defineProps(Map<String, String> props) {
		/*
		 * general settings
		 */
		// Module specific value
		props.put(PROP_KEY_MODULE_SPECIFIC_VALUE, "4000");
	}
}
