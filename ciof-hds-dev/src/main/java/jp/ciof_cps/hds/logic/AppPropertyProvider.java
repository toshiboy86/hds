/**
 * AppPropertyProvider.java
 * (C) 2019. Industrial Value Chain Initiative
 */
package jp.ciof_cps.hds.logic;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This is the class which provides the Properties of this Application.
 */
public class AppPropertyProvider {

	private static final Log LOG = LogFactory.getLog(AppPropertyProvider.class);

	private final URL defaultPropsFileUrl;

	private String customPropsFilePath;

	private Map<String, String> configMap = null;

	private final Object oMutex = new Object();

	/**
	 * Creates an instance with specified arguments.
	 * @param defaultPropsFileName the Default Property File Name.
	 * @param customPropsFilePath the Property File Path, which overrides Default Property Values.
	 */
	public AppPropertyProvider(String defaultPropsFileName, String customPropsFilePath) {
		this.defaultPropsFileUrl = this.getClass().getResource(defaultPropsFileName);
		this.customPropsFilePath = customPropsFilePath;
		configMap = null;
	}

	/**
	 * Obtains the property map, which is loaded from property files.
	 * @return the property map.
	 */
	public Map<String, String> getProperties() {
		synchronized (oMutex) {
			if (configMap == null) {
				configMap = Collections.unmodifiableMap(loadConfigMap(customPropsFilePath));
			}
			return configMap;
		}
	}

	/**
	 * Obtains the property value, which is loaded from property files.
	 * @param key the Property Key.
	 * @return the property value.
	 */
	public String getProperty(String key) {
		synchronized (oMutex) {
			if (configMap == null) {
				configMap = Collections.unmodifiableMap(loadConfigMap(customPropsFilePath));
			}
			return configMap.get(key);
		}
	}

	/**
	 * Obtains the property value as boolean type.
	 * If "true" is set in property files, this returns true.
	 * Otherwise, returns false.
	 * @param key the Property Key.
	 * @return the property value.
	 */
	public Boolean getBooleanProperty(String key) {
		String prop = getProperty(key);
		return "true".equalsIgnoreCase(prop);
	}

	/**
	 * Obtains the property value as integer type.
	 * If the property does not exist or the property value cannot be paused as integer,
	 * this returns null.
	 * @param key the Property Key.
	 * @return the property value.
	 */
	public Integer getIntegerProperty(String key) {
		String prop = getProperty(key);
		Integer nProp = null;
		try {
			nProp = Integer.parseInt(prop);
		} catch(NumberFormatException e) {
			LOG.warn("Failed to parse Integer.: key=" + key);
		}
		return nProp;
	}

	/**
	 * Obtains the comma-separated property value as string array.
	 * @param key the Property Key.
	 * @return the property value.
	 */
	public String[] getCommaSeparatedProperty(String key) {
		String[] vals = null;
		String prop = getProperty(key);
		if (prop != null) {
			vals = prop.split(",");
		}
		return vals;
	}

	/**
	 * Obtains the property values as property map with specified key prefix string.
	 * The keys of the property map are strings which are rest parts of the specified key prefix.
	 * The property value with perfect-matching key to the key prefix is not included in the propery map.
	 * @param keyPrefix the Key Prefix.
	 * @return the property map.
	 */
	public Map<String, String> getSubProperties(String keyPrefix) {
		Map<String, String> subPropMap = new HashMap<String, String>();
		Map<String, String> propMap = getProperties();
		for (Map.Entry<String, String> entry : propMap.entrySet()) {
			if (entry.getKey().startsWith(keyPrefix) && !entry.getKey().equals(keyPrefix)) {
				String newKey = entry.getKey().substring(keyPrefix.length());
				subPropMap.put(newKey, entry.getValue());
			}
		}
		return subPropMap;
	}

