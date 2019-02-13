/**
 * UserDto.java
 * (C) 2019. Industrial Value Chain Initiative
 */
package jp.ciof_cps.hds.dto;

import java.util.EnumSet;

import jp.ciof_cps.hds.SpecConstants.HdsRoles;

/**
 * This class is the DTO class for user.
 */
public class UserDto {

	private String userName;

	private EnumSet<HdsRoles> roles;

	/**
	 * Get the userName.
	 * 
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Set the userName.
	 *
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Get the roles.
	 * 
	 * @return the roles
	 */
	public EnumSet<HdsRoles> getRoles() {
		return roles;
	}

	/**
	 * Set the roles.
	 *
	 * @param roles the roles to set
	 */
	public void setRoles(EnumSet<HdsRoles> roles) {
		this.roles = roles;
	}

}
