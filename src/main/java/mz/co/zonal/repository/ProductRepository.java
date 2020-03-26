package mz.co.zonal.repository;

import mz.co.zonal.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findBySoldFalseOrderByCreatedDateAsc();

    ArrayList<Product> findByCurrencyId(Long currencyId);

    ArrayList<Product> findByTypeId(Long typeId);

    ArrayList<Product> findByBrandId(Long brandId);

    Product findOneById(Long productId);

    ArrayList<Product> findByUserIdAndSoldFalse(Long userId);

    ArrayList<Product> findByUserIdAndSoldTrue(Long id);

    ArrayList<Product> findByUserId(Long id);

    ArrayList<Product> findByCategoryId(Long categoryId);

    ArrayList<Product> findByTitleContainingOrderByCreatedDateAsc(String name);

    ArrayList<Product> findByTitleContainingAndCategoryIdAndTypeId(String title, Long categoryId, Long typeId);

    ArrayList<Product> findByTitleContainingOrCategoryIdOrTypeIdOrPriceLessThanEqualOrPriceGreaterThanEqual(
            String title, Long categoryId, Long typeId, Double priceLess, Double priceThan);

    ArrayList<Product> findByTitleContainingAndCategoryIdAndPriceLessThanEqualAndPriceGreaterThanEqual(
            String title, Long categoryId, Double priceLess, Double priceGreater);

    ArrayList<Product> findByTitleContainingAndTypeId(String title, Long typeId);

    ArrayList<Product> findByTitleContainingAndPriceGreaterThanEqual(String title, Double price);

    ArrayList<Product> findByTitleContainingAndPriceLessThanEqual(String title, Double price);

    ArrayList<Product> findByCategoryIdAndTypeId(Long categoryId, Long typeId);

    @Transactional
    @Modifying
    @Query("UPDATE Product p SET p.sold = ?1 WHERE p.id = ?2")
    int sold(boolean sold, Long id);

}