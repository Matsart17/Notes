package com.app.categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public void createCategory(Category category) {
        if (categoryRepository.findByName(category.getName()) == null)
            categoryRepository.save(category);
    }

    public void findCategory(Set<Category> categories) {
        categories.forEach(this::createCategory);
    }
}
