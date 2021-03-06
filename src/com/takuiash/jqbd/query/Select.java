package com.takuiash.jqbd.query;

import java.sql.SQLException;
import java.util.List;

import com.takuiash.jqbd.orm.Entity;
import com.takuiash.jqbd.orm.EntityConverter;
import com.takuiash.jqbd.query.helpers.Column;
import com.takuiash.jqbd.query.helpers.builders.ConditionBuilder;
import com.takuiash.jqbd.query.helpers.condition.ConditionType;
import com.takuiash.jqbd.query.helpers.map.maps.ConditionMap;
import com.takuiash.jqbd.worker.ReturningWork;
import com.takuiash.jqbd.worker.search.SearchResponse;
import com.takuiash.jqbd.worker.search.generic.SelectData;

/**
 * @author THDev
 */
public class Select<T> {

	protected final Table table;
	protected final ReturningWork<T> worker;
	protected final T entityBase;
	
	private String[] fields = new String[0];
	private ConditionMap conditions = new ConditionMap();
	
	private int limit = 0;
	private int offset = 0;
	
	/**
	 * @param worker
	 * @param table
	 */
	public Select(ReturningWork<T> worker, Table table) {
		this.table = table;
		this.worker = worker;
		this.entityBase = null;
	}
	
	/**
	 * @param worker
	 * @param table
	 * @param entityBase
	 */
	public Select(ReturningWork<T> worker, Table table, T entityBase) {
		this.table = table;
		this.worker = worker;
		this.entityBase = entityBase;
	}
		
	/**
	 * TODO Add fields to query.
	 * 
	 * @param fields
	 * @return {@link Select}
	 */
	public Select<T> fields(String... fields) { this.fields = fields; return this; }
	
	/**
	 * TODO Add 'where' argument.
	 * 
	 * @param expression
	 * @return {@link Select}
	 */
	public Select<T> where(String expression) { conditions.put(ConditionType.WHERE, expression); return this; }

	/**
	 * TODO Add 'where' argument.
	 * 
	 * @param columnObjects
	 * @return {@link Select}
	 */
	public Select<T> where(Column... columns) { conditions.put(ConditionType.WHERE, columns); return this; }

	
	/**
	 * TODO Add 'or' argument.
	 * 
	 * @param expression
	 * @return {@link Select}
	 */
	public Select<T> or(String expression) { conditions.put(ConditionType.OR, expression); return this; }

	/**
	 * TODO Add 'or' argument.
	 * 
	 * @param columnObjects
	 * @return {@link Select}
	 */
	public Select<T> or(Column... columns) { conditions.put(ConditionType.OR, columns); return this; }
	
	/**
	 * TODO Add 'where not' argument.
	 * 
	 * @param expression
	 * @return {@link Select}
	 */
	public Select<T> whereNot(String expression) { conditions.put(ConditionType.WHERE_NOT, expression); return this; }


	/**
	 * TODO Add 'where not' argument.
	 * 
	 * @param columnObjects
	 * @return {@link Select}
	 */
	public Select<T> whereNot(Column... columns) { conditions.put(ConditionType.WHERE_NOT, columns); return this; }

	/**
	 * TODO Add 'or not' argument.
	 * 
	 * @param expression
	 * @return {@link Select}
	 */
	public Select<T> orNot(String expression) { conditions.put(ConditionType.OR_NOT, expression); return this; }

	/**
	 * TODO Add 'or not' argument.
	 * 
	 * @param columnObjects
	 * @return {@link Select}
	 */
	public Select<T> orNot(Column... columns) { conditions.put(ConditionType.OR_NOT, columns); return this; }

	
	/**
	 * TODO Set the condition map
	 * 
	 * @param conditions
	 * @return {@link Select}
	 */
	public Select<T> conditions(ConditionMap conditions) { this.conditions = conditions; return this; }

	
	/**
	 * TODO Set the limit.
	 * 
	 * @param value
	 * @return {@link Select}
	 */
	public Select<T> limit(int value) { this.limit = value; return this; }
	/**
	 * TODO Set the offset.
	 * 
	 * @param value
	 * @return {@link Select}
	 */
	public Select<T> offset(int value) { this.offset = value; return this; }
	
	
	/**
	 * TODO Search the select query
	 * 
	 * @return {@link SearchResponse}
	 * @throws SQLException
	 */
	public SearchResponse<T> search() { return worker.execute(build(), table.getConnection()); }
	
	
	/**
	 * TODO Return all response.
	 * 
	 * @return {@link SelectData} or {@link Entity}
	 * @throws SQLException
	 */
	public List<T> all() {
		return this.search().all();
	}

	/**
	 * TODO Return first response.
	 * 
	 * @return {@link SelectData} or {@link Entity}
	 * @throws SQLException
	 */
	public T first() {
		return this.limit(1).search().first();
	}

	/**
	 * TODO Return last response.
	 * 
	 * @return {@link SelectData} or {@link Entity}
	 * @throws SQLException
	 */
	public T last() {
		return this.search().last();
	}
	
	/**
	 * TODO Returns the query string.
	 * 
	 * @return {@link String}
	 */
	public String build() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("SELECT ");
		builder.append(new FieldBuilder().build(entityBase, fields));
		builder.append(" FROM ");
		builder.append(table.getName());
		builder.append(ConditionBuilder.buid(conditions));
		
		if(limit > 0) {
			builder.append(" LIMIT ");
			builder.append(limit);
		}
		
		if(offset > 0) {
			builder.append(" OFFSET ");
			builder.append(offset);
		}
		
		builder.append(";");
				
		return builder.toString();
	}
	
	private class FieldBuilder {
		
		public StringBuilder build(T entityBase, String[] fields) {
			StringBuilder builder = new StringBuilder();
			
			if(fields.length == 0) {
				if(entityBase == null) {
					builder.append("*");
					return builder;
				}
				
				fields = EntityConverter.getRowsName((Entity) entityBase, true);
			}
						
			String separator = "";
			for(String field : fields) {
				builder.append(separator);
				separator = ", ";

				builder.append(field);
				
			}
					
			return builder;
		}
		
	}
}