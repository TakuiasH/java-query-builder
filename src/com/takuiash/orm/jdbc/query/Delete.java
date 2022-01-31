package com.takuiash.orm.jdbc.query;

import java.sql.SQLException;

import com.takuiash.orm.jdbc.query.helpers.column.COBJ;
import com.takuiash.orm.jdbc.query.helpers.condition.Condition;
import com.takuiash.orm.jdbc.query.helpers.condition.ConditionBuilder;
import com.takuiash.orm.jdbc.query.helpers.condition.ConditionMap;
import com.takuiash.orm.jdbc.query.helpers.condition.ConditionType;
import com.takuiash.orm.jdbc.worker.Work;
import com.takuiash.orm.table.Table;

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

	public Delete where(String expression) { conditions.put(ConditionType.WHERE, expression); return this; }
	public Delete where(String column, Object value) { conditions.put(ConditionType.WHERE, column, value); return this; }
	public Delete where(COBJ... columnObjects) { conditions.put(ConditionType.WHERE, columnObjects); return this; }
	public Delete where(Condition condition) { conditions.put(ConditionType.WHERE, condition); return this; }
	
	public Delete or(String expression) { conditions.put(ConditionType.OR, expression); return this; }
	public Delete or(String column, Object value) { conditions.put(ConditionType.OR, column, value); return this; }
	public Delete or(COBJ... columnObjects) { conditions.put(ConditionType.OR, columnObjects); return this; }
	public Delete or(Condition condition) { conditions.put(ConditionType.OR, condition); return this; }
	
	public Delete whereNot(String expression) { conditions.put(ConditionType.WHERE_NOT, expression); return this; }
	public Delete whereNot(String column, Object value) { conditions.put(ConditionType.WHERE_NOT, column, value); return this; }
	public Delete whereNot(COBJ... columnObjects) { conditions.put(ConditionType.WHERE_NOT, columnObjects); return this; }
	public Delete whereNot(Condition condition) { conditions.put(ConditionType.WHERE_NOT, condition); return this; }
	
	public Delete orNot(String expression) { conditions.put(ConditionType.OR_NOT, expression); return this; }
	public Delete orNot(String column, Object value) { conditions.put(ConditionType.OR_NOT, column, value); return this; }
	public Delete orNot(COBJ... columnObjects) { conditions.put(ConditionType.OR_NOT, columnObjects); return this; }
	public Delete orNot(Condition condition) { conditions.put(ConditionType.OR_NOT, condition); return this; }
	
	public Delete conditions(ConditionMap conditions) { this.conditions = conditions; return this; }

	/**
	 * TODO execute the query and returns the count of rows affected
	 * 
	 * @return {@link Integer}
	 * @throws SQLException
	 */
	public int execute() throws SQLException { return worker.execute(build(), table.getConnection()); }
	
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

		System.out.println(builder.toString());
		
		return builder.toString();
	}
}
