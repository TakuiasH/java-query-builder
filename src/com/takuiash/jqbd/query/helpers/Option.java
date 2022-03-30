package com.takuiash.jqbd.query.helpers;

public enum Option {
	PRIMARY("PRIMARY KEY"),
	AUTO_INCREMENT("AUTOINCREMENT"),
	NOT_NULL("NOT NULL"),
	UNIQUE("UNIQUE");

	private final String tag;
	
	Option(String tag) {
		this.tag = tag;
	}
	
	public String getTag() {
		return this.tag;
	}
	
	
}
