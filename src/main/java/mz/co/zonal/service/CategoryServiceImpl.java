package mz.co.zonal.service;

import mz.co.zonal.models.Category;

import java.util.ArrayList;

public interface CategoryServiceImpl {
    Category save(Category category);
    ArrayList<Category> allCategories();
    Category findCategory(Long id);
    boolean deleteCategory(Long id);
    Category update(Category category);


}
