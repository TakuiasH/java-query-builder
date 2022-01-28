package com.thdev.jqbd.query.set;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SetMap {

	private Map<String, Object> map = new HashMap<>();
	
	public SetMap put(String column, Object value) {
		this.map.put(column, value);
		return this;
	}
	
	public SetMap remove(String column) {
		this.map.remove(column);
		return this;
	}
	
	public boolean containsColumn(String column) {
		return this.map.containsKey(column);
	}
	
	public boolean containsValue(Object value) {
		return this.map.containsValue(value);
	}
	
	public boolean isEmpty() {
		return this.map.isEmpty();
	}
	
	public int size() {
		return this.map.size();
	}
	
	public void clear() {
		this.map.clear();
	}
	
	public static SetMap convert(SET... conditions) {
		SetMap map = new SetMap();

		for(SET condition : conditions) {
			map.put(condition.getColumn(), condition.getValue());
		}
		
		return map;
	}
	
	public SetMap merge(SetMap conditions) {
		conditions.getMap().forEach((a, b) -> this.map.put(a, b));
		return this;
	}
	
	public Map<String, Object> getMap() {
		return Collections.unmodifiableMap(map);
	}
}
