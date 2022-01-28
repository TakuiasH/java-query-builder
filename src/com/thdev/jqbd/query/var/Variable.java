package com.thdev.jqbd.query.var;

import com.thdev.jqbd.ConnectionType;

public class Variable {

	private final String columnName;
	private final int size;
	private final VarType type;
	private final VarArgument argument;
	
	public Variable(String columnName,VarType type) {
		this(columnName, 0, type, VarArgument.NONE);
	}
	
	public Variable(String columnName, VarType type, VarArgument argument) {
		this(columnName, 0, type, argument);
	}
	
	public Variable(String columnName, int size, VarType type) {
		this(columnName, size, type, VarArgument.NONE);
	}
	
	public Variable(String columnName, int size, VarType type, VarArgument argument) {
		this.columnName = columnName;
		this.size = size;
		this.type = type;
		this.argument = argument;
	}
	
	public String build(ConnectionType connectionType) {
		String result = columnName + " " + type.getContextByType(connectionType);
		
		if(type == VarType.DOUBLE)
			if(size != 0) result += "("+size+", 2)";
		else
			if(size != 0) result += "("+size+")";
			
		result += " " + argument.getName();
		
		return result;
	}
	
}
