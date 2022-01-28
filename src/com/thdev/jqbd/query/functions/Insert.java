package com.thdev.jqbd.query.functions;

import com.thdev.jqbd.Database;
import com.thdev.jqbd.query.base.BaseQuery;
import com.thdev.jqbd.query.builders.SetBuilder;
import com.thdev.jqbd.query.set.SET;
import com.thdev.jqbd.query.set.SetMap;
import com.thdev.jqbd.query.table.Table;

public class Insert extends BaseQuery {

	public Insert(Database database) { super(database); }
	public Insert(Database database, Table table) { super(database, table); }

	private SetBuilder setBuilder = new SetBuilder();
	
	public Insert set(String column, Object value) { return set(SET.as(column, value)); }
	public Insert set(SET... sets) { return set(SetMap.convert(sets)); }
	public Insert set(SetMap sets) { setBuilder.set(sets); return this; }
	
	public void execute() {
		getDatabase().executeUpdate(build());
	}
	
	public String build() {
		String result = "INSERT INTO " + getTable().getName(); 
		
		result += setBuilder.buildInsert();
		
		result += ";";
		
		return result;
	}
}