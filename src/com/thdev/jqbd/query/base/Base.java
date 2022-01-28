package com.thdev.jqbd.query.base;

import com.thdev.jqbd.Database;
import com.thdev.jqbd.query.table.Table;

public interface Base {
	
	public Base table(String name);
	public Table getTable();

	public Database getDatabase();

}
