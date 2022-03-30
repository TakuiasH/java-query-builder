package com.takuiash.jqbd.orm;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.takuiash.jqbd.query.helpers.Column;
import com.takuiash.jqbd.query.helpers.Row;

public class EntityConverter {
	
	public static <T> String[] getRowsName(Entity entity, boolean includePrimary) {		
		List<String> rows = new ArrayList<String>();
		
		entity.getSetup().getRows(includePrimary).forEach(c -> rows.add(c.getName()));
		
		return rows.toArray(String[]::new);
	}
	
	public static <T> Column[] getColumnObjects(Entity entity, boolean includePrimary) {
		List<Row> rows = entity.getSetup().getRows(includePrimary);
		
		Object[] values = getValues(entity, rows);
		
		List<Column> columnObjects = new ArrayList<Column>();
		
		for(int i = 0; i < rows.size(); i++) 		
			columnObjects.add(Column.as(rows.get(i).getName(), values[i]));
		
		return columnObjects.toArray(Column[]::new);
	}
	
	public static <T> Object[] getValues(Entity entity, List<Row> columns) {
		Object[] values = new Object[columns.size()];
		
		for(int i = 0; i < columns.size(); i++) {
			try {
				values[i] = getMethod(entity, MethodType.GET, columns.get(i).getName()).invoke(entity);
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
