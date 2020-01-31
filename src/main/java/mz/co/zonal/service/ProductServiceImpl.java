package mz.co.zonal.service;

import mz.co.zonal.models.Product;

import java.util.ArrayList;

public interface ProductServiceImpl {

    Product save(Product product);
    Product findOne(Long id);
    ArrayList<Product> allProducts();
    boolean update(Long id);
    boolean delete(Long id);
}
