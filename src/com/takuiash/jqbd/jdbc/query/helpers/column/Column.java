package com.takuiash.jqbd.jdbc.query.helpers.column;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Column {

	private final String fieldName;
	private Object defaults;

	private List<Option> options = new ArrayList<Option>();
	
	public Column(String fieldName) {
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
	
	public Column setDefault(Object defaults) {
		this.defaults = defaults;
		
		return this;
	}
}
