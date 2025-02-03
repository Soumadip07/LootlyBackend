package LootlyBackend.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import LootlyBackend.entities.Category;
import LootlyBackend.entities.Product;
import LootlyBackend.entities.User;
import LootlyBackend.exceptions.ResourceNotFoundException;
import LootlyBackend.payloads.ProductDto;
import LootlyBackend.payloads.ProductResponse;
import LootlyBackend.repository.CategoryRepo;
import LootlyBackend.repository.ProductRepo;
import LootlyBackend.repository.UserRepo;
import LootlyBackend.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public ProductDto  createProduct(ProductDto productDto,Integer userId, Integer categoryId) {
		
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","User id",userId));
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()
				-> new ResourceNotFoundException("Category","category id",categoryId));
		
		Product product=this.modelMapper.map(productDto, Product.class);
		product.setImageName("default.png");
		product.setAddedDate(new Date());
		product.setUser(user);
		product.setCategory(category);
		Product newProduct=this.productRepo.save(product);
		
		return this.modelMapper.map(newProduct, ProductDto.class);
	}

	@Override
	public ProductDto updateProduct(ProductDto productDto, Integer productId) {
		
		Product product=this.productRepo.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Product","product id",productId));
		
		product.setTitle(productDto.getTitle());
		product.setContent(productDto.getContent());
		product.setImageName(productDto.getImageName());
		
		Product updatedProduct=this.productRepo.save(product);
		
		return this.modelMapper.map(updatedProduct, ProductDto.class);
	}

	@Override
	public void deleteProduct(Integer productId) {
		Product product=this.productRepo.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Product","product id", productId));
		
		this.productRepo.delete(product);
	}

	@Override
	public ProductResponse getAllProduct(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
		Sort sort=null;
		if(sortDir.equalsIgnoreCase("ASC")) {
			sort=Sort.by(sortBy).ascending();
		}
		else {
			sort=Sort.by(sortBy).descending();
		}
		Pageable p=PageRequest.of(pageNumber, pageSize, sort);
		Page<Product> pageProduct=this.productRepo.findAll(p);
		List<Product> allProducts=pageProduct.getContent();
		List<ProductDto> productDtos=allProducts.stream().map((product)-> this.modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
		
		ProductResponse productResponse=new ProductResponse();
		
		productResponse.setContent(productDtos);
		productResponse.setPageNumber(pageProduct.getNumber());
		productResponse.setPageSize(pageProduct.getSize());
		productResponse.setTotalElements(pageProduct.getTotalElements());
		productResponse.setTotalPages(pageProduct.getTotalPages());
		productResponse.setLastPAge(pageProduct.isLast());
		
		return productResponse;
	}

	@Override
	public ProductDto getProductById(Integer productId) {
		
		Product product=this.productRepo.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Product","product id",productId));
		return this.modelMapper.map(product, ProductDto.class);
		
	}

	@Override
	public List<ProductDto> getProductsByCategory(Integer categoryId) {
		
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","category id",categoryId));
		List<Product> products=this.productRepo.findByCategory(cat);
		
		List<ProductDto> productDtos=products.stream().map((product)-> this.modelMapper.map(product,ProductDto.class)).collect(Collectors.toList());
		return productDtos;
	}

	@Override
	public List<ProductDto> getProductsByUser(Integer userId) {
		
		
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","userId",userId));
		List<Product> products=this.productRepo.findByUser(user);
		
		List<ProductDto> productDtos=products.stream().map((product)-> this.modelMapper.map(product,ProductDto.class)).collect(Collectors.toList());

		return productDtos;
	}

	@Override
	public List<ProductDto> searchProducts(String keyword) {
		List<Product> products=this.productRepo.findByTitleContaining(keyword);
		List<ProductDto> productDtos=products.stream().map((product)-> this.modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
		
		return productDtos;
	}

}
