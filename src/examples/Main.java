package examples;

import java.io.File;

import com.thdev.jqbd.Database;
import com.thdev.jqbd.query.set.SET;
import com.thdev.jqbd.query.var.VarArgument;
import com.thdev.jqbd.query.var.VarType;
import com.thdev.jqbd.query.var.Variable;

public class Main {

	static Database db = new Database(new File("test.db"));
	
	public static void main(String[] args) {
		db.table("Test_Table").create()
		.add(new Variable("id", VarType.INTEGER, VarArgument.PRIMARY))
		.add(new Variable("username", 32, VarType.STRING, VarArgument.UNIQUE))
		.add(new Variable("password", 255, VarType.STRING))
		.add(new Variable("locale", 7, VarType.STRING, VarArgument.DEFAULT.add("en")))
		.execute();
		
		db.table("Test_Table").insert(SET.as("username", "TakuiasH"), SET.as("password", "1234")).execute();
		db.table("Test_Table").insert().set("username", "ArantesXYZ").set("password", "1234").execute();
		
		//db.table("Test_Table").update("locale", "pt").where("username", "TakuiasH").or("id", 1).execute();
		
		//db.table("Test_Table").delete().whereNot("username", "TakuiasH").execute();
		
		//db.table("Test_Table").select().orderBy("id").all();
		String username = db.table("Test_Table").select("username").where("id", 1).first().getString("username");
		
		System.out.println(username);
		
		//db.table("Test_Table").truncate().execute();
		//db.table("Test_Table").drop().execute();
	}

}
