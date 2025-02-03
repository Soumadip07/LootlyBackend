package LootlyBackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import LootlyBackend.entities.Category;
import LootlyBackend.entities.Product;
import LootlyBackend.entities.User;


public interface ProductRepo extends JpaRepository<Product, Integer>{
	
	List<Product> findByUser(User user); 
	List<Product> findByCategory(Category category); 
	
	List<Product> findByTitleContaining(String title);
}
