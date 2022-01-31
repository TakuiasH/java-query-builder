package com.takuiash.jqbd.query.helpers.column;

public enum Option {
	PRIMARY(""),
	NOT_NULL(""),
	UNIQUE("");

	private final String tag;
	
	Option(String tag) {
		this.tag = tag;
	}
	
	public String getTag() {
		return this.tag;
	}
	
	
}
