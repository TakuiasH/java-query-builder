package com.takuiash.jqbd.orm;

import com.takuiash.jqbd.connector.Connector;
import com.takuiash.jqbd.connector.Connector.ConnectionType;
import com.takuiash.jqbd.query.Delete;
import com.takuiash.jqbd.query.Insert;
import com.takuiash.jqbd.query.Select;
import com.takuiash.jqbd.query.Table;
import com.takuiash.jqbd.query.Update;
import com.takuiash.jqbd.query.helpers.Column;
import com.takuiash.jqbd.worker.ExecutorWork;
import com.takuiash.jqbd.worker.search.entity.EntitySearcherWorker;
import com.takuiash.jqbd.worker.search.generic.SelectData;

public class Repository<T> {

	private final Table table;
	private final T entityBase;
	
	public Repository(T entityBase, Connector connector) {		
		this.entityBase = entityBase;
		this.table = new Table(connector.getConnection(), ((Entity) entityBase).getSetup().getTableName());
		
		table.create(connector.getConnectionType(), ((Entity) entityBase).getSetup());
	}
	
	public Table getTable() {
		return table; 
	}
	
	public int createTable(ConnectionType type) {
		return table.create(type, ((Entity) entityBase).getSetup());
	}
	
	public Select<T> select() {
		return new Select<>(new EntitySearcherWorker<T>(entityBase), table, entityBase);
	}
	
	public Select<T> select(String... fields) {
		return select().fields(fields);
	}
	
	public Insert insert(){
		return table.insert();
	}

	public Insert insert(Column... columnObjects) {
		return table.insert(columnObjects);
	}
	
	public Delete delete() {
		return table.delete();
	}
	
	public Update<SelectData> Update() {
		return table.update();
	}
	
	public Update<SelectData> Update(Column... values) {
		return table.update(values);
	}
	
	public int dropTable() {
		return table.drop();
	}
	
	public int truncateTable() {
		return table.truncate();
	}
	
	public T insert(T entity) {
		return insert(entity, false);
	}
	
	public T insert(T entity, boolean includePrimary) {
		Column[] columnObjects = EntityConverter.getColumnObjects((Entity) entity, includePrimary);
				
		int rowsCount = new Insert(new ExecutorWork(), table).value(columnObjects).execute();
		
		if(rowsCount == 0)
			return null;
		else
			return select().where(columnObjects).first();
	}
	
	public int delete(T entity) {
		Column[] columnObjects = EntityConverter.getColumnObjects((Entity) entity, false);

		return table.delete().where(columnObjects).execute();
	}
	
	public Update<T> update(T entity) {
		Column[] columnObjects = EntityConverter.getColumnObjects((Entity) entity, false);

		return new Update<>(new ExecutorWork(), table, this).value(columnObjects);
	}

}
