package mz.co.zonal.service;

import mz.co.zonal.models.Brand;

import java.util.ArrayList;

public interface BrandsImpl {

    ArrayList<Brand> allBrands();
    Brand findBrand(Long id);
    Brand save(Brand brand);
}
