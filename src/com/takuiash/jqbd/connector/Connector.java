package com.takuiash.jqbd.connector;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.takuiash.jqbd.query.Table;

public class Connector {
	
	private final ConnectionType connectionType;
	
	private Connection connection;
	
	private File sqliteFile;
	private String host, databaseName, username, password;
	
	public Connector(String host, String databaseName, String username, String password) {
		this.connectionType = ConnectionType.MYSQL;
		
		this.host = host;
		this.databaseName = databaseName;
		this.username = username;
		this.password = password;
	}
	
	public Connector(String sqliteFilePath) {
		this(new File(sqliteFilePath));
	}
	
	public Connector(File sqliteFile) {
		this.connectionType = ConnectionType.SQLITE;
		this.sqliteFile = sqliteFile;
		
		try { if(!sqliteFile.exists()) sqliteFile.createNewFile(); } catch (IOException e) { e.printStackTrace(); }
	}
	
	public ConnectionType getConnectionType() {
		return this.connectionType;
	}
	
	public Connection getConnection() {
		try {
			if(connection == null) {
				if(connectionType == ConnectionType.MYSQL)
					connection = DriverManager.getConnection("jdbc:mysql://" + host + databaseName, username, password);
				else
					connection = DriverManager.getConnection("jdbc:sqlite:" + sqliteFile.getAbsolutePath());
			}
			return connection;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Table table(String name) {
		return new Table(getConnection(), name);
	}
	
	public enum ConnectionType {
		MYSQL, SQLITE;
	}
}
