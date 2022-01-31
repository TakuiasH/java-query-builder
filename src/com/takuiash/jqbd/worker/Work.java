package com.takuiash.jqbd.worker;

import java.sql.Connection;
import java.sql.SQLException;

public interface Work {

	int execute(String query, Connection connection) throws SQLException;
	
}
