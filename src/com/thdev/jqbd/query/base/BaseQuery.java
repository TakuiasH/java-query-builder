package com.thdev.jqbd.query.base;

import com.thdev.jqbd.Database;
import com.thdev.jqbd.query.table.Table;

public class BaseQuery implements Base {

	private final Database database;
	private Table table;
		
	public BaseQuery(Database database) {
		this.database = database;
	}
	
	public BaseQuery(Database database, Table table) {
		this.database = database;
		this.table = table;
	}
	
	public Base table(String name) {
		this.table = new Table(database, name);
		return this;
	}
	
	public Table getTable() {
		return table;
	}
	
	public Database getDatabase() {
		return database;
	}
	
}
