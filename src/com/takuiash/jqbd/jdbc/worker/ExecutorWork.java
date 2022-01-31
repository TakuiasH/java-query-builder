package com.takuiash.jqbd.jdbc.worker;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ExecutorWork implements Work {

	public int execute(String query, Connection connection) throws SQLException {
		Statement st = connection.createStatement();
		int i = st.executeUpdate(query);
		st.close();		
		return i;
	}


}
