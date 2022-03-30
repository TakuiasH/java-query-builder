package com.takuiash.jqbd.query.helpers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TableSetup {

	private List<Row> rows = new ArrayList<Row>();

	private String tableName;
	
	public TableSetup() {
	}
	
	public TableSetup(String tableName) {
		this.tableName = tableName;
	}
	
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	public String getTableName() {
		return this.tableName;
	}
	
	public void addRow(Row column) {
		this.rows.add(column);
	}
	
	public Row getRow(int index) {
		return rows.get(index);
	}
	
	public Row getPrimaryRow() {
		return rows.stream().filter(c -> c.isPrimary()).findFirst().orElse(null);
	}
	
	public Row getRow(String rowName) {
		return rows.stream().filter(c -> c.getName() == rowName).findFirst().orElse(null);
	}
	
	public int rowsSize() {
		return rows.size();
	}

	public List<Row> getRows(boolean includePrimary) {
		if(includePrimary)
			return Collections.unmodifiableList(rows);
		
		List<Row> tempList = new ArrayList<Row>();
		
		for (Row row : rows) {
			if(!includePrimary && row.isPrimary())
				continue;
			
			tempList.add(row);
		}
		
		return Collections.unmodifiableList(tempList);
	}
	
}
