package com.takuiash.orm.repository;

import java.sql.SQLException;

import com.takuiash.orm.entity.DatabaseEntity;
import com.takuiash.orm.entity.EntityConverter;
import com.takuiash.orm.jdbc.query.Delete;
import com.takuiash.orm.jdbc.query.Insert;
import com.takuiash.orm.jdbc.query.Update;
import com.takuiash.orm.jdbc.query.helpers.column.COBJ;
import com.takuiash.orm.jdbc.query.select.Select;
import com.takuiash.orm.jdbc.query.select.Selector;
import com.takuiash.orm.jdbc.worker.ExecutorWork;
import com.takuiash.orm.jdbc.worker.search.entity.EntitySearcherWorker;
import com.takuiash.orm.jdbc.worker.search.generic.SelectData;
import com.takuiash.orm.table.Table;

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
	
	public int createTable() {
		return table.create(((DatabaseEntity) entityBase).getColumnList());
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
	
	public int dropTable() throws SQLException {
		return table.drop();
	}
	
	public int truncateTable() throws SQLException {
		return table.truncate();
	}
	
	public T insert(T entity) throws SQLException {
		return insert(entity, false);
	}
	
	public T insert(T entity, boolean includePrimary) throws SQLException {
		COBJ[] columnObjects = EntityConverter.getColumnObjects((DatabaseEntity) entity, includePrimary);
				
		int rowsCount = new Insert(new ExecutorWork(), table).value(columnObjects).execute();
		
		if(rowsCount == 0)
			return null;
		else
			return select().where(columnObjects).first();
	}
	
	public int delete(T entity) throws SQLException {
		COBJ[] columnObjects = EntityConverter.getColumnObjects((DatabaseEntity) entity, false);

		return table.delete().where(columnObjects).execute();
	}
	
	public Update<T> update(T entity) {
		COBJ[] columnObjects = EntityConverter.getColumnObjects((DatabaseEntity) entity, false);

		return new Update<>(new ExecutorWork(), table, this).value(columnObjects);
	}

}
