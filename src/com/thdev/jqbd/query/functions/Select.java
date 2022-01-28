package com.thdev.jqbd.query.functions;

import com.thdev.jqbd.Database;
import com.thdev.jqbd.query.base.BaseQuery;
import com.thdev.jqbd.query.base.Gettable;
import com.thdev.jqbd.query.builders.ConditionBuilder;
import com.thdev.jqbd.query.builders.FieldBuilder;
import com.thdev.jqbd.query.builders.OrderBuilder;
import com.thdev.jqbd.query.builders.OrderType;
import com.thdev.jqbd.query.condition.CObj;
import com.thdev.jqbd.query.condition.ConditionMap;
import com.thdev.jqbd.query.condition.ConditionType;
import com.thdev.jqbd.query.response.DatabaseResponse;
import com.thdev.jqbd.query.table.Table;

public class Select extends BaseQuery implements Gettable {

	public Select(Database database) { super(database); }
	public Select(Database database, Table table) { super(database, table); }

	
	private FieldBuilder fieldBuilder = new FieldBuilder();

	public Select field(String... fields) { fieldBuilder.setFields(fields); return this; }
	
	private ConditionBuilder conditionBuilder = new ConditionBuilder();
	
	public Select where(String column, Object value) { return condition(ConditionType.WHERE, column, value); }
	public Select where(CObj... conditions) { return condition(ConditionType.WHERE, conditions); }
	public Select where(ConditionMap conditions) { return condition(ConditionType.WHERE, conditions); }
	
	public Select whereNot(String column, Object value) { return condition(ConditionType.WHERE_NOT, column, value); }
	public Select whereNot(CObj... conditions) { return condition(ConditionType.WHERE_NOT, conditions); }
	public Select whereNot(ConditionMap conditions) { return condition(ConditionType.WHERE_NOT, conditions); }
	
	public Select or(String column, Object value) { return condition(ConditionType.OR, column, value); }
	public Select or(CObj... conditions) { return condition(ConditionType.OR, conditions); }
	public Select or(ConditionMap conditions) { return condition(ConditionType.OR, conditions); }
	
	public Select orNot(String column, Object value) { return condition(ConditionType.OR_NOT, column, value); }
	public Select orNot(CObj... conditions) { return condition(ConditionType.OR_NOT, conditions); }
	public Select orNot(ConditionMap conditions) { return condition(ConditionType.OR_NOT, conditions); }

	public Select customCondition(String value) { conditionBuilder.add(ConditionType.CUSTOM, ConditionMap.convert(CObj.as(value, value))); return this; }
	
	public Select condition(ConditionType type, String column, Object value) { return condition(type, CObj.as(column, value)); }
	public Select condition(ConditionType type, CObj... conditions) { conditionBuilder.add(type, ConditionMap.convert(conditions)); return this; }
	public Select condition(ConditionType type, ConditionMap conditions) { conditionBuilder.add(type, conditions); return this; }

	
	private OrderBuilder orderBuilder = new OrderBuilder();
	
	public Select orderBy(String... column) { orderBuilder.add(column); return this; }
	public Select orderBy(String column, OrderType type) { orderBuilder.add(column, type); return this; }
	
	public DatabaseResponse getResponse() { return getDatabase().executeQuery(build()); }
	
	public String build() {
		String result = "SELECT"; 
		
		result += " " + fieldBuilder.build();
		result += " FROM " + getTable().getName();
		result += conditionBuilder.build();
		result += orderBuilder.build();
				
		result += ";";
		
		return result;
	}
	


	
}
