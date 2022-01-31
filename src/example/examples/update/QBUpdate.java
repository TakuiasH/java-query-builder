package example.examples.update;

import java.sql.SQLException;

import com.takuiash.orm.jdbc.query.helpers.column.COBJ;
import com.takuiash.orm.jdbc.worker.search.generic.SelectData;
import com.takuiash.orm.table.Table;

public class QBUpdate {

	public static void execute(Table table) throws SQLException {
		System.out.println("Executing update example with QueryBuilder style \n");

		SelectData response = table.update(COBJ.as("locale", "pt")).where("username", "TakuiasH").executeAndSelect();
		
		System.out.println(response.get("id") + " : " + response.get("username") + " : " + response.get("locale") + "\n");

	}
	
}
