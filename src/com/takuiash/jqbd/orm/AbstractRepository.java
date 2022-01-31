package com.takuiash.jqbd.orm;

import com.takuiash.jqbd.connector.Connector.ConnectionType;
import com.takuiash.jqbd.query.Delete;
import com.takuiash.jqbd.query.Insert;
import com.takuiash.jqbd.query.Table;
import com.takuiash.jqbd.query.Update;
import com.takuiash.jqbd.query.helpers.column.COBJ;
import com.takuiash.jqbd.query.select.Select;
import com.takuiash.jqbd.query.select.Selector;
import com.takuiash.jqbd.worker.ExecutorWork;
import com.takuiash.jqbd.worker.search.entity.EntitySearcherWorker;
import com.takuiash.jqbd.worker.search.generic.SelectData;

public abstract class AbstractRepository<T> implements Repository<T> {

	private final Table table;
	private final T entityBase;
	
	public AbstractRepository(Table table, T entityBase) {
		this.table = table;
		this.entityBase = entityBase;
	}
	
	public Table getTable() {
		return table; 
	}
	
	public int createTable(ConnectionType type) {
		return table.create(type, ((DatabaseEntity) entityBase).getColumnList());
	}
	
	public Select<T> select() {
		return new Selector<>(new EntitySearcherWorker<T>(entityBase), table, entityBase);
	}
	
	public Insert insert(){
		return table.insert();
	}

	public Insert insert(COBJ... columnObjects) {
		return table.insert(columnObjects);
	}
	
	public Delete delete() {
		return table.delete();
	}
	
	public Update<SelectData> Update() {
		return table.update();
	}
	
	public Update<SelectData> Update(COBJ... values) {
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
		COBJ[] columnObjects = EntityConverter.getColumnObjects((DatabaseEntity) entity, includePrimary);
				
		int rowsCount = new Insert(new ExecutorWork(), table).value(columnObjects).execute();
		
		if(rowsCount == 0)
			return null;
		else
			return select().where(columnObjects).first();
	}
	
	public int delete(T entity) {
		COBJ[] columnObjects = EntityConverter.getColumnObjects((DatabaseEntity) entity, false);

		return table.delete().where(columnObjects).execute();
	}
	
	public Update<T> update(T entity) {
		COBJ[] columnObjects = EntityConverter.getColumnObjects((DatabaseEntity) entity, false);

		return new Update<>(new ExecutorWork(), table, this).value(columnObjects);
	}

}
