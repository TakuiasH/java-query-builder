package com.takuiash.jqbd.orm;

import com.takuiash.jqbd.connector.Connector.ConnectionType;
import com.takuiash.jqbd.query.Delete;
import com.takuiash.jqbd.query.Insert;
import com.takuiash.jqbd.query.Table;
import com.takuiash.jqbd.query.Update;
import com.takuiash.jqbd.query.helpers.column.COBJ;
import com.takuiash.jqbd.query.select.Select;
import com.takuiash.jqbd.worker.search.generic.SelectData;

public interface Repository<T> {

	public Table getTable();

	public int createTable(ConnectionType type);
	
	public Select<T> select();
	public Select<T> select(String... fields);
	
	public Insert insert();
	public Insert insert(COBJ... values);
	public Delete delete();
	public Update<SelectData> Update();
	public Update<SelectData> Update(COBJ... values);
	public int dropTable();
	public int truncateTable();

	public T insert(T entity);
	public T insert(T entity, boolean includePrimary);
	public int delete(T entity);
	public Update<T> update(T entity);

}