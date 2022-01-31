package example.examples.ORM;

import java.sql.Connection;

import example.models.entities.UserEntity;
import example.models.repositories.UserRepository;

public class ORMInsert {

	public static void execute(Connection connection) {
		System.out.println("Executing insert example with ORM style \n");
		UserRepository repo = new UserRepository(connection);

		UserEntity user1 = repo.insert(new UserEntity("TakuiasH")); //Return the entity with all arguments passed. (Primary keys, Defaults...)
		System.out.println("User 1: " + user1.getId() + " : " + user1.getUsername() + " : " + user1.getLocale() + "\n");
		
		UserEntity user2 = repo.insert(new UserEntity("ArantesXYZ", "pt"));
		System.out.println("User 2: " + user2.getId() + " : " + user2.getUsername() + " : " + user2.getLocale() + "\n");
	}
	
}
