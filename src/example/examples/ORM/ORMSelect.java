package example.examples.ORM;

import java.sql.Connection;
import java.sql.SQLException;

import example.models.entities.UserEntity;
import example.models.repositories.UserRepository;

public class ORMSelect {

	public static void execute(Connection connection) throws SQLException {
		System.out.println("Executing select example with ORM style \n");
		UserRepository repo = new UserRepository(connection);
		
		UserEntity user = repo.select().where("username", "TakuiasH").first();
		System.out.println("User: " + user.getId() + " : " + user.getUsername() + " : " + user.getLocale() + "\n");
	}
	
}
