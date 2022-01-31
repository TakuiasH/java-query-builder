package com.takuiash.jqbd.worker.search.generic;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.takuiash.jqbd.worker.ReturningWork;
import com.takuiash.jqbd.worker.search.SearchResponse;

public class GenericSearchWorker implements ReturningWork<SelectData> {	
	
	public SearchResponse<SelectData> execute(String query, Connection connection) {
		try {
			List<SelectData> results = new ArrayList<SelectData>();
			
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(query);
		
			while (rs.next()) {
				 Map<String, Object> values = new HashMap<String, Object>();
				
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					values.put(rs.getMetaData().getColumnLabel(i), rs.getObject(i));
				}
				
				results.add(new SelectData(values));
			}
			
			st.close();
			
			return new GenericSearchResponse(results);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
