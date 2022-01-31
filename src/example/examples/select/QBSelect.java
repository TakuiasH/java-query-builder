package example.examples.select;

import java.sql.SQLException;

import com.takuiash.orm.jdbc.worker.search.generic.SelectData;
import com.takuiash.orm.table.Table;

public class QBSelect {

	public static void execute(Table table) throws SQLException {
		System.out.println("Executing select example with QueryBuilder style \n");
		SelectData response = table.select().where("username", "ArantesXYZ").first();

		System.out.println(response.get("id") + " : " + response.get("username") + " : " + response.get("locale") + "\n");
	}
	
}
