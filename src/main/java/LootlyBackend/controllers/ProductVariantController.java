package LootlyBackend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import LootlyBackend.payloads.ApiResponse;
import LootlyBackend.payloads.ProductVariantDto;
import LootlyBackend.services.ProductVariantService;

@RestController
@RequestMapping("/api")
public class ProductVariantController {
	@Autowired
    private ProductVariantService productVariantService;

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

}
