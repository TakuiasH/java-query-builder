package com.takuiash.orm.jdbc.query.helpers;

import java.util.Map.Entry;

import com.takuiash.orm.jdbc.query.helpers.column.ColumnMap;

public class UpdateValueBuilder {

	public static StringBuilder build(ColumnMap values) {
		StringBuilder builder = new StringBuilder(" SET ");
		
		String separator = "";

		for(Entry<String, Object> value : values.getValues().entrySet()) {
			if(value.getValue() == null) continue;
			
			builder.append(separator);
			separator = ", ";
			
			builder.append(value.getKey());
			builder.append("=");
			
			if(value.getValue() instanceof String)
				builder.append("'" + value.getValue() + "'");
			else
				builder.append(value.getValue());
			
		}
		
		return builder;
	}
	
}
