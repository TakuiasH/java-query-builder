package com.thdev.jqbd.query.var;

import com.thdev.jqbd.ConnectionType;

public enum VarType {

	DOUBLE("DOUBLE", "DECIMAL"),
	STRING("VARCHAR", "VARCHAR"),
	TEXT("TEXT", "TEXT"),
	INTEGER("INT", "INTEGER");

	private final String mysqlContext, sqliteContext;
	
	VarType(String mysql, String sqlite) {
		this.mysqlContext = mysql;
		this.sqliteContext = sqlite;
	}
	
	public String getMysqlContext() {
		return mysqlContext;
	}
	
	public String getSqliteContext() {
		return sqliteContext;
	}
	
	public String getContextByType(ConnectionType type) {
		if(type == ConnectionType.MYSQL)
			return getMysqlContext();
		else
			return getSqliteContext();
	}
	
}
