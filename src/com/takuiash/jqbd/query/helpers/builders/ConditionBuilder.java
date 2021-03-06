package com.takuiash.jqbd.query.helpers.builders;

import java.util.Map.Entry;

import com.takuiash.jqbd.query.helpers.condition.Condition;
import com.takuiash.jqbd.query.helpers.condition.ConditionType;
import com.takuiash.jqbd.query.helpers.map.maps.ConditionMap;

public class ConditionBuilder {

	public static StringBuilder buid(ConditionMap map) {
		StringBuilder query = new StringBuilder();
		
		
		for(ConditionType type : ConditionType.values()) {
			String conditionType = type.toString().replaceAll("_", " ");
			Condition condition = map.get(type);
			
			if(condition == null)
				continue;
			
			StringBuilder conditionQuery = new StringBuilder();
			conditionQuery.append(" ");
			conditionQuery.append(conditionType);
			conditionQuery.append(" ");
			
			String separator = "";
			for(Entry<String, Object> value : condition.values().entrySet()) {
				if(value.getValue() == null) continue;
				
				conditionQuery.append(separator);
				separator = " AND ";
				
				conditionQuery.append(value.getKey());
				conditionQuery.append("=");
				conditionQuery.append(value.getValue() instanceof String ? "'" + value.getValue() + "'" : value.getValue());
			}
			
			separator = "";
			for(String expression : condition.expressions()) {
				if(expression == null) continue;
				
				conditionQuery.append(separator);
				separator = " AND ";
				
				conditionQuery.append(expression);
			}
			
			
			query.append(conditionQuery);
		}
		
		return query;
	}
}
