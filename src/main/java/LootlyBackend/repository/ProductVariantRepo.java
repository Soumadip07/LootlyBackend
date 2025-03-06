package LootlyBackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import LootlyBackend.entities.ProductVariant;

public interface ProductVariantRepo extends JpaRepository<ProductVariant, Integer> {
	
    List<ProductVariant> findByProduct_ProductId(Integer productId);

}
