package example.examples.ORM;

import java.sql.Connection;

import example.models.entities.UserEntity;
import example.models.repositories.UserRepository;

public class ORMDelete {

	public static void execute(Connection connection) {
		System.out.println("Executing delete example with ORM style \n");

		UserRepository repo = new UserRepository(connection);
		
		int rowsCount = repo.delete(new UserEntity("Freizao"));
		System.out.println("Freizao delete | Rows affected: " + rowsCount + "\n");
	}
	
}
