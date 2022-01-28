package com.thdev.jqbd.query.functions;

import com.thdev.jqbd.Database;
import com.thdev.jqbd.query.base.BaseQuery;
import com.thdev.jqbd.query.table.Table;

public class Truncate extends BaseQuery {

	public Truncate(Database database) { super(database); }
	public Truncate(Database database, Table table) { super(database, table); }
	
	public void execute() {
		getDatabase().executeUpdate(build());
	}
	
	public String build() {
		return "TRUNCATE TABLE " + getTable().getName() + ";";
	}
}
