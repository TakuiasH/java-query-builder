package example;

import java.sql.Connection;
import java.sql.SQLException;

import com.takuiash.jqbd.jdbc.connector.Connector;
import com.takuiash.jqbd.table.Table;

import example.examples.delete.ORMDelete;
import example.examples.delete.QBDelete;
import example.examples.insert.ORMInsert;
import example.examples.insert.QBInsert;
import example.examples.select.ORMSelect;
import example.examples.select.QBSelect;
import example.examples.update.ORMUpdate;
import example.examples.update.QBUpdate;

public class Main {

	private static Connector connector = new Connector();
	
	public static void main(String[] args){
		
		try {
			Connection conn = connector.getConnection();
			Table table = connector.table("Test_Table");
			
			ORMInsert.execute(conn);
			QBInsert.execute(table);
			
			ORMSelect.execute(conn);
			QBSelect.execute(table);
			
			ORMDelete.execute(conn);
			QBDelete.execute(table);
			
			ORMUpdate.execute(conn);
			QBUpdate.execute(table);
			
			//table.truncate();
			//table.drop(); 
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
