package com.takuiash.jqbd.query.helpers.map.maps;

import java.util.Map;
import java.util.Map.Entry;

import com.takuiash.jqbd.query.helpers.column.COBJ;
import com.takuiash.jqbd.query.helpers.column.DuplicatedColumnException;
import com.takuiash.jqbd.query.helpers.map.AbstractObjectMap;

public class ColumnMap extends AbstractObjectMap<String, Object> {
	
	public void put(String column, Object value) {
		if(containsKey(column))
			throw new DuplicatedColumnException();
		
		super.put(column, value);
	}
	
	public void put(COBJ... columnObjects) {
		for (COBJ cobj : columnObjects) {
			this.put(cobj.getColumn(), cobj.getValue());
		}
	}
	
	public void remove(String column) {
		this.values.remove(column);
	}
	
	public COBJ[] toColumnObjects() {
		COBJ[] result = new COBJ[size()];
		
		int i = 0;
		
		for(Entry<String, Object> entry : values.entrySet()) {
			result[i] = COBJ.as(entry.getKey(), entry.getValue());
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
