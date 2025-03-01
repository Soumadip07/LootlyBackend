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

import LootlyBackend.payloads.ApiResponse;
import LootlyBackend.payloads.CategoryDto;
import LootlyBackend.payloads.ProductDto;
import LootlyBackend.services.CategoryService;
import LootlyBackend.services.FileService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	
	//create
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto cateogDto)
	{
		CategoryDto createCategory=this.categoryService.createCategory(cateogDto);
		return new ResponseEntity<CategoryDto>(createCategory, HttpStatus.CREATED);
	}
	
	
	//update
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,
			@PathVariable Integer catId) {
		CategoryDto updatedCategory = this.categoryService.updateCategory(categoryDto, catId);
		return new ResponseEntity<CategoryDto>(updatedCategory, HttpStatus.OK);
	}
	
	
	//delete
	@DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer catId) {
		this.categoryService.deleteCategory(catId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("category is deleted successfully !!", true),
				HttpStatus.OK);
	}
	
	
	//get
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer catId) {

		CategoryDto categoryDto = this.categoryService.getCategory(catId);

		return new ResponseEntity<CategoryDto>(categoryDto, HttpStatus.OK);

	}
	
	 
	//getAll
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getCategories() {
		List<CategoryDto> categories = this.categoryService.getCategories();
		return ResponseEntity.ok(categories);
	}
	
	// Upload Category Image
	@PostMapping("/image/upload/{categoryId}")
	public ResponseEntity<CategoryDto> uploadCategoryImage(
	        @PathVariable Integer categoryId,
	        @RequestParam("image") MultipartFile image) throws IOException {

	    String fileName = fileService.uploadImage(path, image);

	    CategoryDto category = categoryService.getCategory(categoryId);
	    category.setImageName(fileName);

	    CategoryDto updatedCategory = categoryService.updateCategory(category, categoryId);

	    return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
	}


	// Serve Category Image
	@GetMapping(value = "/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadCategoryImage(
	        @PathVariable("imageName") String imageName,
	        HttpServletResponse response
	) throws IOException {
	    // Fetch image as InputStream
	    InputStream resource = this.fileService.getResource(path, imageName);
	    
	    // Set content type as JPEG
	    response.setContentType(MediaType.IMAGE_JPEG_VALUE);
	    
	    // Copy image data to response output stream
	    StreamUtils.copy(resource, response.getOutputStream());
	}

}
