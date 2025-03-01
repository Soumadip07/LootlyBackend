package LootlyBackend.services.impl;

import java.util.List;
import java.util.stream.Collectors;
import LootlyBackend.utils.GenerateSlug;

import LootlyBackend.entities.Category;
import LootlyBackend.exceptions.ResourceNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import LootlyBackend.payloads.CategoryDto;
import LootlyBackend.repository.CategoryRepo;
import LootlyBackend.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		
		Category cat=this.modelMapper.map(categoryDto, Category.class);
		
		cat.setImageName("default.png");
	    // Generate Slug
	    String slug = GenerateSlug.generateSlug(categoryDto.getCategoryTitle());

	    // Ensure Uniqueness
	    int count = 1;
	    String originalSlug = slug;
	    while (categoryRepo.existsByCategorySlug(slug)) {
	        slug = originalSlug + "-" + count;
	        count++;
	    }

	    cat.setCategorySlug(slug);
	    
	    
		Category addedCat=this.categoryRepo.save(cat);
		return this.modelMapper.map(addedCat, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		
		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category ", "Category Id", categoryId));
		
		cat.setCategoryTitle(categoryDto.getCategoryDescription());
		cat.setImageName(categoryDto.getImageName());

		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		
		Category updatedcat=this.categoryRepo.save(cat);
		
		return this.modelMapper.map(updatedcat, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		

		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category ", "category id", categoryId));
		this.categoryRepo.delete(cat);
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()-> 
		new ResourceNotFoundException("Catgeory","category id",categoryId));
		return this.modelMapper.map(cat,CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getCategories() {
		List<Category> categories = this.categoryRepo.findAll();
		List<CategoryDto> catDtos = categories.stream().map((cat) -> this.modelMapper.map(cat, CategoryDto.class))
				.collect(Collectors.toList());
		
		return catDtos;
	}

}
