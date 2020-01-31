package mz.co.zonal.service;

import mz.co.zonal.models.Category;
import mz.co.zonal.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CategoryService implements CategoryServiceImpl {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public boolean saveCategory(Category category) {
        try {
            categoryRepository.save(category);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public ArrayList<Category> allCategories() {
        return new ArrayList<>(categoryRepository.findAll());
    }

    @Override
    public Category findCategory(Long id) {
        return null;
    }

    @Override
    public boolean deleteCategory(Long id) {
        return false;
    }

    @Override
    public Category update(Category category) {
        return null;
    }
}
