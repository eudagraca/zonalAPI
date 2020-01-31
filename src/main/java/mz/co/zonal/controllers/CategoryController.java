package mz.co.zonal.controllers;

import mz.co.zonal.models.Category;
import mz.co.zonal.service.CategoryService;
import mz.co.zonal.utils.Disk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequestMapping("/rest/v01/categories/")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping
    private ArrayList<Category> allCategories() {
        return categoryService.allCategories();
    }

    @GetMapping(value = "admin/create")
    private ModelAndView formCategory() {
        return new ModelAndView("category/createCategory");
    }

    @PostMapping(value = "admin/save")
    private ModelAndView saveCategory(@RequestParam("name") String name,
                                      @RequestParam("imagePath") MultipartFile file) throws IOException {
        var category = new Category();
        if (!name.isEmpty() || file.getOriginalFilename() != null) {
            category.setName(name);
            var disk = new Disk("category");
            category.setImagePath(disk.salvar(file));
            try {
                categoryService.saveCategory(category);
                ModelAndView modelAndView =new ModelAndView();
                modelAndView.addObject("category", category);
                return new ModelAndView("category/createCategory");
            }catch (Exception e){
                return new ModelAndView("category/createCategory");
            }

        } else {
            return new ModelAndView("category/createCategory");
        }
    }

}