	private Map<String, String> loadConfigMap(String propFilePath) {
		Map<String, String> configs = new HashMap<String, String>();

		try {
			loadConfigMapFrom(configs, defaultPropsFileUrl);
		} catch (IOException e) {
			LOG.fatal("Default properties file can not be loaded.", e);
		}

		if (propFilePath != null) {
			File propFile = new File(propFilePath);
			if (propFile.exists() && propFile.isFile()) {
				try {
					loadConfigMapFrom(configs, propFilePath);
				} catch (IOException e) {
					LOG.warn("Properties file can not be loaded. : " + propFilePath, e);
				}
			} else {
				LOG.info("Properties file is not specfied. : " + propFilePath);
			}
		} else {
			LOG.warn("Properties file is null.");
		}

		return configs;
	}

	/**
	 */
	private static void loadConfigMapFrom(Map<String, String> configMap, String filePath)
			throws IOException {
		File defaultFile = new File(filePath);
		InputStream istream = null;
		try {
			LOG.info("Loading " + filePath);
			istream = new FileInputStream(defaultFile);
			loadConfigMapFrom(configMap, istream);
		} catch (IOException e) {
			LOG.warn("Failed to load " + defaultFile.getAbsolutePath());
			throw e;
		} finally {
			if (istream != null) {
				istream.close();
				istream = null;
			}
		}
	}

	/**
	 */
	private static void loadConfigMapFrom(Map<String, String> configMap, URL url) throws IOException {
		InputStream istream = null;
		try {
			LOG.info("Loading " + url);
			istream = url.openStream();
			loadConfigMapFrom(configMap, istream);
		} catch (IOException e) {
			LOG.warn("Failed to load " + url);
			throw e;
		} finally {
			if (istream != null) {
				istream.close();
				istream = null;
			}
		}
	}

	/**
	 */
	private static void loadConfigMapFrom(Map<String, String> configMap, InputStream istream) throws IOException {
		Properties props = new Properties();
		props.load(istream);
		for (Object key : props.keySet()) {
			String sKey = (String) key;
			configMap.put(sKey, props.getProperty(sKey));
		}
	}

	/**
	 *
	 * @param generatorClass generatorClass
	 * @param propsFile propsFile
	 * @param props props
	 * @throws IOException 入出力エラー
	 */
	public static void storeProps(Class<?> generatorClass, File propsFile, Map<String, String> props) throws IOException {
		OutputStreamWriter oswriter = null;
		FileOutputStream fostream = null;
		BufferedWriter bwriter = null;

		String[] fileHeaderLines = new String[] {
				"#####################################################################",
				"#       DON'T CHANGE THIS FILE DIRECTLY.",
				"# This file is automatically generated.",
				"# If you want to change this file, ",
				"# modify the generator-class file, and then generate this file.",
				"# ",
				"# This file includes properties for:",
				"# Connected Industries Open Framework - Hyper Dictionary Server (CIOF-HDS)",
				"# ",
				"# generator-class: ",
				"#  "+ generatorClass.getName(),
				"#####################################################################",
				"",
		};

		try {
			LOG.info("Storing props to " + propsFile.getAbsolutePath());
			fostream = new FileOutputStream(propsFile);
			oswriter = new OutputStreamWriter(fostream, "ISO-8859-1");
			bwriter = new BufferedWriter(oswriter);
			String value = null;
			for (String fileHeaderLine : fileHeaderLines) {
				bwriter.write(fileHeaderLine);
				bwriter.newLine();
			}
			for (Entry<String, String> key : props.entrySet()) {
				value = key.getValue();
				if (value != null) {
					bwriter.write(String.format("%s=%s", key.getKey(), value));
					bwriter.newLine();
				}
			}
			bwriter.flush();
			LOG.info("Stored.");
		} finally {
			if (bwriter != null) {
				bwriter.close();
				bwriter = null;
			}
			if (oswriter != null) {
				oswriter.close();
				oswriter = null;
			}
			if (fostream != null) {
				fostream.close();
				fostream = null;
			}
		}
	}
}
