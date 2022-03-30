package com.takuiash.jqbd.query;

import java.sql.SQLException;

import com.takuiash.jqbd.query.helpers.Column;
import com.takuiash.jqbd.query.helpers.builders.ConditionBuilder;
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
	 * @param columns
	 * @return {@link Delete}
	 */
	public Delete where(Column... columns) { conditions.put(ConditionType.WHERE, columns); return this; }
	
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
	 * @param columnObjects
	 * @return {@link Delete}
	 */
	public Delete or(Column... columns) { conditions.put(ConditionType.OR, columns); return this; }
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
	 * @param columnObjects
	 * @return {@link Delete}
	 */
	public Delete whereNot(Column... columns) { conditions.put(ConditionType.WHERE_NOT, columns); return this; }
	
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
	 * @param columnObjects
	 * @return {@link Delete}
	 */
	public Delete orNot(Column... columns) { conditions.put(ConditionType.OR_NOT, columns); return this; }

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
