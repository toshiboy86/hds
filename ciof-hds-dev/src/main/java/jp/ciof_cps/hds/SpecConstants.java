/**
 * SpecConstants.java
 * (C) 2019. Industrial Value Chain Initiative
 */
package jp.ciof_cps.hds;

/**
 * This class defines the specification constants.
 */
public final class SpecConstants {

	/** A constructor. */
	private SpecConstants() {
		super();
	}

	/**
	 * This class defines the specification constants for API.
	 */
	public static final class Api {
		/** API relative path. */
		public static final String API_REL_PATH = "api/v1";

		/** A constructor. */
		private Api() {
			super();
		}
	}

	/**
	 * This class defines the specification constants for roles.
	 */
	public enum HdsRoles {
		ADMINISTRATOR,
		USER
	}
}

