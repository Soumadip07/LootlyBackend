package LootlyBackend.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
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
import LootlyBackend.payloads.ProductVariantDto;
import LootlyBackend.services.FileService;
import LootlyBackend.services.ProductVariantService;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;

@RestController
@RequestMapping("/api")
public class ProductVariantController {
	@Autowired
    private ProductVariantService productVariantService;
	
	@Autowired
    private FileService fileService;  // Assuming you already have this service

    @Value("${project.image}")
    private String path; 
    
    @PostMapping("/product/{productId}/product-variants")
    public ResponseEntity<ProductVariantDto> createProductVariant(
            @PathVariable Integer productId,
            @RequestBody ProductVariantDto productVariantDto) {
    	  ProductVariantDto createProductVariant = productVariantService.createProductVariant(productId, productVariantDto);
          return new ResponseEntity<ProductVariantDto>(createProductVariant, HttpStatus.CREATED);
          }
    @PutMapping("/product-variants/{productVariantId}")
	public ResponseEntity<ApiResponse> updateProductVariant(  @PathVariable Integer productId,@PathVariable Integer productVariantId,
	        @RequestBody ProductVariantDto productVariantDto){
        ProductVariantDto updatedVariant = productVariantService.updateProductVariant(productId, productVariantId, productVariantDto);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Product Variant Updated successfully",true),HttpStatus.OK);
    }
    
    @DeleteMapping("/product-variants/{productVariantId}")
	public ResponseEntity<ApiResponse> deleteProductVariant(@PathVariable Integer productVariantId){
    	this.productVariantService.deleteProductVariant(productVariantId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Deleted Product Variant successfully",true),HttpStatus.OK);
    }
    @GetMapping("/product-variants/product/{productId}")
    public ResponseEntity<List<ProductVariantDto>> getProductVariantsByProductId(@PathVariable Integer productId) {
        List<ProductVariantDto> productVariantDtos = productVariantService.getProductVariantsByProductId(productId);
        return new ResponseEntity<>(productVariantDtos, HttpStatus.OK);
    }
    
    @PostMapping("/product-variants/{productVariantId}/image")
    public ResponseEntity<ProductVariantDto> uploadProductVariantImage(
            @PathVariable Integer productVariantId,
            @RequestParam("imageName") MultipartFile imageName
    ) throws IOException {
        // Upload the image using fileService (you must already have this service)
        String fileName = fileService.uploadImage(path, imageName);

        // Set the image name in DTO
        ProductVariantDto productVariantDto = new ProductVariantDto();
        productVariantDto.setImageName(fileName);

        // Update the product variant's image in the database
        ProductVariantDto updatedVariant = productVariantService.updateProductVariantImage(productVariantId, productVariantDto);

        return new ResponseEntity<>(updatedVariant, HttpStatus.OK);
    }

//    http://localhost:8082/api/image/product1.jpg image serving url

    @GetMapping(value = "/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadProductVariantImage(
            @PathVariable("imageName") String imageName,
            HttpServletResponse response) throws IOException {

        // Fetch image as InputStream (from local path or cloud storage)
        InputStream resource = fileService.getResource(path, imageName);

        // Set content type to JPEG (adjust this based on the actual image type)
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);

        // Copy image data to response output stream
        StreamUtils.copy(resource, response.getOutputStream());
    }


}
