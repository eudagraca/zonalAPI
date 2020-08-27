package mz.co.zonal.controllers;

import mz.co.zonal.models.Brand;
import mz.co.zonal.service.BrandService;
import mz.co.zonal.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@RestController
@RequestMapping("/rest/v01/brands/")
public class BrandController {

    @Autowired
    private BrandService service;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("admin/create")
    private ModelAndView form() {
        ModelAndView mav = new ModelAndView("brand/create");
        mav.addObject("brands", service.allBrands());
        mav.addObject("categories", categoryService.allCategories());
        return mav;
    }

    @GetMapping
    private ResponseEntity<ArrayList<Brand>> allBrands(){
        return ResponseEntity.status(HttpStatus.OK).body(new ArrayList<>(service.allBrands()));
    }

    @PostMapping(value = "admin/save")
    @ResponseBody
    private Brand save(@RequestParam("name") String name) {
        var brand = new Brand();
        brand.setName(name);
        return service.save(brand);
    }
}
