package com.takuiash.jqbd.query.helpers.builders;

import java.util.Map.Entry;

import com.takuiash.jqbd.query.helpers.map.maps.ColumnMap;

public class UpdateValueBuilder {

	public static StringBuilder build(ColumnMap map) {
		StringBuilder query = new StringBuilder(" SET ");
		
		String separator = "";

		for(Entry<String, Object> value : map.entrySet()) {
			if(value.getValue() == null) continue;
			
			query.append(separator);
			separator = ", ";
			
			query.append(value.getKey());
			query.append("=");
			
			if(value.getValue() instanceof String)
				query.append("'" + value.getValue() + "'");
			else
				query.append(value.getValue());
			
		}
		
		return query;
	}
	
}
