package com.takuiash.orm.jdbc.worker;

import java.sql.Connection;
import java.sql.SQLException;

import com.takuiash.orm.jdbc.worker.search.SearchResponse;

public interface ReturningWork<T> {
	SearchResponse<T> execute(String query, Connection connection) throws SQLException;
}
