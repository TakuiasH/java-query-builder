package com.takuiash.orm.jdbc.query.helpers;

import com.takuiash.orm.jdbc.query.helpers.column.ColumnMap;

public class InsertValueBuilder {

	public static StringBuilder build(ColumnMap map) {
		StringBuilder query = new StringBuilder(" (");

		
		String separator = "";
		for(String column : map.getValues().keySet()) {
			if(map.get(column) == null) continue;
			
			query.append(separator);
			separator = ", ";
			
			query.append(column);		
		}
		
		query.append(") VALUES (");

		separator = "";
		for(Object value : map.getValues().values()) {
			if(value == null) continue;

			query.append(separator);
			separator = ", ";
			
			if(value instanceof String)
				query.append("'" + value + "'");
			else
				query.append(value);
		}
		query.append(")");

		return query;
	}
	
}
