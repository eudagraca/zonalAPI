package mz.co.zonal.service;

import mz.co.zonal.models.Product;

import java.util.ArrayList;
import java.util.List;

public interface ProductServiceImpl {

    Product save(Product product);

    Product findOne(Long id);

    List<Product> allProducts();

    List<Product> findByUserId(Long id);

    Product update(Product product);

    int delete(Long id);

    List<Product> findByUserIdAndSoldFalse(Long userId);

    List<Product> findByUserIdAndSoldTrue(Long userId);

    List<Product> findProductsByCategoryId(Long categoryId);

    List<Product> findByNameLike(String name);

    List<Product> findByTitleContainingAndCategoryIdAndTypeId(String title, Long categoryId, Long typeId);

    List<Product> findByTitleContainingAndCategoryIdAndTypeIdAndPriceLessThanAndPriceGreaterThan(
            String title, Long categoryId, Long typeId, Double priceLess, Double priceThan);

    List<Product> findByTitleContainingAndCategoryIdAndPriceLessThanEqual(
            String title, Long categoryId, Double priceLess, Double priceGreater);


    List<Product> findByTitleContainingAndTypeId(String title, Long typeId);

    List<Product> findByTitleContainingAndPriceGreaterThan(String title, Double price);

    List<Product> findByTitleContainingAndPriceLessThan(String title, Double price);

    List<Product> findByCategoryId(Long id);

    List<Product> findByType(Long name);

    List<Product> findByBrand(Long name);

    List<Product> findByCurrency(Long name);

    List<Product> findByCategoryIdAndTypeId(Long categoryId, Long typeId);

    int sold(Long id);

    Long countByUserIdAndSoldTrue(Long userId);

    Long countByUserIdAndSoldFalse(Long userId);
}
