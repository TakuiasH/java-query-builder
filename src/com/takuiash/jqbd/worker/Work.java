package com.takuiash.jqbd.worker;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author THDev
 */
public interface Work {

	/**
	 * TODO Returns the count of rows affected.
	 * 
	 * @param query
	 * @param connection
	 * @return {@link Integer}
	 * @throws SQLException
	 */
	int execute(String query, Connection connection) throws SQLException;
	
}
