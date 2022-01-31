package com.takuiash.jqbd.query;

import java.sql.SQLException;

import com.takuiash.jqbd.query.helpers.builders.ConditionBuilder;
import com.takuiash.jqbd.query.helpers.column.COBJ;
import com.takuiash.jqbd.query.helpers.condition.Condition;
import com.takuiash.jqbd.query.helpers.condition.ConditionType;
import com.takuiash.jqbd.query.helpers.map.maps.ConditionMap;
import com.takuiash.jqbd.worker.Work;

/**
 * @author THDev
 */
public class Delete {

	protected final Table table;
	protected final Work worker;
	
	private ConditionMap conditions = new ConditionMap();
	
	/**
	 * @param worker
	 * @param table
	 */
	public Delete(Work worker, Table table) {
		this.table = table;
		this.worker = worker;
	}

	/**
	 * TODO Add 'where' argument.
	 * 
	 * @param expression
	 * @return {@link Delete}
	 */
	public Delete where(String expression) { conditions.put(ConditionType.WHERE, expression); return this; }
	/**
	 * TODO Add 'where' argument.
	 * 
	 * @param column
	 * @param value
	 * @return {@link Delete}
	 */
	public Delete where(String column, Object value) { conditions.put(ConditionType.WHERE, column, value); return this; }
	/**
	 * TODO Add 'where' argument.
	 * 
	 * @param columnObjects
	 * @return {@link Delete}
	 */
	public Delete where(COBJ... columnObjects) { conditions.put(ConditionType.WHERE, columnObjects); return this; }
	/**
	 * TODO Add 'where' argument.
	 * 
	 * @param condition
	 * @return {@link Delete}
	 */
	public Delete where(Condition condition) { conditions.put(ConditionType.WHERE, condition); return this; }
	
	/**
	 * TODO Add 'or' argument.
	 * 
	 * @param expression
	 * @return {@link Delete}
	 */
	public Delete or(String expression) { conditions.put(ConditionType.OR, expression); return this; }
	/**
	 * TODO Add 'or' argument.
	 * 
	 * @param column
	 * @param value
	 * @return {@link Delete}
	 */
	public Delete or(String column, Object value) { conditions.put(ConditionType.OR, column, value); return this; }
	/**
	 * TODO Add 'or' argument.
	 * 
	 * @param columnObjects
	 * @return {@link Delete}
	 */
	public Delete or(COBJ... columnObjects) { conditions.put(ConditionType.OR, columnObjects); return this; }
	/**
	 * TODO Add 'or' argument.
	 * 
	 * @param condition
	 * @return {@link Delete}
	 */
	public Delete or(Condition condition) { conditions.put(ConditionType.OR, condition); return this; }
	
	/**
	 * TODO Add 'where not' argument.
	 * 
	 * @param expression
	 * @return {@link Delete}
	 */
	public Delete whereNot(String expression) { conditions.put(ConditionType.WHERE_NOT, expression); return this; }
	/**
	 * TODO Add 'where not' argument.
	 * 
	 * @param column
	 * @param value
	 * @return {@link Delete}
	 */
	public Delete whereNot(String column, Object value) { conditions.put(ConditionType.WHERE_NOT, column, value); return this; }
	/**
	 * TODO Add 'where not' argument.
	 * 
	 * @param columnObjects
	 * @return {@link Delete}
	 */
	public Delete whereNot(COBJ... columnObjects) { conditions.put(ConditionType.WHERE_NOT, columnObjects); return this; }
	/**
	 * TODO Add 'where not' argument.
	 * 
	 * @param condition
	 * @return {@link Delete}
	 */
	public Delete whereNot(Condition condition) { conditions.put(ConditionType.WHERE_NOT, condition); return this; }
	
	/**
	 * TODO Add 'or not' argument.
	 * 
	 * @param expression
	 * @return {@link Delete}
	 */
	public Delete orNot(String expression) { conditions.put(ConditionType.OR_NOT, expression); return this; }
	/**
	 * TODO Add 'or not' argument.
	 * 
	 * @param column
	 * @param value
	 * @return {@link Delete}
	 */
	public Delete orNot(String column, Object value) { conditions.put(ConditionType.OR_NOT, column, value); return this; }
	/**
	 * TODO Add 'or not' argument.
	 * 
	 * @param columnObjects
	 * @return {@link Delete}
	 */
	public Delete orNot(COBJ... columnObjects) { conditions.put(ConditionType.OR_NOT, columnObjects); return this; }
	/**
	 * TODO Add 'or not' argument.
	 * 
	 * @param condition
	 * @return {@link Delete}
	 */
	public Delete orNot(Condition condition) { conditions.put(ConditionType.OR_NOT, condition); return this; }

	/**
	 * TODO Set the condition map
	 * 
	 * @param conditions
	 * @return {@link Delete}
	 */
	public Delete conditions(ConditionMap conditions) { this.conditions = conditions; return this; }

	/**
	 * TODO execute the query and returns the count of rows affected
	 * 
	 * @return {@link Integer}
	 * @throws SQLException
	 */
	public int execute() { return worker.execute(build(), table.getConnection()); }
	
	/**
	 * TODO Returns the query string.
	 * 
	 * @return {@link String}
	 */
	public <T> String build() {
		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(table.getName());
		builder.append(ConditionBuilder.buid(conditions));
		builder.append(";");
		
		return builder.toString();
	}
}
