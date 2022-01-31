package example.examples.select;

import java.sql.SQLException;

import com.takuiash.jqbd.query.Table;
import com.takuiash.jqbd.worker.search.generic.SelectData;

public class QBSelect {

	public static void execute(Table table) throws SQLException {
		System.out.println("Executing select example with QueryBuilder style \n");
		SelectData response = table.select().where("username", "ArantesXYZ").first();

		System.out.println(response.get("id") + " : " + response.get("username") + " : " + response.get("locale") + "\n");
	}
	
}
