package mz.co.zonal.controllers;

import mz.co.zonal.errors.ResourceNotFoundException;
import mz.co.zonal.models.Category;
import mz.co.zonal.service.BrandService;
import mz.co.zonal.service.CategoryService;
import mz.co.zonal.utils.Disk;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

@RestController
@RequestMapping("/rest/v01/categories/")
public class CategoryController {
    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BrandService brandService;

    @RequestMapping
    private ArrayList<Category> allCategories() {
        var categoriesList = categoryService.allCategories();

        var categories = new ArrayList<Category>();
        for (var category : categoriesList) {
            if (category.getImagePath() != null) {
                try {
                    String picPath = category.getImagePath();
                    Path path = Paths.get(picPath);
                    category.setCategoryImage(Files.readAllBytes(path));
                    categories.add(category);
                } catch (IOException e) {
                    throw new ResourceNotFoundException(e.getMessage());
                }
            }
        }
        return categories;
    }

    @GetMapping(value = "admin/create")
    private ModelAndView formCategory() {
        ModelAndView mav = new ModelAndView("category/createCategory");
        mav.addObject("categories", categoryService.allCategories());
        return mav;
    }


    @PostMapping(value = "admin/save")
    @ResponseBody
    private ModelAndView saveCategory(@RequestParam("name") String name,
                                      @RequestParam("imagePath") MultipartFile file) throws IOException {
        var category = new Category();

        if (!name.isEmpty() || file.getOriginalFilename() != null) {
            category.setName(name);
            var disk = new Disk("category");
            category.setImagePath(disk.saveImage(file));
            try {
                var categorySaved = categoryService.save(category);

                ModelAndView modelAndView = new ModelAndView();
                modelAndView.addObject("category", categoryService.allCategories());
                return new ModelAndView("category/createCategory");
            } catch (Exception e) {
                return new ModelAndView("category/createCategory");
            }

        } else {
            return new ModelAndView("category/createCategory");
        }
    }

}
