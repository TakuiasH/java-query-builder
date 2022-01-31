package com.takuiash.orm.jdbc.query.helpers.column;

public class COBJ {

	private final String column;
	private final Object value;
	
	public COBJ(String column, Object value) {
		this.column = column;
		this.value = value;
	}
	
	public static COBJ as(String column, Object value) {
		return new COBJ(column, value);
	}
	
	public String getColumn() {
		return column;
	}
	
	public Object getValue() {
		return value;
	}
	
}
