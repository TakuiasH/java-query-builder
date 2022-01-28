package com.thdev.jqbd.query.response;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ResponseMap {
	
	private Map<String, Object> map = new HashMap<>();
	
	public void put(String column, Object value) {
		map.put(column, value);
	}
	
	public Object get(String column) {
		return map.get(column);
	}
	
	public Set<Entry<String, Object>> entrySet() {
		return map.entrySet();
	}
	
	public boolean isEmpty() {
		return map.isEmpty();
	}
	
	public boolean containsValue(Object value) {
		return map.containsValue(value);
	}
	
	public boolean containsColumn(String column) {
		return map.containsKey(column);
	}
	
	public int size() {
		return map.size();
	}
	
	public Object getObject(String column) {
		return map.get(column);
	}
	
	public String getString(String column) {
		return (String) map.get(column);
	}
	
	public int getInteger(String column) {
		return (int) map.get(column);
	}
	
	public double getDouble(String column) {
		return (double) map.get(column);
	}
	
	public boolean getBoolean(String column) {
		return getInteger(column) == 1 ? true : false;
	}
}
