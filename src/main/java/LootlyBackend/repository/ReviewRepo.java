package LootlyBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import LootlyBackend.entities.Product;
import LootlyBackend.entities.Review;

public interface ReviewRepo extends JpaRepository<Review, Integer>{

}
