package com.takuiash.jqbd.query.helpers;

public class Column {

	private final String column;
	private final Object value;
	
	public Column(String column, Object value) {
		this.column = column;
		this.value = value;
	}
	
	public static Column as(String column, Object value) {
		return new Column(column, value);
	}
	
	public String getColumn() {
		return column;
	}
	
	public Object getValue() {
		return value;
	}
	
}
