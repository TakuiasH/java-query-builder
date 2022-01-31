package com.takuiash.jqbd.worker.search.generic;

import java.util.Map;

public class SelectData {

	private final Map<String, Object> values;
	
	public SelectData(Map<String, Object> values) {
		this.values = values;
	}
	
	public Object get(String column) {
		return values.get(column);
	}
	
	public Map<String, Object> getValues() {
		return values;
	}
	
}
