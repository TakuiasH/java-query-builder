package com.takuiash.jqbd.query.helpers.builders;


import com.takuiash.jqbd.query.helpers.column.Column;
import com.takuiash.jqbd.query.helpers.column.Column.FieldType;
import com.takuiash.jqbd.query.helpers.column.ColumnList;
import com.takuiash.jqbd.query.helpers.column.Option;

public class TableCreateBuilder {
	
	public static StringBuilder build(ColumnList list) {
		StringBuilder query = new StringBuilder(" (");
		String separator = "";
		for(Column column : list.getColumns(true)) {
			query.append(separator);
			separator = ", ";
			
			query.append(column.getFieldName());
			
			query.append(column.getFieldType().getSqlite());
			query.append("(");
			query.append(column.getSize());
			if(column.getFieldType() == FieldType.DOUBLE)
				query.append(", 2)");
			query.append(")");
			
			for(Option option : column.getOptions()) {
				query.append(" ");
				query.append(option.getTag());
			}
			
			if(column.hasDefault()) {
				query.append("DEFAULT ");
				
				if(column.getDefault() instanceof String)
					query.append("'" + column.getDefault() + "'");
				else
					query.append(column.getDefault());
			}				
		}
		query.append(")");
		return query;
	}
	
}
