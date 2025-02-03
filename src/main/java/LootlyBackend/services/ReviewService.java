package LootlyBackend.services;

import LootlyBackend.payloads.ReviewDto;

public interface ReviewService {
	
	ReviewDto createReview(ReviewDto reviewDto,Integer productId);
	
	void deleteReview(Integer reviewId);
}
