package LootlyBackend.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import LootlyBackend.config.AppConstants;
import LootlyBackend.entities.Product;
import LootlyBackend.payloads.ApiResponse;
import LootlyBackend.payloads.ProductDto;
import LootlyBackend.payloads.ProductResponse;
import LootlyBackend.services.FileService;
import LootlyBackend.services.ProductService;
import jakarta.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/api/")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private FileService fileService;
	
	
	@Value("${project.image}")
	private String path;
	
	
	//create
	@PostMapping("/user/{userId}/category/{categoryId}/products")
	public ResponseEntity<ProductDto> createProduct(
			@RequestBody ProductDto productDto, 
			@PathVariable Integer userId,
			@PathVariable Integer categoryId)
	{
		ProductDto createProduct=this.productService.createProduct(productDto, userId, categoryId);
		return new ResponseEntity<ProductDto>(createProduct,HttpStatus.CREATED);
	}
	
	
	//get products by user
	@GetMapping("/user/{userId}/products")
	public ResponseEntity<List<ProductDto>> getProductsByUser(@PathVariable Integer userId){
		List<ProductDto> products=this.productService.getProductsByUser(userId);
		
		return new ResponseEntity<List<ProductDto>>(products,HttpStatus.OK);
	}
	
	
	//get products by category
	@GetMapping("/category/{categoryId}/products")
	public ResponseEntity<List<ProductDto>> getCategoryByUser(@PathVariable Integer categoryId){
		List<ProductDto> products=this.productService.getProductsByCategory(categoryId);
		
		return new ResponseEntity<List<ProductDto>>(products,HttpStatus.OK);
	}
	
	
	//get all products
	@GetMapping("/products")
	public ResponseEntity<ProductResponse> getAllProducts(@RequestParam(value="pageNumber",defaultValue=AppConstants.PAGE_NUMBER,required=false)Integer pageNumber,
			@RequestParam(value="pageSize",defaultValue=AppConstants.PAGE_SIZE,required=false)Integer pageSize,
			@RequestParam(value="sortBy",defaultValue=AppConstants.SORT_BY,required=false) String sortBy,
			@RequestParam(value="sortDir",defaultValue=AppConstants.SORT_DIR,required=false) String sortDir
			)
	{
		ProductResponse allProduct = this.productService.getAllProduct(pageNumber,pageSize,sortBy,sortDir);
		
		
		return new ResponseEntity<ProductResponse>(allProduct,HttpStatus.OK);
	}
	
	//get products by id
	@GetMapping("/products/{productId}")
	public ResponseEntity<ProductDto> getProductById(@PathVariable Integer productId){
		ProductDto productDto= this.productService.getProductById(productId);
		return new ResponseEntity<ProductDto>(productDto,HttpStatus.OK);
	}
	@GetMapping("/products/slug/{productSlug}")
	public ResponseEntity<ProductDto> getProductBySlug(@PathVariable("productSlug") String productSlug) {
	    try {
	        ProductDto productDto = productService.getProductBySlug(productSlug);
	        return new ResponseEntity<>(productDto, HttpStatus.OK);
	    } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	//delete product
	@DeleteMapping("/products/{productId}")
	public ApiResponse deleteProduct(@PathVariable Integer productId) {
		this.productService.deleteProduct(productId);
		return new ApiResponse("Product successfully deleted!!",true);
	}
	
	//update product
	@PutMapping("/products/{productId}")
	public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto,@PathVariable Integer productId){
		
		ProductDto updateProduct=this.productService.updateProduct(productDto, productId);
		return new ResponseEntity<ProductDto>(updateProduct,HttpStatus.OK);

}
	
	
	//search
	@GetMapping("/products/search/{keywords}")
	public ResponseEntity<List<ProductDto>> searchProductByTitle(@PathVariable("keywords") String keywords){
		List<ProductDto> result=this.productService.searchProducts(keywords);
		return new ResponseEntity<List<ProductDto>>(result,HttpStatus.OK);
	}
	
	//product image upload
	@PostMapping("/products/image/upload/{productId}")
	public ResponseEntity<ProductDto> uploadProductImage(
			@RequestParam("image") MultipartFile image,
			@PathVariable Integer productId
			) throws IOException{
		ProductDto productDto=this.productService.getProductById(productId);
		
		String fileName=this.fileService.uploadImage(path, image);
		productDto.setImageName(fileName);
		ProductDto updatedProduct=this.productService.updateProduct(productDto, productId);
		return new ResponseEntity<ProductDto>(updatedProduct, HttpStatus.OK);
	}
	
	
	 //method to serve files
    @GetMapping(value = "/products/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(
            @PathVariable("imageName") String imageName,
            HttpServletResponse response
    ) throws IOException {

        InputStream resource = this.fileService.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream())   ;

    }
}
