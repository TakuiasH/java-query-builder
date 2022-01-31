package com.takuiash.jqbd.query;

import java.sql.SQLException;

import com.takuiash.jqbd.worker.Work;

/**
 * @author THDev
 */
public class Drop {

	protected final Table table;
	protected final Work worker;
		
	/**
	 * @param worker
	 * @param table
	 */
	public Drop(Work worker, Table table) {
		this.table = table;
		this.worker = worker;
	}
	
	/**
	 * TODO execute the query and returns the count of rows affected
	 * 
	 * @return {@link Integer}
	 * @throws SQLException
	 */
	public int execute() { return worker.execute(build(), table.getConnection()); }
	
	/**
	 * TODO Returns the query string.
	 * 
	 * @return {@link String}
	 */
	public <T> String build() {
		StringBuilder builder = new StringBuilder();
		builder.append("DROP TABLE ");
		builder.append(table.getName());
		builder.append(";");
		
		return builder.toString();
	}
}
