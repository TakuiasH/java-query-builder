package com.thdev.jqbd.query.var;

public enum VarArgument {
	PRIMARY("NOT NULL PRIMARY KEY AUTOINCREMENT"),
	UNIQUE("NOT NULL UNIQUE"),
	NOT_NULL("NOT NULL"),
	DEFAULT("DEFAULT "),
	NONE("");

	private String name;
	
	private VarArgument(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public VarArgument add(Object argument) {
		if(argument instanceof String)
			name += "'"+argument+"'";
		else
			name += argument;

		return this;
	}
	
}
