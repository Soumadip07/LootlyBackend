package LootlyBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import LootlyBackend.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
