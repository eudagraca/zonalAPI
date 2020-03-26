package mz.co.zonal.service;

import mz.co.zonal.models.Brand;
import mz.co.zonal.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class BrandService implements BrandsImpl {

    @Autowired
    private BrandRepository repository;

    @Override
    public ArrayList<Brand> allBrands() {
        return new ArrayList<>(repository.findAll());
    }

    @Override
    public Brand findBrand(Long id) {
        return repository.findBrandById(id);
    }

    @Override
    public Brand save(Brand brand) {
        return repository.save(brand);
    }
}
