/**
 * CustomSqlDialect.java
 * (C) 2019. Industrial Value Chain Initiative
 */
package jp.ciof_cps.hds.entity;

import java.sql.Types;

import org.hibernate.dialect.PostgreSQL94Dialect;

/**
 * An SQL dialect for additional functions.
 */
public class CustomSqlDialect extends PostgreSQL94Dialect {

	/**
	 * The constructor.
	 */
	public CustomSqlDialect() {
		this.registerColumnType(Types.JAVA_OBJECT, "jsonb");
	}
}
