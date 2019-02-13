/**
 * Resources.java
 * (C) 2019. Industrial Value Chain Initiative
 */
package jp.ciof_cps.hds;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jp.ciof_cps.hds.logic.AppPropertyConstants;
import jp.ciof_cps.hds.logic.AppPropertyProvider;

/**
 * This is the CDI-resources class.
 */
@ApplicationScoped
public class Resources {
	private static final Log LOG = LogFactory.getLog(Resources.class);

	private static final String DEFAULT_PROPS_FILENAME = AppPropertyConstants.DEFAULT_FILENAME;
	private static final String CUSTOM_PROPS_FILEPATH = AppPropertyConstants.CUSTOM_FILEPATH;

	@PersistenceContext(unitName = "ciofhdsPersistenceUnit")
	@Produces
	private EntityManager em;
	
	@Produces
	private AppPropertyProvider appPropertyProvider;

	/**
	 * Initialize objects.
	 */
	@PostConstruct
	public void init() {
		LOG.info("called init()");
		
		if (appPropertyProvider == null) {
			appPropertyProvider = new AppPropertyProvider(DEFAULT_PROPS_FILENAME, CUSTOM_PROPS_FILEPATH);
		}
	}
}

