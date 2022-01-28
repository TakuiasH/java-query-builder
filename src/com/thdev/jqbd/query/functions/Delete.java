package com.thdev.jqbd.query.functions;

import com.thdev.jqbd.Database;
import com.thdev.jqbd.query.base.BaseQuery;
import com.thdev.jqbd.query.builders.ConditionBuilder;
import com.thdev.jqbd.query.condition.CObj;
import com.thdev.jqbd.query.condition.ConditionMap;
import com.thdev.jqbd.query.condition.ConditionType;
import com.thdev.jqbd.query.table.Table;

public class Delete extends BaseQuery {

	public Delete(Database database) { super(database); }
	public Delete(Database database, Table table) { super(database, table); }

	private ConditionBuilder conditionBuilder = new ConditionBuilder();
	
	public Delete where(String column, Object value) { return condition(ConditionType.WHERE, column, value); }
	public Delete where(CObj... conditions) { return condition(ConditionType.WHERE, conditions); }
	public Delete where(ConditionMap conditions) { return condition(ConditionType.WHERE, conditions); }
	
	public Delete whereNot(String column, Object value) { return condition(ConditionType.WHERE_NOT, column, value); }
	public Delete whereNot(CObj... conditions) { return condition(ConditionType.WHERE_NOT, conditions); }
	public Delete whereNot(ConditionMap conditions) { return condition(ConditionType.WHERE_NOT, conditions); }
	
	public Delete or(String column, Object value) { return condition(ConditionType.OR, column, value); }
	public Delete or(CObj... conditions) { return condition(ConditionType.OR, conditions); }
	public Delete or(ConditionMap conditions) { return condition(ConditionType.OR, conditions); }
	
	public Delete orNot(String column, Object value) { return condition(ConditionType.OR_NOT, column, value); }
	public Delete orNot(CObj... conditions) { return condition(ConditionType.OR_NOT, conditions); }
	public Delete orNot(ConditionMap conditions) { return condition(ConditionType.OR_NOT, conditions); }

	public Delete condition(ConditionType type, String column, Object value) { return condition(type, CObj.as(column, value)); }
	public Delete condition(ConditionType type, CObj... conditions) { conditionBuilder.add(type, ConditionMap.convert(conditions)); return this; }
	public Delete condition(ConditionType type, ConditionMap conditions) { conditionBuilder.add(type, conditions); return this; }

	public void execute() {
		getDatabase().executeUpdate(build());
	}
	
	public String build() {
		String result = "DELETE"; 
		
		result += " FROM " + getTable().getName();
		result += conditionBuilder.build();
		
		result += ";";
		
		return result;
	}

	
}
