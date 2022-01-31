package com.takuiash.jqbd.jdbc.query.select;

import java.sql.SQLException;
import java.util.List;

import com.takuiash.jqbd.entity.DatabaseEntity;
import com.takuiash.jqbd.entity.EntityConverter;
import com.takuiash.jqbd.jdbc.query.helpers.column.COBJ;
import com.takuiash.jqbd.jdbc.query.helpers.condition.Condition;
import com.takuiash.jqbd.jdbc.query.helpers.condition.ConditionBuilder;
import com.takuiash.jqbd.jdbc.query.helpers.condition.ConditionMap;
import com.takuiash.jqbd.jdbc.query.helpers.condition.ConditionType;
import com.takuiash.jqbd.jdbc.worker.ReturningWork;
import com.takuiash.jqbd.jdbc.worker.search.SearchResponse;
import com.takuiash.jqbd.table.Table;

public class Selector<T> implements Select<T> {

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
	public Selector(ReturningWork<T> worker, Table table) {
		this.table = table;
		this.worker = worker;
		this.entityBase = null;
	}
	
	/**
	 * @param worker
	 * @param table
	 * @param entityBase
	 */
	public Selector(ReturningWork<T> worker, Table table, T entityBase) {
		this.table = table;
		this.worker = worker;
		this.entityBase = entityBase;
	}
		
	public Select<T> fields(String... fields) { this.fields = fields; return this; }
	
	public Select<T> where(String expression) { conditions.put(ConditionType.WHERE, expression); return this; }
	public Select<T> where(String column, Object value) { conditions.put(ConditionType.WHERE, column, value); return this; }
	public Select<T> where(COBJ... columnObjects) { conditions.put(ConditionType.WHERE, columnObjects); return this; }
	public Select<T> where(Condition condition) { conditions.put(ConditionType.WHERE, condition); return this; }
	
	public Select<T> or(String expression) { conditions.put(ConditionType.OR, expression); return this; }
	public Select<T> or(String column, Object value) { conditions.put(ConditionType.OR, column, value); return this; }
	public Select<T> or(COBJ... columnObjects) { conditions.put(ConditionType.OR, columnObjects); return this; }
	public Select<T> or(Condition condition) { conditions.put(ConditionType.OR, condition); return this; }
	
	public Select<T> whereNot(String expression) { conditions.put(ConditionType.WHERE_NOT, expression); return this; }
	public Select<T> whereNot(String column, Object value) { conditions.put(ConditionType.WHERE_NOT, column, value); return this; }
	public Select<T> whereNot(COBJ... columnObjects) { conditions.put(ConditionType.WHERE_NOT, columnObjects); return this; }
	public Select<T> whereNot(Condition condition) { conditions.put(ConditionType.WHERE_NOT, condition); return this; }
	
	public Select<T> orNot(String expression) { conditions.put(ConditionType.OR_NOT, expression); return this; }
	public Select<T> orNot(String column, Object value) { conditions.put(ConditionType.OR_NOT, column, value); return this; }
	public Select<T> orNot(COBJ... columnObjects) { conditions.put(ConditionType.OR_NOT, columnObjects); return this; }
	public Select<T> orNot(Condition condition) { conditions.put(ConditionType.OR_NOT, condition); return this; }
	
	public Select<T> conditions(ConditionMap conditions) { this.conditions = conditions; return this; }

	
	public Select<T> limit(int value) { this.limit = value; return this; }
	public Select<T> offset(int value) { this.offset = value; return this; }
	
	public SearchResponse<T> search() throws SQLException { return worker.execute(build(), table.getConnection()); }
	
	@Override
	public List<T> all() throws SQLException {
		return this.search().all();
	}

	@Override
	public T first() throws SQLException {
		return this.limit(1).search().first();
	}

	@Override
	public T last() throws SQLException {
		return this.search().last();
	}
	
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
		
		System.out.println(builder.toString());
		
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
				
				fields = EntityConverter.getColumns((DatabaseEntity) entityBase, true);
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