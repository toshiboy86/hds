/**
 * AuthUtil.java
 * (C) 2019. Industrial Value Chain Initiative
 */
package jp.ciof_cps.hds.logic;

import java.util.EnumSet;

import javax.ws.rs.core.SecurityContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jp.ciof_cps.hds.SpecConstants.HdsRoles;
import jp.ciof_cps.hds.dto.UserDto;

/**
 * This class is the utility class for User Information.
 */
public final class AuthUtil {

	static final Log LOG = LogFactory.getLog(AuthUtil.class);

	/** A constructor. */
	private AuthUtil() {
		super();
	}

	/**
	 * Extract user information from SecurityContext.
	 * 
	 * @param securityContext the Security Context to get User Information
	 * @return UserDto that includes user information
	 */
	public static UserDto extractUserDto(SecurityContext securityContext) {
		UserDto userDto = new UserDto();
		String userName = securityContext.getUserPrincipal().getName();
		EnumSet<HdsRoles> roles = EnumSet.noneOf(HdsRoles.class);
		for (HdsRoles role: HdsRoles.values()) {
			if (securityContext.isUserInRole(role.name())) {
				roles.add(role);
			}
		}
		userDto.setUserName(userName);
		userDto.setRoles(roles);
		return userDto;
	}
}
