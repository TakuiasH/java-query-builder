package com.takuiash.jqbd.query;

import java.sql.SQLException;

import com.takuiash.jqbd.orm.DatabaseEntity;
import com.takuiash.jqbd.orm.Repository;
import com.takuiash.jqbd.query.helpers.builders.ConditionBuilder;
import com.takuiash.jqbd.query.helpers.builders.UpdateValueBuilder;
import com.takuiash.jqbd.query.helpers.column.COBJ;
import com.takuiash.jqbd.query.helpers.condition.Condition;
import com.takuiash.jqbd.query.helpers.condition.ConditionType;
import com.takuiash.jqbd.query.helpers.map.maps.ColumnMap;
import com.takuiash.jqbd.query.helpers.map.maps.ConditionMap;
import com.takuiash.jqbd.worker.Work;
import com.takuiash.jqbd.worker.search.generic.SelectData;

/**
 * @author THDev
 */
public class Update<T> {

	protected final Table table;
	protected final Work worker;
	private final Repository<T> repository;

	public ColumnMap values = new ColumnMap();
	private ConditionMap conditions = new ConditionMap();

	/**
	 * @param worker
	 * @param table
	 * @param repository
	 */
	public Update(Work worker, Table table, Repository<T> repository) {
		this.table = table;
		this.worker = worker;
		this.repository = repository;
	}
	
	/**
	 * TODO Add arguments to update.
	 * 
	 * @param column
	 * @param value
	 * @return {@link Update}
	 */
	public Update<T> value(String column, Object value) { values.put(column, value); return this; }
	
	/**
	 * TODO Add arguments to update.
	 * 
	 * @param columnObjects
	 * @return {@link Update}
	 */
	public Update<T> value(COBJ... columnObjects) { values.put(columnObjects); return this; }
	
	
	/**
	 * TODO Add 'where' argument.
	 * 
	 * @param expression
	 * @return {@link Update}
	 */
	public Update<T> where(String expression) { conditions.put(ConditionType.WHERE, expression); return this; }
	/**
	 * TODO Add 'where' argument.
	 * 
	 * @param column
	 * @param value
	 * @return {@link Update}
	 */
	public Update<T> where(String column, Object value) { conditions.put(ConditionType.WHERE, column, value); return this; }
	/**
	 * TODO Add 'where' argument.
	 * 
	 * @param columnObjects
	 * @return {@link Update}
	 */
	public Update<T> where(COBJ... columnObjects) { conditions.put(ConditionType.WHERE, columnObjects); return this; }
	/**
	 * TODO Add 'where' argument.
	 * 
	 * @param condition
	 * @return {@link Update}
	 */
	public Update<T> where(Condition condition) { conditions.put(ConditionType.WHERE, condition); return this; }
	
	/**
	 * TODO Add 'or' argument.
	 * 
	 * @param expression
	 * @return {@link Update}
	 */
	public Update<T> or(String expression) { conditions.put(ConditionType.OR, expression); return this; }
	/**
	 * TODO Add 'or' argument.
	 * 
	 * @param column
	 * @param value
	 * @return {@link Update}
	 */
	public Update<T> or(String column, Object value) { conditions.put(ConditionType.OR, column, value); return this; }
	/**
	 * TODO Add 'or' argument.
	 * 
	 * @param columnObjects
	 * @return {@link Update}
	 */
	public Update<T> or(COBJ... columnObjects) { conditions.put(ConditionType.OR, columnObjects); return this; }
	/**
	 * TODO Add 'or' argument.
	 * 
	 * @param condition
	 * @return {@link Update}
	 */
	public Update<T> or(Condition condition) { conditions.put(ConditionType.OR, condition); return this; }
	
	/**
	 * TODO Add 'where not' argument.
	 * 
	 * @param expression
	 * @return {@link Update}
	 */
	public Update<T> whereNot(String expression) { conditions.put(ConditionType.WHERE_NOT, expression); return this; }
	/**
	 * TODO Add 'where not' argument.
	 * 
	 * @param column
	 * @param value
	 * @return {@link Update}
	 */
	public Update<T> whereNot(String column, Object value) { conditions.put(ConditionType.WHERE_NOT, column, value); return this; }
	/**
	 * TODO Add 'where not' argument.
	 * 
	 * @param columnObjects
	 * @return {@link Update}
	 */
	public Update<T> whereNot(COBJ... columnObjects) { conditions.put(ConditionType.WHERE_NOT, columnObjects); return this; }
	/**
	 * TODO Add 'where not' argument.
	 * 
	 * @param condition
	 * @return {@link Update}
	 */
	public Update<T> whereNot(Condition condition) { conditions.put(ConditionType.WHERE_NOT, condition); return this; }
	
	/**
	 * TODO Add 'or not' argument.
	 * 
	 * @param expression
	 * @return {@link Update}
	 */
	public Update<T> orNot(String expression) { conditions.put(ConditionType.OR_NOT, expression); return this; }
	/**
	 * TODO Add 'or not' argument.
	 * 
	 * @param column
	 * @param value
	 * @return {@link Update}
	 */
	public Update<T> orNot(String column, Object value) { conditions.put(ConditionType.OR_NOT, column, value); return this; }
	/**
	 * TODO Add 'or not' argument.
	 * 
	 * @param columnObjects
	 * @return {@link Update}
	 */
	public Update<T> orNot(COBJ... columnObjects) { conditions.put(ConditionType.OR_NOT, columnObjects); return this; }
	/**
	 * TODO Add 'or not' argument.
	 * 
	 * @param condition
	 * @return {@link Update}
	 */
	public Update<T> orNot(Condition condition) { conditions.put(ConditionType.OR_NOT, condition); return this; }

	/**
	 * TODO Set the condition map
	 * 
	 * @param conditions
	 * @return {@link Update}
	 */
	public Update<T> conditions(ConditionMap conditions) { this.conditions = conditions; return this; }
	
	/**
	 * TODO execute the query and returns the count of rows affected
	 * 
	 * @return {@link Integer}
	 * @throws SQLException
	 */
	public int execute() throws SQLException { return worker.execute(build(), table.getConnection()); }
	
	/**
	 * TODO execute the query and return the inserted value with all arguments.
	 * @param <T>
	 * 
	 * @return {@link SelectData} OR {@link DatabaseEntity}
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public T executeAndSelect() throws SQLException {
		int rowsCount = execute();
		
		if(rowsCount == 0)
			return null;
		
		if(repository == null)
			return (T) table.select().conditions(conditions).first();
		else
			return repository.select().conditions(conditions).first();
	}
	
	/**
	 * TODO Returns the query string.
	 * 
	 * @return {@link String}
	 */
	public String build() {
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ");
		builder.append(table.getName());
		builder.append(UpdateValueBuilder.build(values));
		builder.append(ConditionBuilder.buid(conditions));
		builder.append(";");

		System.out.println(builder.toString());
		
		return builder.toString();
	}
	
}
