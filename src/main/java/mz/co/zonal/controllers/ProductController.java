package mz.co.zonal.controllers;

import mz.co.zonal.models.Product;
import mz.co.zonal.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
@RequestMapping("/rest/v01/product/")
public class ProductController  {
    @Autowired
    private ProductService service;

    @GetMapping(value = "all")
    private ArrayList<Product> allProducts(){
        return service.allProducts();
    }

    @PostMapping(value = "new")
    private Product save(Product product){
        return service.save(product);
    }

    @GetMapping(path = "{id}")
    private Product product(@PathVariable("id") Long id){
        return service.findOne(id);
    }

}
