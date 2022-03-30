package com.takuiash.jqbd.query.helpers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Row {

	private final String name;
	private final FieldType type;
	
	private Object defaults;
	private Integer size;
	
	private List<Option> options = new ArrayList<Option>();
	
	public Row(String name, FieldType type) {
		this.name = name;
		this.type = type;
	}
	
	public Row(String name, int size, FieldType type) {
		this.name = name;
		this.size = size;
		this.type = type;
	}
	
	public Row addOptions(Option... options) {
		for(Option option : options) {
			if(!this.options.contains(option))
				this.options.add(option);
		}
		
		return this;
	}
	
	public boolean hasDefault() {
		return defaults != null;
	}
	
	public boolean isPrimary() {
		return options.contains(Option.PRIMARY);
	}
	
	public String getName() {
		return name;
	}
	
	public List<Option> getOptions() {
		return Collections.unmodifiableList(options);
	}
	
	public Object getDefault() {
		return defaults;
	}
	
	public Row setSize(int size) {
		this.size = size;
		return this;
	}
	
	public FieldType getType() {
		return this.type;
	}
	
	public Integer getSize() {
		return this.size;
	}
	
	public Row setDefault(Object defaults) {
		this.defaults = defaults;
		return this;
	}
	
	public enum FieldType {
		UUID("UUID", "UUID"),
		VARCHAR("VARCHAR", "VARCHAR"),
		TEXT("TEXT", "TEXT"),
		INT("INT", "INTEGER"),
		DOUBLE("DOUBLE", "DOUBLE");

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
