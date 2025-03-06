package LootlyBackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import LootlyBackend.entities.ShippingAdress;
import LootlyBackend.entities.User;

public interface ShippingAddressRepo extends JpaRepository<ShippingAdress, Integer> {

    Optional<ShippingAdress> findByUser_Id(Integer userId);

}