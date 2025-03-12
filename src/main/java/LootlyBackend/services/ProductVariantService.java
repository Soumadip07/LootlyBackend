package LootlyBackend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import LootlyBackend.payloads.ProductVariantDto;

@Service
public interface ProductVariantService {
	 ProductVariantDto createProductVariant(Integer productId, ProductVariantDto productVariantDto);

	 ProductVariantDto updateProductVariant(Integer productId,Integer variantId, ProductVariantDto productVariantDto);

	 ProductVariantDto getProductVariantById(Integer productVariantId);

	 List<ProductVariantDto> getProductVariantsByProductId(Integer productId);
	 public ProductVariantDto updateProductVariantImage(Integer productVariantId, ProductVariantDto productVariantDto);


	 void deleteProductVariant(Integer productVariantId);
}
 