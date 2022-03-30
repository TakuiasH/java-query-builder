package example.examples.QB;

import com.takuiash.jqbd.query.Table;
import com.takuiash.jqbd.query.helpers.Column;
import com.takuiash.jqbd.worker.search.generic.SelectData;

public class QBUpdate {

	public static void execute(Table table) {
		System.out.println("Executing update example with QueryBuilder style \n");

		SelectData response = table.update(Column.as("locale", "pt")).where(Column.as("username", "TakuiasH")).executeAndSelect();
		
		System.out.println(response.get("id") + " : " + response.get("username") + " : " + response.get("locale") + "\n");

	}
	
}
