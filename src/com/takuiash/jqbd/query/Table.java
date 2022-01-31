package com.takuiash.jqbd.query;

import java.sql.Connection;
import java.sql.SQLException;

import com.takuiash.jqbd.connector.Connector.ConnectionType;
import com.takuiash.jqbd.query.helpers.builders.TableCreateBuilder;
import com.takuiash.jqbd.query.helpers.column.COBJ;
import com.takuiash.jqbd.query.helpers.column.ColumnList;
import com.takuiash.jqbd.query.select.Select;
import com.takuiash.jqbd.query.select.Selector;
import com.takuiash.jqbd.worker.ExecutorWork;
import com.takuiash.jqbd.worker.search.generic.GenericSearchWorker;
import com.takuiash.jqbd.worker.search.generic.SelectData;

/**
 * @author THDev
 */
public class Table {

	private final Connection connection;
	private final String name;
	
	public Table(Connection connection, String name) {
		this.connection = connection;
		this.name = name;
	}
	
	/**
	 * TODO Get table name.
	 * 
	 * @return {@link String}
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * TODO Get the table connection.
	 * 
	 * @return {@link Connection}
	 */
	public Connection getConnection() {
		return connection;
	}
	
	/**
	 * TODO Create the table if not exists
	 * 
	 * @param type
	 * @param columns
	 * @return {@link Integer}
	 * @throws SQLException 
	 */
	public int create(ConnectionType type, ColumnList columns) {
		return new ExecutorWork().execute(build(type, columns), connection);
	}
	
	/**
	 * TODO Make select query.
	 * 
	 * @param fields
	 * @return {@link Select}
	 */
	public Select<SelectData> select(String... fields) { return select().fields(fields); }
	
	/**
	 * TODO Make select query.
	 * 
	 * @param fields
	 * @return {@link Select}
	 */
	public Select<SelectData> select() { return new Selector<>(new GenericSearchWorker(), this); }
	
	/**
	 * TODO Make insert query.
	 * 
	 * @return {@link Insert}
	 */
	public Insert insert() { return new Insert(new ExecutorWork(), this); }
	
	/**
	 * TODO Make insert query.
	 * 
	 * @param columnObjects
	 * @return {@link Insert}
	 */
	public Insert insert(COBJ... columnObjects) { return insert().value(columnObjects); }
	
	/**
	 * TODO Make delete query.
	 * 
	 * @return {@link Delete}
	 */
	public Delete delete() { return new Delete(new ExecutorWork(), this); }

	/**
	 * TODO Make update query.
	 * 
	 * @return {@link Update}
	 */
	public Update<SelectData> update() { return new Update<>(new ExecutorWork(), this, null); }
	
	/**
	 * TODO Make update query.
	 * 
	 * @param columnObjects
	 * @return {@link Update}
	 */
	public Update<SelectData> update(COBJ... columnObjects) { return update().value(columnObjects); }
	
	/**
	 * TODO Make drop table query.
	 * 
	 * @return {@link Integer}
	 * @throws SQLException
	 */
	public int drop() { return new Drop(new ExecutorWork(), this).execute(); }
	
	/**
	 * TODO Make truncate table query.
	 * 
	 * @return {@link Integer}
	 * @throws SQLException
	 */
	public int truncate() { return new Truncate(new ExecutorWork(), this).execute(); }

	/**
	 * TODO Returns the query string.
	 * 
	 * @return {@link String}
	 */
	public String build(ConnectionType type, ColumnList columns) {
		StringBuilder query = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
		query.append(this.name);
		
		query.append(TableCreateBuilder.build(type, columns));
				
		return query.toString();
	}
}
