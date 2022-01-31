package com.takuiash.jqbd.entity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.takuiash.jqbd.jdbc.query.helpers.column.COBJ;
import com.takuiash.jqbd.jdbc.query.helpers.column.Column;

public class EntityConverter {
	
	public static <T> String[] getColumns(DatabaseEntity entity, boolean includePrimary) {		
		List<String> columns = new ArrayList<String>();
		entity.getColumnList().getColumns(includePrimary).forEach(c -> columns.add(c.getFieldName()));
		return columns.toArray(String[]::new);
	}
	
	
	public static <T> COBJ[] getColumnObjects(DatabaseEntity entity, boolean includePrimary) {
		List<Column> columns = entity.getColumnList().getColumns(includePrimary);
		Object[] values = getValues(entity, columns);
		
		List<COBJ> columnObjects = new ArrayList<COBJ>();
		
		for(int i = 0; i < columns.size(); i++) {			
			columnObjects.add(COBJ.as(columns.get(i).getFieldName(), values[i]));
		}
		
		return columnObjects.toArray(COBJ[]::new);
	}
	
	public static <T> Object[] getValues(DatabaseEntity entity, List<Column> columns) {
		Object[] values = new Object[columns.size()];
		
		for(int i = 0; i < columns.size(); i++) {
			try {
				values[i] = getMethod(entity, MethodType.GET, columns.get(i).getFieldName()).invoke(entity);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		
		return values;
	}
	
	
	public static <T> Method getMethod(T entity, MethodType type, String columnName) {
		for(Method method : entity.getClass().getMethods()) {
			if(method.getName().startsWith(type.toString().toLowerCase()) && 
					method.getName().length() == (columnName.length() + 3) &&
					method.getName().equalsIgnoreCase(type.toString() + columnName)) {
				
				return method;
			}
		}
		return null;
	}
	
	public enum MethodType {
		GET, SET;
	}
}
