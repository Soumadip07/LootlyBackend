package LootlyBackend.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import LootlyBackend.entities.Product;
import LootlyBackend.entities.ProductVariant;
import LootlyBackend.exceptions.ResourceNotFoundException;
import LootlyBackend.payloads.ProductVariantDto;
import LootlyBackend.repository.ProductRepo;
import LootlyBackend.repository.ProductVariantRepo;
import LootlyBackend.repository.UserRepo;
import LootlyBackend.services.ProductVariantService;
@Service
public class ProductVariantServiceImpl implements ProductVariantService {
	 @Autowired
	    private ProductVariantRepo productVariantRepo;

	    @Autowired
	    private ProductRepo productRepo;

	    @Autowired
	    private ModelMapper modelMapper;  

	    @Autowired
	    private UserRepo userRepo;
	 
	@Override
	public ProductVariantDto createProductVariant(Integer productId, ProductVariantDto productVariantDto) {
		 Product product = productRepo.findById(productId)
	                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
		 ProductVariant variant = modelMapper.map(productVariantDto, ProductVariant.class);
	     variant.setProduct(product);
	     variant.setImageName(productVariantDto.getImageName());

	     ProductVariant savedVariant = productVariantRepo.save(variant);

	     return modelMapper.map(savedVariant, ProductVariantDto.class);
	}
	

	@Override
	public ProductVariantDto updateProductVariant(Integer productId,Integer variantId, ProductVariantDto productVariantDto) {
		ProductVariant variant=this.productVariantRepo.findById(variantId).orElseThrow(()-> new ResourceNotFoundException("ProductVariant","ProductVariant id",variantId));
		variant.setPrice(productVariantDto.getPrice());
		variant.setStock(productVariantDto.getStock());
	    variant.setColor(productVariantDto.getColor());
	    variant.setSize(productVariantDto.getSize());
	    ProductVariant updatedVariant = productVariantRepo.save(variant);
		
	    return modelMapper.map(updatedVariant, ProductVariantDto.class);
	}

	@Override
	public ProductVariantDto getProductVariantById(Integer productVariantId) {
	    ProductVariant productVariant = productVariantRepo.findById(productVariantId)
	            .orElseThrow(() -> new ResourceNotFoundException("ProductVariant", "productVariantId", productVariantId));

	    return modelMapper.map(productVariant, ProductVariantDto.class);
	}

	@Override
	public List<ProductVariantDto> getProductVariantsByProductId(Integer productId) {
	    List<ProductVariant> variants = productVariantRepo.findByProduct_ProductId(productId);

	    if (variants.isEmpty()) {
	        throw new ResourceNotFoundException("ProductVariant", "productId", productId);
	    }

	    return variants.stream()
	            .map(variant -> modelMapper.map(variant, ProductVariantDto.class))
	            .collect(Collectors.toList());
	}


	@Override
	public void deleteProductVariant(Integer productVariantId) {
		
		ProductVariant variant=this.productVariantRepo.findById(productVariantId).orElseThrow(()-> new ResourceNotFoundException("ProductVariant","ProductVariant id",productVariantId));
		this.productVariantRepo.delete(variant);
	}


	@Override
	public ProductVariantDto updateProductVariantImage(Integer productVariantId, ProductVariantDto productVariantDto) {
	    ProductVariant variant = this.productVariantRepo.findById(productVariantId)
	            .orElseThrow(() -> new ResourceNotFoundException("ProductVariant", "ProductVariant id", productVariantId));

	    // Set the new image name
	    variant.setImageName(productVariantDto.getImageName());

	    // Save updated variant
	    ProductVariant updatedVariant = productVariantRepo.save(variant);

	    // Return the updated variant as DTO
	    return modelMapper.map(updatedVariant, ProductVariantDto.class);
	}

}
