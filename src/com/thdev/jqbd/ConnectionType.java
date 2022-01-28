package com.thdev.jqbd;

import java.io.File;

public enum ConnectionType {

	MYSQL("jdbc:mysql://"),
	SQLITE("jdbc:sqlite:");

	private final String base;
	
	ConnectionType(String base) {
		this.base = base;
	}
	
	public String getBase(File sqliteFile) {
		return SQLITE.base + sqliteFile.getAbsolutePath();
	}
	
	public String getBase(String host, String databaseName) {
		return MYSQL.base + host + "/" + databaseName;
	}
}
