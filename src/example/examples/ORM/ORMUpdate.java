package example.examples.ORM;

import java.sql.Connection;

import example.models.entities.UserEntity;
import example.models.repositories.UserRepository;

public class ORMUpdate {
	
	public static void execute(Connection connection) {
		System.out.println("Executing update example with ORM style \n");

		UserRepository repo = new UserRepository(connection);

		UserEntity updateUser = new UserEntity();
		updateUser.setLocale("es");
		
		UserEntity user = repo.update(updateUser).where("username", "ArantesXYZ").executeAndSelect();
		System.out.println("User updated: " + user.getId() + " : " + user.getUsername() + " : " + user.getLocale() + "\n");

	}
	
}
