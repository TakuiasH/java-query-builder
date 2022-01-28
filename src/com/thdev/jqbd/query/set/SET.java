package com.thdev.jqbd.query.set;

public class SET {
	private final String column;
	private final Object value;
	
	public SET(String column, Object value) {
		this.column = column;
		this.value = value;
	}
	
	public static SET as(String column, Object value) {
		return new SET(column, value);
	}
	
	public String getColumn() {
		return column;
	}
	
	public Object getValue() {
		return value;
	}
	
}