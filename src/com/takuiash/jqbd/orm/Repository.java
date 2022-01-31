package com.takuiash.jqbd.orm;

import java.sql.SQLException;

import com.takuiash.jqbd.query.Delete;
import com.takuiash.jqbd.query.Insert;
import com.takuiash.jqbd.query.Table;
import com.takuiash.jqbd.query.Update;
import com.takuiash.jqbd.query.helpers.column.COBJ;
import com.takuiash.jqbd.query.select.Select;
import com.takuiash.jqbd.worker.search.generic.SelectData;

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