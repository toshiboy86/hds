/**
 * IdGenerator.java
 * (C) 2019. Industrial Value Chain Initiative
 */
package jp.ciof_cps.hds.logic;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class is the ID-generator class for string-type primary keys for Entities.
 */
@ApplicationScoped
public class IdGenerator implements AppPropertyConstants {
	
	static final Log LOG = LogFactory.getLog(IdGenerator.class);
	
	private static final int TIME_LOW_IDX = 0;
	private static final int TIME_MID_IDX = 1;
	private static final int CLK_SEQ_IDX = 3;
	private static final int NODE_IDX = 4;
	
	@Inject
	AppPropertyProvider appPropertyProvider;
	
	/**
	 * Generate ID string.
	 * @param clazz user class
	 * @return ID string.
	 */
	public String generateId(Class<?> clazz) {
		Class<?> nameClass = clazz;
		if (nameClass == null) {
			nameClass = Void.class;
		}
		
		String sName = nameClass.getCanonicalName();
		byte[] name = sName.getBytes(StandardCharsets.UTF_8);
		UUID uuidV3 = UUID.nameUUIDFromBytes(name);
		UUID uuidV4 = UUID.randomUUID();
		
		
		String moduleSpecificValue = appPropertyProvider.getProperty(PROP_KEY_MODULE_SPECIFIC_VALUE);
		String[] uuidV3Parts = uuidV3.toString().split("-");
		String[] uuidV4Parts = uuidV4.toString().split("-");
		
		String id = (moduleSpecificValue + uuidV3Parts[TIME_MID_IDX] + uuidV3Parts[CLK_SEQ_IDX] + uuidV4Parts[TIME_LOW_IDX] + uuidV4Parts[NODE_IDX]);
		
		LOG.info(String.format("Generating id ...: (sName, uuidV3, uuidV4, id)=(%s, %s, %s, %s)", sName, uuidV3, uuidV4, id));
		return id;
	}
}
