package example.examples.QB;

import com.takuiash.jqbd.query.Table;
import com.takuiash.jqbd.query.helpers.Column;
import com.takuiash.jqbd.worker.search.generic.SelectData;

public class QBInsert {
	
	public static void execute(Table table) {
		System.out.println("Executing insert example with QueryBuilder style \n");

		int rowsCount = table.insert().value("username", "Freizao").execute(); //Default locale defined in UserEntity.
		System.out.println("Freizao insertion | Rows affected: " + rowsCount + "\n");

		
		SelectData user2  = table.insert(Column.as("username", "KUMApt"), Column.as("locale", "pt")).executeAndSelect(); //Return the data with all arguments passed. (Primary keys, Defaults...)
		System.out.println("User 2: " + user2.get("id") + " : " + user2.get("username") + " : " + user2.get("locale") + "\n");
	}
	
}
