package com.takuiash.orm.jdbc.query;

import java.sql.SQLException;

import com.takuiash.orm.jdbc.worker.Work;
import com.takuiash.orm.table.Table;

public class Truncate {

	protected final Table table;
	protected final Work worker;
		
	/**
	 * @param worker
	 * @param table
	 */
	public Truncate(Work worker, Table table) {
		this.table = table;
		this.worker = worker;
	}
	
	/**
	 * TODO execute the query and returns the count of rows affected
	 * 
	 * @return {@link Integer}
	 * @throws SQLException
	 */
	public int execute() throws SQLException { return worker.execute(build(), table.getConnection()); }
	
	/**
	 * TODO Returns the query string.
	 * 
	 * @return {@link String}
	 */
	public <T> String build() {
		StringBuilder builder = new StringBuilder();
		builder.append("TRUNCATE TABLE ");
		builder.append(table.getName());
		builder.append(";");

		System.out.println(builder.toString());
		
		return builder.toString();
	}
}
