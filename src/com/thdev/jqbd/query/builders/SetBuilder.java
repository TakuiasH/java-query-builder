package com.thdev.jqbd.query.builders;

import java.util.Map.Entry;

import com.thdev.jqbd.query.set.SetMap;

public class SetBuilder implements Builder {

	private SetMap map = new SetMap();
	
	public void set(SetMap map) {
		 this.map.merge(map);
	}
	
	public String build() {
		String result = " SET ";			

		for(Entry<String, Object> set : map.getMap().entrySet()) {
				
			if(set.getValue() instanceof String)
				result += set.getKey() + "='" + set.getValue() + "', ";
			else
				result += set.getKey() + "=" + set.getValue() + ", ";
		}
		
		result = result.substring(0, result.length() - 2); 			

		return result;
	}

	public String buildInsert() {
		String tables = " (";
		String values = " VALUES (";
		
		for(Entry<String, Object> set : map.getMap().entrySet()) {
				tables += set.getKey() + ", ";
				
			if(set.getValue() instanceof String)
				values += "'" + set.getValue() + "', ";
			else
				values += set.getValue() + ", ";
		}
		
		tables = tables.substring(0, tables.length() - 2) + ")"; 			
		values = values.substring(0, values.length() - 2) + ")"; 			

		return tables + values;
	}
	
}
