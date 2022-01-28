package com.thdev.jqbd.query.builders;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.thdev.jqbd.query.condition.ConditionMap;
import com.thdev.jqbd.query.condition.ConditionType;

public class ConditionBuilder implements Builder {

	private Map<ConditionType, ConditionMap> map = new HashMap<>();
	
	public void add(ConditionType type, ConditionMap conditions) {
		ConditionMap conditionMap = map.get(type);
		
		if(conditionMap != null) map.put(type, conditionMap.merge(conditions));
		else map.put(type, conditions); 
	}
	
	public String build() {
		String result = "";
		
		for(ConditionType type : ConditionType.values()) {
			
			if(!map.containsKey(type)) continue;
			
			if(type != ConditionType.CUSTOM) 
				result += " " + type.getTag() + " ";

			for(Entry<String, Object> condition : map.get(type).getMap().entrySet()) {
				
				if(type == ConditionType.CUSTOM) { result += " " + condition.getKey() + " "; continue; }

				
				if(condition.getValue() instanceof String)
					result += condition.getKey() + "='" + condition.getValue() + "' AND ";
				else
					result += condition.getKey() + "=" + condition.getValue() + "  AND ";
			}
			if(type != ConditionType.CUSTOM)
				result = result.substring(0, result.length() - 5); 			
		}
		
		if(result.endsWith(" ")) result = result.substring(0, result.length() - 1); 			
		return result;
	}

}
