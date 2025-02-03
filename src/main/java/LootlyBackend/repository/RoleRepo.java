package LootlyBackend.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import LootlyBackend.entities.Role;

public interface RoleRepo extends JpaRepository<Role,Integer>{

	
}
