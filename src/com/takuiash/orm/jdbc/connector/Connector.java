package com.takuiash.orm.jdbc.connector;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.takuiash.orm.table.Table;

public class Connector {
	
	private Connection connection;

	public Connector() {}
	
	public Connection getConnection() throws SQLException {
		if(connection == null)
			connection = DriverManager.getConnection("jdbc:sqlite:" + new File("test.db").getAbsolutePath());
		
		return connection;
	}
	
	public Table table(String name) throws SQLException {
		return new Table(getConnection(), name);
	}
}