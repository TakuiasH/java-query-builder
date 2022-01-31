package com.takuiash.jqbd.worker;

import java.sql.Connection;
import java.sql.SQLException;

import com.takuiash.jqbd.worker.search.SearchResponse;

/**
 * @author THDev
 */
public interface ReturningWork<T> {
	
	/**
	 * TODO Returns the request response with defined type.
	 * 
	 * @param query
	 * @param connection
	 * @return {@link SearchResponse}
	 * @throws SQLException
	 */
	SearchResponse<T> execute(String query, Connection connection);
}
