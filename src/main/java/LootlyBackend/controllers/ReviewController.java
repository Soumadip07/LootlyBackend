package LootlyBackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import LootlyBackend.payloads.ApiResponse;
import LootlyBackend.payloads.ReviewDto;
import LootlyBackend.services.ReviewService;

@RestController
@RequestMapping("/api/")
public class ReviewController {
	
	@Autowired
	private ReviewService reviewService;
	
	@PostMapping("/products/{productId}/reviews")
	public ResponseEntity<ReviewDto> createReview(@RequestBody ReviewDto reviewDto,
			@PathVariable Integer productId
			){
		
		ReviewDto createReview=this.reviewService.createReview(reviewDto, productId);
		
		return new ResponseEntity<ReviewDto>(createReview,HttpStatus.OK);
	}
	
	@DeleteMapping("/reviews/{reviewId}")
	public ResponseEntity<ApiResponse> deleteReview(@PathVariable Integer reviewId){
		
		this.reviewService.deleteReview(reviewId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Deleted Review successfully",true),HttpStatus.OK);
	}
}
