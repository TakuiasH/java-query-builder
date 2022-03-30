package com.takuiash.jqbd.query;

import java.sql.SQLException;

import com.takuiash.jqbd.orm.Entity;
import com.takuiash.jqbd.orm.Repository;
import com.takuiash.jqbd.query.helpers.Column;
import com.takuiash.jqbd.query.helpers.builders.ConditionBuilder;
import com.takuiash.jqbd.query.helpers.builders.UpdateValueBuilder;
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

	private ColumnMap values = new ColumnMap();
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
	public Update<T> value(Column... columns) { values.put(columns); return this; }
	
	
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
	 * @param columnObjects
	 * @return {@link Update}
	 */
	public Update<T> where(Column... columns) { conditions.put(ConditionType.WHERE, columns); return this; }
	
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
	 * @param columnObjects
	 * @return {@link Update}
	 */
	public Update<T> or(Column... columns) { conditions.put(ConditionType.OR, columns); return this; }

	
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
	 * @param columnObjects
	 * @return {@link Update}
	 */
	public Update<T> whereNot(Column... columns) { conditions.put(ConditionType.WHERE_NOT, columns); return this; }

	
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
	 * @param columnObjects
	 * @return {@link Update}
	 */
	public Update<T> orNot(Column... columns) { conditions.put(ConditionType.OR_NOT, columns); return this; }


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
	public int execute() { return worker.execute(build(), table.getConnection()); }
	
	/**
	 * TODO execute the query and return the inserted value with all arguments.
	 * @param <T>
	 * 
	 * @return {@link SelectData} OR {@link Entity}
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public T executeAndSelect() {
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
		
		return builder.toString();
	}
	
}
