package LootlyBackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import LootlyBackend.entities.Category;
import LootlyBackend.entities.Product;
import LootlyBackend.entities.User;


public interface ProductRepo extends JpaRepository<Product, Integer>{
    boolean existsByProductSlug(String productSlug);

	List<Product> findByUser(User user); 
	List<Product> findByCategory(Category category); 
	
	List<Product> findByTitleContaining(String title);
	Optional<Product> findByProductSlug(String productSlug);
}
