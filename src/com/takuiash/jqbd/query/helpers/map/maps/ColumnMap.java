package com.takuiash.jqbd.query.helpers.map.maps;

import java.util.Map;
import java.util.Map.Entry;

import com.takuiash.jqbd.query.helpers.Column;
import com.takuiash.jqbd.query.helpers.map.AbstractObjectMap;

public class ColumnMap extends AbstractObjectMap<String, Object> {
	
	public void put(Column... columnObjects) {
		for (Column cobj : columnObjects) {
			this.put(cobj.getColumn(), cobj.getValue());
		}
	}
	
	public void remove(String column) {
		this.values.remove(column);
	}
	
	public Column[] toColumnObjects() {
		Column[] result = new Column[size()];
		
		int i = 0;
		
		for(Entry<String, Object> entry : values.entrySet()) {
			result[i] = Column.as(entry.getKey(), entry.getValue());
			i++;
		}
		
		return result;
	}
	
	public void merge(String column, Object value) {
		this.values.put(column, value);
	}
	
	public void merge(Map<String, Object> values) {
		for(Entry<String, Object> entry : values.entrySet()) {
			this.values.put(entry.getKey(), entry.getValue());
		}
	}
	
}
