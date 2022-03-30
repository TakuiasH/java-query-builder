package example;


import com.takuiash.jqbd.connector.Connector;
import com.takuiash.jqbd.query.Table;

import example.examples.ORM.ORMDelete;
import example.examples.ORM.ORMInsert;
import example.examples.ORM.ORMSelect;
import example.examples.ORM.ORMUpdate;
import example.examples.QB.QBDelete;
import example.examples.QB.QBInsert;
import example.examples.QB.QBSelect;
import example.examples.QB.QBUpdate;

public class Main {

	private static Connector connector = new Connector("test.db");
	
	public static void main(String[] args){
		
		Table table = connector.table("Test_Table");
			
		/*TableSetup setup = new TableSetup("users");
		
		setup.addRow(new Row("id", FieldType.INT).addOptions(Option.PRIMARY, Option.AUTO_INCREMENT));
		setup.addRow(new Row("username", FieldType.VARCHAR).addOptions(Option.UNIQUE));
		setup.addRow(new Row("locale", FieldType.VARCHAR).setDefault("en"));		
		
		table.create(connector.getConnectionType(), setup);*/
			
		
		QBInsert.execute(table);
		QBSelect.execute(table);
		QBDelete.execute(table);
		QBUpdate.execute(table);

		
		ORMInsert.execute(connector);
		ORMSelect.execute(connector);
		ORMDelete.execute(connector);
		ORMUpdate.execute(connector);
			
		//table.truncate();
		//table.drop(); 

	}
}
