package com.takuiash.jqbd.query.helpers.column;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Column {

	private final String fieldName;
	private final FieldType fieldType;
	
	private Object defaults;
	private Integer size;
	
	private List<Option> options = new ArrayList<Option>();
	
	public Column(String fieldName, FieldType fieldType) {
		this.fieldType = fieldType;
		this.fieldName = fieldName;
	}
	
	public Column addOption(Option option) {
		if(!options.contains(option))
			this.options.add(option);
		
		return this;
	}
	
	public boolean hasDefault() {
		return defaults != null;
	}
	
	public boolean isPrimary() {
		return options.contains(Option.PRIMARY);
	}
	
	public String getFieldName() {
		return fieldName;
	}
	
	public List<Option> getOptions() {
		return Collections.unmodifiableList(options);
	}
	
	public Object getDefault() {
		return defaults;
	}
	
	public Column setSize(int size) {
		this.size = size;
		return this;
	}
	
	public FieldType getFieldType() {
		return this.fieldType;
	}
	
	public int getSize() {
		return this.size;
	}
	
	public Column setDefault(Object defaults) {
		this.defaults = defaults;
		
		return this;
	}
	
	public enum FieldType {
		VARCHAR("VARCHAR", "VARCHAR"),
		TEXT("TEXT", "TEXT"),
		INT("INT", "INT"),
		DOUBLE("DOUBLE", "DECIMAL");

		private final String mysql, sqlite;
		
		FieldType(String mysql, String sqlite) {
			this.mysql = mysql;
			this.sqlite = sqlite;
		}
		
		public String getMysql() {
			return this.mysql;
		}
		
		public String getSqlite() {
			return this.sqlite;
		}
		
	}
}
