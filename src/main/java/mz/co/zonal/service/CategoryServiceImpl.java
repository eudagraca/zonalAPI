package mz.co.zonal.service;

import mz.co.zonal.models.Category;

import java.util.ArrayList;
import java.util.Optional;

public interface CategoryServiceImpl {
    boolean saveCategory(Category category);
    ArrayList<Category> allCategories();
    Category findCategory(Long id);
    boolean deleteCategory(Long id);
    Category update(Category category);


}
