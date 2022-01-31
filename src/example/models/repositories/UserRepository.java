package example.models.repositories;


import example.models.entities.UserEntity;

import java.sql.Connection;

import com.takuiash.jqbd.repository.AbstractRepository;
import com.takuiash.jqbd.table.Table;

public class UserRepository extends AbstractRepository<UserEntity> {

	public UserRepository(Connection connection) {
		super(new Table(connection, "Test_Table"), new UserEntity());
	}

}
