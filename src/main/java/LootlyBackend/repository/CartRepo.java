package LootlyBackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import LootlyBackend.entities.Cart;
import LootlyBackend.entities.User;

public interface CartRepo extends JpaRepository<Cart, Integer> {
    Optional<Cart> findByUser_Id(Integer userId);  // <-- this is the correct query method
}
