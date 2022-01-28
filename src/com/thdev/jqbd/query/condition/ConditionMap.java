package com.thdev.jqbd.query.condition;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ConditionMap {

	private Map<String, Object> map = new HashMap<>();
	
	public ConditionMap put(String column, Object value) {
		this.map.put(column, value);
		return this;
	}
	
	public ConditionMap remove(String column) {
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
	
	public static ConditionMap convert(CObj... conditions) {
		ConditionMap map = new ConditionMap();

		for(CObj condition : conditions) {
			map.put(condition.getColumn(), condition.getValue());
		}
		
		return map;
	}
	
	public ConditionMap merge(ConditionMap conditions) {
		conditions.getMap().forEach((a, b) -> this.map.put(a, b));
		return this;
	}
	
	public Map<String, Object> getMap() {
		return Collections.unmodifiableMap(map);
	}
}
