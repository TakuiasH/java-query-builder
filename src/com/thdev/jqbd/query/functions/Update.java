package com.thdev.jqbd.query.functions;

import com.thdev.jqbd.Database;
import com.thdev.jqbd.query.base.BaseQuery;
import com.thdev.jqbd.query.builders.ConditionBuilder;
import com.thdev.jqbd.query.builders.SetBuilder;
import com.thdev.jqbd.query.condition.CObj;
import com.thdev.jqbd.query.condition.ConditionMap;
import com.thdev.jqbd.query.condition.ConditionType;
import com.thdev.jqbd.query.set.SET;
import com.thdev.jqbd.query.set.SetMap;
import com.thdev.jqbd.query.table.Table;

public class Update extends BaseQuery {

	public Update(Database database) { super(database); }
	public Update(Database database, Table table) { super(database, table); }

	private SetBuilder setBuilder = new SetBuilder();
	
	public Update set(String column, Object value) { return set(SET.as(column, value)); }
	public Update set(SET... sets) { return set(SetMap.convert(sets)); }
	public Update set(SetMap sets) { setBuilder.set(sets); return this; }
	
	private ConditionBuilder conditionBuilder = new ConditionBuilder();

	public Update where(String column, Object value) { return condition(ConditionType.WHERE, column, value); }
	public Update where(CObj... conditions) { return condition(ConditionType.WHERE, conditions); }
	public Update where(ConditionMap conditions) { return condition(ConditionType.WHERE, conditions); }
	
	public Update whereNot(String column, Object value) { return condition(ConditionType.WHERE_NOT, column, value); }
	public Update whereNot(CObj... conditions) { return condition(ConditionType.WHERE_NOT, conditions); }
	public Update whereNot(ConditionMap conditions) { return condition(ConditionType.WHERE_NOT, conditions); }
	
	public Update or(String column, Object value) { return condition(ConditionType.OR, column, value); }
	public Update or(CObj... conditions) { return condition(ConditionType.OR, conditions); }
	public Update or(ConditionMap conditions) { return condition(ConditionType.OR, conditions); }
	
	public Update orNot(String column, Object value) { return condition(ConditionType.OR_NOT, column, value); }
	public Update orNot(CObj... conditions) { return condition(ConditionType.OR_NOT, conditions); }
	public Update orNot(ConditionMap conditions) { return condition(ConditionType.OR_NOT, conditions); }

	public Update condition(ConditionType type, String column, Object value) { return condition(type, CObj.as(column, value)); }
	public Update condition(ConditionType type, CObj... conditions) { conditionBuilder.add(type, ConditionMap.convert(conditions)); return this; }
	public Update condition(ConditionType type, ConditionMap conditions) { conditionBuilder.add(type, conditions); return this; }

	public void execute() {
		getDatabase().executeUpdate(build());
	}
	
	public String build() {
		String result = "UPDATE " + getTable().getName(); 
		
		result += setBuilder.build();
		result += conditionBuilder.build();
		
		result += ";";
		
		return result;
	}

	
}
