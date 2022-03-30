package com.takuiash.jqbd.query;

import java.sql.SQLException;

import com.takuiash.jqbd.query.helpers.Column;
import com.takuiash.jqbd.query.helpers.builders.InsertValueBuilder;
import com.takuiash.jqbd.query.helpers.map.maps.ColumnMap;
import com.takuiash.jqbd.worker.Work;
import com.takuiash.jqbd.worker.search.generic.SelectData;

/**
 * @author THDev
 */
public class Insert {

	protected final Table table;
	protected final Work worker;
	
	public ColumnMap insertions = new ColumnMap();
	
	/**
	 * @param worker
	 * @param table
	 */
	public Insert(Work worker, Table table) {
		this.table = table;
		this.worker = worker;
	}
	
	/**
	 * TODO Add arguments to insertion.
	 * 
	 * @param column
	 * @param value
	 * @return {@link Insert}
	 */
	public Insert value(String column, Object value) { insertions.put(column, value); return this; }
	
	/**
	 * TODO Add arguments to insertion.
	 * 
	 * @param columnObjects
	 * @return {@link Insert}
	 */
	public Insert value(Column... columnObjects) { insertions.put(columnObjects); return this; }
	
	/**
	 * TODO execute the query and returns the count of rows affected
	 * 
	 * @return {@link Integer}
	 * @throws SQLException
	 */
	public int execute() { return worker.execute(build(), table.getConnection()); }
	
	/**
	 * TODO execute the query and return the inserted value with all arguments.
	 * 
	 * @return {@link SelectData}
	 * @throws SQLException
	 */
	public SelectData executeAndSelect() {
		int rowsCount = execute();
		
		if(rowsCount == 0)
			return null;
		
		return table.select().where(insertions.toColumnObjects()).first();
	}
	
	/**
	 * TODO Returns the query string.
	 * 
	 * @return {@link String}
	 */
	public String build() {
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO ");
		builder.append(table.getName());
		builder.append(InsertValueBuilder.build(insertions));
		builder.append(";");
		
		return builder.toString();
	}
}
