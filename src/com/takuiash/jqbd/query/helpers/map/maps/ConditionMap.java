package com.takuiash.jqbd.query.helpers.map.maps;

import java.util.Map.Entry;

import com.takuiash.jqbd.query.helpers.Column;
import com.takuiash.jqbd.query.helpers.condition.Condition;
import com.takuiash.jqbd.query.helpers.condition.ConditionType;
import com.takuiash.jqbd.query.helpers.map.AbstractObjectMap;

public class ConditionMap extends AbstractObjectMap<ConditionType, Condition> {
	
	public void put(ConditionType type, Condition condition) {
		Condition cachedCondition = get(type);
		
		if(cachedCondition == null)
			cachedCondition = condition;
		else
			cachedCondition.merge(condition);
		
		values.put(type, cachedCondition);
	}
	

	public void put(ConditionType type, String column, Object value) {
		this.put(type, new Condition().value(column, value));
	}

	public void put(ConditionType type, String expression) {
		this.put(type, new Condition().expression(expression));
	}
	
	public void put(ConditionType type, Column... columnObjects) {
		Condition condition = new Condition();
		
		for (Column cobj : columnObjects) {
			condition.value(cobj.getColumn(), cobj.getValue());
		}
		
		this.put(type, condition);
	}
	
	public Column[] toColumnObjects(ConditionType type) {
		Column[] result = new Column[size()];
		
		int i = 0;
		
		for(Entry<String, Object> entry : values.get(type).values().entrySet()) {
			result[i] = Column.as(entry.getKey(), entry.getValue());
			i++;
		}
		
		return result;
	}
}
