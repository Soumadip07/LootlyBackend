package LootlyBackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import LootlyBackend.entities.Profile;

@Repository
public interface ProfileRepo extends JpaRepository<Profile, Long>{
	
	Optional<Profile> findByUserId(Long userId);


}
