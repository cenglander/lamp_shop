package lamp_shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import lamp_shop.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	public User findOneByName(String name);
	
}
