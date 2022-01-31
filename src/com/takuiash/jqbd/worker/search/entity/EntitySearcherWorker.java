package com.takuiash.jqbd.worker.search.entity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.takuiash.jqbd.orm.EntityConverter;
import com.takuiash.jqbd.orm.EntityConverter.MethodType;
import com.takuiash.jqbd.worker.ReturningWork;

public class EntitySearcherWorker<T> implements ReturningWork<T> {

	private final T entityBase;
	
	public EntitySearcherWorker(T entityBase) {
		this.entityBase = entityBase;
	}
	
	@Override
	public EntitySearchResponse<T> execute(String query, Connection connection) {
		try {
			List<T> results = new ArrayList<T>();
			
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(query);
		
			while (rs.next()) {
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					Method method = EntityConverter.getMethod(entityBase, MethodType.SET, rs.getMetaData().getColumnLabel(i));
					
					if(method == null) continue;
					
					try {
						method.invoke(entityBase, rs.getObject(i));
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						e.printStackTrace();
					}
				}
				results.add(entityBase);	
			}
			st.close();
			
			return new EntitySearchResponse<T>(results);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
