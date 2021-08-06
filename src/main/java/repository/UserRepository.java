package repository;

import model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	@Override
	public List<User> findAll();

	@Query("SELECT u FROM User u WHERE u.email = ?1")
	User findByEmail(String email);

}
