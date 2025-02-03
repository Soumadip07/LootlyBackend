package LootlyBackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import LootlyBackend.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	
	
	Optional<User> findByEmail(String email);
}
