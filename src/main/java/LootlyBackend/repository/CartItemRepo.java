package LootlyBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import LootlyBackend.entities.CartItem;

public interface CartItemRepo extends JpaRepository<CartItem, Integer> {

}
