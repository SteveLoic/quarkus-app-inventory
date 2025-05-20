package org.stve.category;


import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.util.List;
import java.util.Optional;

@Dependent
public class CategoryService {

    @Inject
    ModelMapper modelMapper;
    @Inject
    CategoryRepository categoryRepository;

    @Transactional
    public CategoryResponse addNewCategory(CategoryRequest categoryRequest) {
        Optional<Category> existingCategory = categoryRepository.findByName(categoryRequest.getName());
        if (existingCategory.isPresent()) {
            throw new EntityExistsException("Category with name " + categoryRequest.getName() + "exists");
        }

        Category newCategory = Category.builder().name(categoryRequest.getName()).build();
        categoryRepository.persist(newCategory);
        return modelMapper.map(newCategory, CategoryResponse.class);
    }

    public List<CategoryResponse> getAllCategory() {
        List<Category> categories = categoryRepository.findAll().list();
        List<CategoryResponse> categoryResponses = modelMapper.map(categories, new TypeToken<List<CategoryResponse>>() {
        }.getType());
        return categoryResponses;
    }

    public CategoryResponse getCategoryByname(String name) {
        Category category = categoryRepository.findByName(name).orElseThrow(
                () -> new EntityNotFoundException(String.format("Category with %s not found", name))
        );
        return modelMapper.map(category, CategoryResponse.class);
    }


    @Transactional
    public CategoryResponse updateCategory(String categoryName, CategoryRequest categoryRequest) {
        Category existingCategory = categoryRepository.findByName(categoryName).orElseThrow(
                () -> new EntityNotFoundException(String.format("Category with name '%s' not found", categoryName))
        );
        existingCategory.setName(categoryRequest.getName());
        categoryRepository.persist(existingCategory);
        return modelMapper.map(existingCategory, CategoryResponse.class);
    }

    @Transactional
    public void deleteCategory(String categoryName) {
        categoryRepository.findByName(categoryName).ifPresentOrElse(
                existingsCategory -> {
                    categoryRepository.delete(existingsCategory);
                }, () -> {
                    new EntityNotFoundException(String.format("Category with name '%s' not found", categoryName));
                }
        );
    }
}
