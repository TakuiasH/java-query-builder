package example.examples.update;

import java.sql.SQLException;

import com.takuiash.jqbd.jdbc.query.helpers.column.COBJ;
import com.takuiash.jqbd.jdbc.worker.search.generic.SelectData;
import com.takuiash.jqbd.table.Table;

public class QBUpdate {

	public static void execute(Table table) throws SQLException {
		System.out.println("Executing update example with QueryBuilder style \n");

		SelectData response = table.update(COBJ.as("locale", "pt")).where("username", "TakuiasH").executeAndSelect();
		
		System.out.println(response.get("id") + " : " + response.get("username") + " : " + response.get("locale") + "\n");

	}
	
}
