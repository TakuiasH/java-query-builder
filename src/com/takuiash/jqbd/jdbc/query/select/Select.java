package com.takuiash.jqbd.jdbc.query.select;

import java.sql.SQLException;
import java.util.List;

import com.takuiash.jqbd.jdbc.query.helpers.column.COBJ;
import com.takuiash.jqbd.jdbc.query.helpers.condition.Condition;
import com.takuiash.jqbd.jdbc.query.helpers.condition.ConditionMap;
import com.takuiash.jqbd.jdbc.worker.search.SearchResponse;

public interface Select<T> {
	
	public Select<T> fields(String... fields);
	
	public Select<T> where(String expression);
	public Select<T> where(String column, Object value);
	public Select<T> where(COBJ... columnObjects);
	public Select<T> where(Condition condition);

	public Select<T> or(String expression);
	public Select<T> or(String column, Object value);
	public Select<T> or(COBJ... columnObjects);
	public Select<T> or(Condition condition);
	
	public Select<T> whereNot(String expression);
	public Select<T> whereNot(String column, Object value);
	public Select<T> whereNot(COBJ... columnObjects);
	public Select<T> whereNot(Condition condition);
	
	public Select<T> orNot(String expression);
	public Select<T> orNot(String column, Object value);
	public Select<T> orNot(COBJ... columnObjects);
	public Select<T> orNot(Condition condition);
	
	public Select<T> conditions(ConditionMap conditions);

	
	public Select<T> limit(int value);
	public Select<T> offset(int value);
	
	public SearchResponse<T> search() throws SQLException;

	public List<T> all() throws SQLException;
	public T first() throws SQLException;
	public T last() throws SQLException;
}
