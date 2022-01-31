package com.takuiash.orm.table;

import java.sql.Connection;
import java.sql.SQLException;

import com.takuiash.orm.jdbc.query.Delete;
import com.takuiash.orm.jdbc.query.Drop;
import com.takuiash.orm.jdbc.query.Insert;
import com.takuiash.orm.jdbc.query.Truncate;
import com.takuiash.orm.jdbc.query.Update;
import com.takuiash.orm.jdbc.query.helpers.column.COBJ;
import com.takuiash.orm.jdbc.query.helpers.column.ColumnList;
import com.takuiash.orm.jdbc.query.select.Select;
import com.takuiash.orm.jdbc.query.select.Selector;
import com.takuiash.orm.jdbc.worker.search.generic.SelectData;
import com.takuiash.orm.jdbc.worker.ExecutorWork;
import com.takuiash.orm.jdbc.worker.search.generic.GenericSearchWorker;

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
