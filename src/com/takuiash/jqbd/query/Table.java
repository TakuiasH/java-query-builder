package com.takuiash.jqbd.query;

import java.sql.Connection;
import java.sql.SQLException;

import com.takuiash.jqbd.query.helpers.column.COBJ;
import com.takuiash.jqbd.query.helpers.column.ColumnList;
import com.takuiash.jqbd.query.select.Select;
import com.takuiash.jqbd.query.select.Selector;
import com.takuiash.jqbd.worker.ExecutorWork;
import com.takuiash.jqbd.worker.search.generic.GenericSearchWorker;
import com.takuiash.jqbd.worker.search.generic.SelectData;

public class Table {

	private final Connection connection;
	private final String name;
	
	public Table(Connection connection, String name) {
		this.connection = connection;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public Connection getConnection() {
		return connection;
	}
	
	public int create(ColumnList columnList) {
		return 0;
	}
	
	public Select<SelectData> select(String... fields) { return select().fields(fields); }
	public Select<SelectData> select() { return new Selector<>(new GenericSearchWorker(), this); }
	
	public Insert insert() { return new Insert(new ExecutorWork(), this); }
	public Insert insert(COBJ... columnObjects) { return insert().value(columnObjects); }
	
	public Delete delete() { return new Delete(new ExecutorWork(), this); }

	public Update<SelectData> update() { return new Update<>(new ExecutorWork(), this, null); }
	public Update<SelectData> update(COBJ... columnObjects) { return update().value(columnObjects); }
	
	public int drop() throws SQLException { return new Drop(new ExecutorWork(), this).execute(); }
	public int truncate() throws SQLException { return new Truncate(new ExecutorWork(), this).execute(); }

}
