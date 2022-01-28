package com.thdev.jqbd.query.condition;

public class CObj {

	private final String column;
	private final Object value;
	
	public CObj(String column, Object value) {
		this.column = column;
		this.value = value;
	}
	
	public static CObj as(String column, Object value) {
		return new CObj(column, value);
	}
	
	public String getColumn() {
		return column;
	}
	
	public Object getValue() {
		return value;
	}
	
}
