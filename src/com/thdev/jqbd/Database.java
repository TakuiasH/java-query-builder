package com.thdev.jqbd;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.thdev.jqbd.query.response.DatabaseResponse;
import com.thdev.jqbd.query.response.ResponseMap;
import com.thdev.jqbd.query.table.Table;

public class Database {

	private final ConnectionType connectionType;
	
	private File sqliteFile;
	
	private String host, databaseName, username, password;
	
	public Database(String host, String databaseName, String username, String password) {
		this.connectionType = ConnectionType.MYSQL;
		
		this.host = host;
		this.databaseName = databaseName;
		this.username = username;
		this.password = password;
	}
	
	public Database(String sqliteFilePath) {
		this(new File(sqliteFilePath));
	}
	
	public Database(File sqliteFile) {
		this.connectionType = ConnectionType.SQLITE;
		this.sqliteFile = sqliteFile;
		
		try { if(!sqliteFile.exists()) sqliteFile.createNewFile(); } catch (IOException e) { e.printStackTrace(); }
	}
	
	public ConnectionType getConnectionType() {
		return connectionType;
	}
	
	public Connection getConnection() {
		
		try {
			if(connectionType == ConnectionType.MYSQL)
				return DriverManager.getConnection(connectionType.getBase(this.host, this.databaseName), this.username, this.password);
			else
				return DriverManager.getConnection(connectionType.getBase(this.sqliteFile));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Table table(String name) { return new Table(this, name); }
	
	public DatabaseResponse executeQuery(String query) {
		
		List<ResponseMap> response = new ArrayList<ResponseMap>();
		
		try {
			Statement st = getConnection().createStatement();
			ResultSet rs = st.executeQuery(query);
		
			while (rs.next()) {
				ResponseMap responseMap = new ResponseMap();
				
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					responseMap.put(rs.getMetaData().getColumnLabel(i), rs.getObject(i));
				}
				
				response.add(responseMap);
			}
			
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return new DatabaseResponse(response);
	}
	
	public Integer executeUpdate(String query) {
		try {
			Statement st = getConnection().createStatement();
			int i = st.executeUpdate(query);
			st.close();
			return i;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
