package mz.co.zonal.service;

import mz.co.zonal.models.Product;
import mz.co.zonal.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class ProductService implements ProductServiceImpl {

    @Autowired
    ProductRepository repository;

    @Override
    public Product save(Product product) {
        return repository.save(product);
    }

    @Override
    public Product findOne(Long id) {
        return repository.getOne(id);
    }

    @Override
    public ArrayList<Product> allProducts() {
        return new ArrayList<>(repository.findAllByOrderByCreatedDateAsc());
    }

    @Override
    public boolean update(Long id) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
