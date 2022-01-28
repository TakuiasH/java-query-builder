package com.thdev.jqbd.query.functions;

import com.thdev.jqbd.Database;
import com.thdev.jqbd.query.base.BaseQuery;
import com.thdev.jqbd.query.table.Table;

public class Drop extends BaseQuery {

	public Drop(Database database) { super(database); }
	public Drop(Database database, Table table) { super(database, table); }
	
	public void execute() {
		getDatabase().executeUpdate(build());
	}
	
	public String build() {
		return "DROP TABLE " + getTable().getName() + ";";
	}
}
