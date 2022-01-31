package com.takuiash.orm.repository;

import java.sql.SQLException;

import com.takuiash.orm.jdbc.query.Delete;
import com.takuiash.orm.jdbc.query.Insert;
import com.takuiash.orm.jdbc.query.Update;
import com.takuiash.orm.jdbc.query.helpers.column.COBJ;
import com.takuiash.orm.jdbc.query.select.Select;
import com.takuiash.orm.jdbc.worker.search.generic.SelectData;
import com.takuiash.orm.table.Table;

public interface Repository<T> {

	public Table getTable();

	public int createTable();
	
	public Select<T> select();
	
	public Insert insert();
	public Insert insert(COBJ... values);
	public Delete delete();
	public Update<SelectData> Update();
	public Update<SelectData> Update(COBJ... values);
	public int dropTable() throws SQLException;
	public int truncateTable() throws SQLException;

	public T insert(T entity) throws SQLException;
	public T insert(T entity, boolean includePrimary) throws SQLException;
	public int delete(T entity) throws SQLException;
	public Update<T> update(T entity);

}