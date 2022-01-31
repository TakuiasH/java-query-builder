package com.takuiash.jqbd.query.helpers.condition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Condition {

	private Map<String, Object> values = new HashMap<String, Object>();
	private List<String> expressions = new ArrayList<String>();
	
	public Condition value(String column, Object value) {
		if(values.containsKey(column))
			throw new DuplicatedConditionException();
		
		values.put(column, value);
		return this;
	}
	
	public Condition expression(String expression) {
		if(expressions.contains(expression))
			throw new DuplicatedConditionException();
		
		this.expressions.add(expression);
		return this;
	}
	
	public Map<String, Object> values() {
		return Collections.unmodifiableMap(values);
	}
	
	public List<String> expressions() {
		return Collections.unmodifiableList(expressions);
	}

	public void merge(Condition condition) {
		if(!condition.expressions.isEmpty())
			for(String expression : condition.expressions()) this.expression(expression);
		
		if(!condition.values.isEmpty())
			for(Entry<String, Object> value : condition.values().entrySet()) this.value(value.getKey(), value.getValue());
	}
	
}
