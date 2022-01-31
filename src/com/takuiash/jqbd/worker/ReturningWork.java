package com.takuiash.jqbd.worker;

import java.sql.Connection;
import java.sql.SQLException;

import com.takuiash.jqbd.worker.search.SearchResponse;

public interface ReturningWork<T> {
	SearchResponse<T> execute(String query, Connection connection) throws SQLException;
}
