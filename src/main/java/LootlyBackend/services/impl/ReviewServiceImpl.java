package LootlyBackend.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import LootlyBackend.entities.Product;
import LootlyBackend.entities.Review;
import LootlyBackend.exceptions.ResourceNotFoundException;
import LootlyBackend.payloads.ReviewDto;
import LootlyBackend.repository.ProductRepo;
import LootlyBackend.repository.ReviewRepo;
import LootlyBackend.services.ReviewService;


@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ProductRepo productRepo;
	@Autowired
	private ReviewRepo reviewRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public ReviewDto createReview(ReviewDto reviewDto, Integer productId) {
		
		Product product=this.productRepo.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Product","Product id",productId));;
		Review review=this.modelMapper.map(reviewDto, Review.class);
		
		review.setProduct(product);
		
		Review savedReview=this.reviewRepo.save(review);
		
		
		return this.modelMapper.map(savedReview, ReviewDto.class);
	}

	@Override
	public void deleteReview(Integer reviewId) {
		
		Review rev=this.reviewRepo.findById(reviewId).orElseThrow(()-> new ResourceNotFoundException("Review","Review id",reviewId));;
		this.reviewRepo.delete(rev);

	}

}
