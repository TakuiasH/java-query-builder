package example.examples.insert;

import java.sql.SQLException;

import com.takuiash.jqbd.query.Table;
import com.takuiash.jqbd.query.helpers.column.COBJ;
import com.takuiash.jqbd.worker.search.generic.SelectData;

public class QBInsert {
	
	public static void execute(Table table) throws SQLException {
		System.out.println("Executing insert example with QueryBuilder style \n");

		int rowsCount = table.insert().value("username", "Freizao").execute(); //Default locale defined in UserEntity.
		System.out.println("Freizao insertion | Rows affected: " + rowsCount + "\n");

		
		SelectData user2  = table.insert(COBJ.as("username", "KUMApt"), COBJ.as("locale", "pt")).executeAndSelect(); //Return the data with all arguments passed. (Primary keys, Defaults...)
		System.out.println("User 2: " + user2.get("id") + " : " + user2.get("username") + " : " + user2.get("locale") + "\n");
	}
	
}
