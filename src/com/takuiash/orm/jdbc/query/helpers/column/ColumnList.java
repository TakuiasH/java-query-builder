package com.takuiash.orm.jdbc.query.helpers.column;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ColumnList {

	private List<Column> columns = new ArrayList<Column>();

	public void add(Column column) {
		this.columns.add(column);
	}
	
	public Column get(int index) {
		return columns.get(index);
	}
	
	public Column get(String columnName) {
		return columns.stream().filter(c -> c.getFieldName() == columnName).findFirst().orElse(null);
	}
	
	public int size() {
		return columns.size();
	}

	public List<Column> getColumns(boolean includePrimary) {
		if(includePrimary)
			return Collections.unmodifiableList(columns);
		
		List<Column> tempList = new ArrayList<Column>();
		
		for (Column column : columns) {
			if(!includePrimary && column.isPrimary())
				continue;
			
			tempList.add(column);
		}
		
		return Collections.unmodifiableList(tempList);
	}
	
}
