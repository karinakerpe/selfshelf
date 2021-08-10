package com.example.mysql;

import com.example.mysql.model.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import com.example.mysql.repository.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private UserRepository userRepo;




	@Test
	public void testCreateUser() {
		User user = new User();
		user.setEmail("sveta@gmail.com");
		user.setPassword("sveta2020");
		user.setFirstName("Sveta");
		user.setLastName("Tret");

		User savedUser = userRepo.save(user);

		User existUser = entityManager.find(User.class, savedUser.getId());

		assertThat(user.getEmail()).isEqualTo(existUser.getEmail());

	}

	@Test
	public void testFindUserByEmail() {
		String email = "star@inbox.lv";

		User user = userRepo.findByEmail(email);

		assertThat(user).isNotNull();
	}



}
