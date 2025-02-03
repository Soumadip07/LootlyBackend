package LootlyBackend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import LootlyBackend.entities.Product;
import LootlyBackend.payloads.ProductDto;
import LootlyBackend.payloads.ProductResponse;
@Service
public interface ProductService {
	
	
	//Create
	ProductDto createProduct(ProductDto productDto, Integer userId,  Integer CategoryId);
	
	//update
	ProductDto updateProduct(ProductDto productDto, Integer productId);
	
	
	//Delete
	void deleteProduct(Integer postId);
	
	
	//get all posts
	ProductResponse getAllProduct(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	
	
	//get Single product
	ProductDto getProductById(Integer productId);
	
	
	//get all product by category
	
	List<ProductDto> getProductsByCategory(Integer categoryId);
	
	
	//get all products by user
	List<ProductDto> getProductsByUser(Integer userId);
	
	//search products
	List<ProductDto> searchProducts(String keyword);
}
