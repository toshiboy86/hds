/**
 * Serdes.java
 * (C) 2019. Industrial Value Chain Initiative
 */
package jp.ciof_cps.hds.logic;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This is the serializer/deserializer class.
 */
public class Serdes {
	
	static final Log LOG = LogFactory.getLog(Serdes.class);
	
	static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	
	/**
	 * Retrieve ObjectMapper instance.
	 * @return the instance
	 */
	public static ObjectMapper getObjectMapper() {
		return OBJECT_MAPPER;
	}
	
	/**
	 * Serialize an instance as JSON string to stream.
	 * @param <BEAN> the type parameter of Java Beans
	 * @param bean the instance
	 * @param outputStream the output stream
	 * @throws IOException the I/O error
	 */
	public <BEAN> void serializeToJson(BEAN bean, OutputStream outputStream) throws IOException {
		OBJECT_MAPPER.writeValue(outputStream, bean);
	}
	
	/**
	 * Deserialize an instance from stream of JSON string 
	 * @param <BEAN> the type parameter of Java Beans
	 * @param inputStream the input stream
	 * @param beanClass the type of the instance
	 * @return the instance
	 * @throws IOException the I/O error
	 */
	public <BEAN> BEAN deserializeFromJson(InputStream inputStream, Class<? extends BEAN> beanClass) throws IOException {
		return OBJECT_MAPPER.readValue(inputStream, beanClass);
	}
}
