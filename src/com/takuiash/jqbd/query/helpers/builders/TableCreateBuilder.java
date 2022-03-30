package com.takuiash.jqbd.query.helpers.builders;


import com.takuiash.jqbd.connector.Connector.ConnectionType;
import com.takuiash.jqbd.query.helpers.Option;
import com.takuiash.jqbd.query.helpers.Row;
import com.takuiash.jqbd.query.helpers.TableSetup;
import com.takuiash.jqbd.query.helpers.Row.FieldType;

public class TableCreateBuilder {
	
	public static StringBuilder build(ConnectionType type, TableSetup list) {
		StringBuilder query = new StringBuilder(" (");
		
		String separator = "";
		for(Row row : list.getRows(true)) {
			
			query.append(separator);
			separator = ", ";
			
			query.append(row.getName());
			
			query.append(" ");
			if(type == ConnectionType.MYSQL)
				query.append(row.getType().getMysql());
			else
				query.append(row.getType().getSqlite());

			if(row.getSize() != null) {
				query.append("(");
				query.append(row.getSize());
				
				if(row.getType() == FieldType.DOUBLE)
					query.append(", 2)");
				query.append(")");
			}
			
			for(Option option : row.getOptions()) {
				query.append(" ");
				query.append(option.getTag());
			}
			
			if(row.hasDefault()) {
				query.append(" DEFAULT ");
				
				if(row.getDefault() instanceof String)
					query.append("'" + row.getDefault() + "'");
				else
					query.append(row.getDefault());
			}				
		}
		query.append(")");
		return query;
	}
	
}
