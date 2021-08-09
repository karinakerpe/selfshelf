package com.example.mysql;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private RoleRepository roleRepo;


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
	@Test
	public void testAddRoleToNewUser() {
		Role roleAdmin = roleRepo.findByName("Admin");

		User user = new User();
		user.setEmail("eugeny.velkin@gmail.com");
		user.setPassword("velkin2020");
		user.setFirstName("Eugeny");
		user.setLastName("Velkin");
		user.addRole(roleAdmin);

		User savedUser = userRepo.save(user);

		assertThat(savedUser.getRoles().size()).isEqualTo(1);
	}
	@Test
	public  void testAddRolesToExistingUser() {
		User user = userRepo.findById(1L).get();
		Role roleUser = roleRepo.findByName("User");
		Role roleCustomer = new Role(3L);

		user.addRole(roleUser);
		user.addRole(roleCustomer);

		User savedUser = userRepo.save(user);

		assertThat(savedUser.getRoles().size()).isEqualTo(2);
	}

}
