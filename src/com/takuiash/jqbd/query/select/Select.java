package com.takuiash.jqbd.query.select;

import java.sql.SQLException;
import java.util.List;

import com.takuiash.jqbd.orm.DatabaseEntity;
import com.takuiash.jqbd.query.helpers.column.COBJ;
import com.takuiash.jqbd.query.helpers.condition.Condition;
import com.takuiash.jqbd.query.helpers.map.maps.ConditionMap;
import com.takuiash.jqbd.worker.search.SearchResponse;
import com.takuiash.jqbd.worker.search.generic.SelectData;

/**
 * @author THDev
 */
public interface Select<T> {
	
	/**
	 * TODO Add fields to query.
	 * 
	 * @param fields
	 * @return {@link Select}
	 */
	public Select<T> fields(String... fields);
	
	/**
	 * TODO Add 'where' argument.
	 * 
	 * @param expression
	 * @return {@link Select}
	 */
	public Select<T> where(String expression);
	/**
	 * TODO Add 'where' argument.
	 * 
	 * @param column
	 * @param value
	 * @return {@link Select}
	 */
	public Select<T> where(String column, Object value);
	/**
	 * TODO Add 'where' argument.
	 * 
	 * @param columnObjects
	 * @return {@link Select}
	 */
	public Select<T> where(COBJ... columnObjects);
	/**
	 * TODO Add 'where' argument.
	 * 
	 * @param condition
	 * @return {@link Select}
	 */
	public Select<T> where(Condition condition);
	
	/**
	 * TODO Add 'or' argument.
	 * 
	 * @param expression
	 * @return {@link Select}
	 */
	public Select<T> or(String expression);
	/**
	 * TODO Add 'or' argument.
	 * 
	 * @param column
	 * @param value
	 * @return {@link Select}
	 */
	public Select<T> or(String column, Object value);
	/**
	 * TODO Add 'or' argument.
	 * 
	 * @param columnObjects
	 * @return {@link Select}
	 */
	public Select<T> or(COBJ... columnObjects);
	/**
	 * TODO Add 'or' argument.
	 * 
	 * @param condition
	 * @return {@link Select}
	 */
	public Select<T> or(Condition condition);
	
	/**
	 * TODO Add 'where not' argument.
	 * 
	 * @param expression
	 * @return {@link Select}
	 */
	public Select<T> whereNot(String expression);
	/**
	 * TODO Add 'where not' argument.
	 * 
	 * @param column
	 * @param value
	 * @return {@link Select}
	 */
	public Select<T> whereNot(String column, Object value);
	/**
	 * TODO Add 'where not' argument.
	 * 
	 * @param columnObjects
	 * @return {@link Select}
	 */
	public Select<T> whereNot(COBJ... columnObjects);
	/**
	 * TODO Add 'where not' argument.
	 * 
	 * @param condition
	 * @return {@link Select}
	 */
	public Select<T> whereNot(Condition condition);
	
	/**
	 * TODO Add 'or not' argument.
	 * 
	 * @param expression
	 * @return {@link Select}
	 */
	public Select<T> orNot(String expression);
	/**
	 * TODO Add 'or not' argument.
	 * 
	 * @param column
	 * @param value
	 * @return {@link Select}
	 */
	public Select<T> orNot(String column, Object value);
	/**
	 * TODO Add 'or not' argument.
	 * 
	 * @param columnObjects
	 * @return {@link Select}
	 */
	public Select<T> orNot(COBJ... columnObjects);
	/**
	 * TODO Add 'or not' argument.
	 * 
	 * @param condition
	 * @return {@link Select}
	 */
	public Select<T> orNot(Condition condition);

	/**
	 * TODO Set the condition map
	 * 
	 * @param conditions
	 * @return {@link Select}
	 */
	public Select<T> conditions(ConditionMap conditions);

	
	/**
	 * TODO Set the limit.
	 * 
	 * @param value
	 * @return {@link Select}
	 */
	public Select<T> limit(int value);
	/**
	 * TODO Set the offset.
	 * 
	 * @param value
	 * @return {@link Select}
	 */
	public Select<T> offset(int value);
	
	
	/**
	 * TODO Search the select query
	 * 
	 * @return {@link SearchResponse}
	 * @throws SQLException
	 */
	public SearchResponse<T> search() throws SQLException;
	
	
	/**
	 * TODO Return all response.
	 * 
	 * @return {@link SelectData} or {@link DatabaseEntity}
	 * @throws SQLException
	 */
	public List<T> all() throws SQLException;

	/**
	 * TODO Return first response.
	 * 
	 * @return {@link SelectData} or {@link DatabaseEntity}
	 * @throws SQLException
	 */
	public T first() throws SQLException;

	/**
	 * TODO Return last response.
	 * 
	 * @return {@link SelectData} or {@link DatabaseEntity}
	 * @throws SQLException
	 */
	public T last() throws SQLException;
}
