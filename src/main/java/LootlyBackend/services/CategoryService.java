package LootlyBackend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import LootlyBackend.payloads.CategoryDto;
@Service

public interface CategoryService {

	
	//CREate
	CategoryDto createCategory(CategoryDto categoryDto);
	
	
	//Update
	CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);
	
	
	//Delete
	 void deleteCategory(Integer catgeoryId);
	
	//get
	CategoryDto getCategory(Integer categoryId);
	
	//get all
	List<CategoryDto> getCategories();

}
