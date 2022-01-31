package com.takuiash.jqbd.worker;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ExecutorWork implements Work {

	public Integer execute(String query, Connection connection){
		try {
			Statement st = connection.createStatement();
			int i = st.executeUpdate(query);
			st.close();		
			return i;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


}
