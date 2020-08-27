package mz.co.zonal.service;

import mz.co.zonal.models.Product;
import mz.co.zonal.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements ProductServiceImpl {

    @Autowired
    ProductRepository repository;

    //    @Transactional
    @Override
    public Product save(Product product) {
        try {
            return repository.save(product);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Product findOne(Long id) {
        return repository.findOneById(id);
    }

    @Override
    public List<Product> allProducts() {
        return repository.findBySoldFalseOrderByCreatedDateAsc();
    }

    @Override
    public Product update(Product product) {
        return repository.save(product);

    }

    @Override
    public int delete(Long id) {
        try {
            repository.deleteById(id);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public List<Product> findByUserIdAndSoldFalse(Long useId) {
        return repository.findByUserIdAndSoldFalse(useId);
    }

    @Override
    public List<Product> findProductsByCategoryId(Long categoryId) {
        return repository.findByCategoryIdAndSoldFalse(categoryId);
    }

    @Override
    public List<Product> findByNameLike(String name) {
        return repository.findByTitleContainingOrderByCreatedDateAsc(name);
    }

    @Override
    public int sold(Long id) {
        return repository.sold(true, id);
    }

    @Override
    public Long countByUserIdAndSoldTrue(Long userId) {
        return repository.countByUserIdAndSoldTrue(userId);
    }

    @Override
    public Long countByUserIdAndSoldFalse(Long userId) {
        return repository.countByUserIdAndSoldFalse(userId);
    }

    @Override
    public List<Product> findByUserIdAndSoldTrue(Long userId) {
        return repository.findByUserIdAndSoldTrue(userId);
    }

    @Override
    public List<Product> findByUserId(Long id) {
        return repository.findByUserId(id);
    }

    /**
     * Filters
     *
     * @Param title
     * @Param price
     * @Param category
     * @Param type
     */
    @Override
    public List<Product> findByTitleContainingAndCategoryIdAndTypeId(String title, Long categoryId, Long typeId) {
        return repository.findByTitleContainingAndCategoryIdAndTypeId(title, categoryId, typeId);
    }

    @Override
    public List<Product>
    findByTitleContainingAndCategoryIdAndTypeIdAndPriceLessThanAndPriceGreaterThan(String title, Long categoryId, Long typeId, Double priceLess, Double priceThan) {
        return repository.findByTitleContainingOrCategoryIdOrTypeIdOrPriceLessThanEqualOrPriceGreaterThanEqual(title, categoryId, typeId, priceLess, priceThan);
    }

    @Override
    public List<Product> findByTitleContainingAndCategoryIdAndPriceLessThanEqual(String title, Long categoryId, Double priceLess, Double priceGreater) {
        return repository.findByTitleContainingAndCategoryIdAndPriceLessThanEqualAndPriceGreaterThanEqual(title, categoryId, priceLess, priceGreater);
    }

    @Override
    public List<Product> findByTitleContainingAndTypeId(String title, Long typeId) {
        return repository.findByTitleContainingAndTypeId(title, typeId);
    }

    @Override
    public List<Product> findByTitleContainingAndPriceGreaterThan(String title, Double price) {
        return repository.findByTitleContainingAndPriceGreaterThanEqual(title, price);
    }

    @Override
    public List<Product> findByTitleContainingAndPriceLessThan(String title, Double price) {
        return repository.findByTitleContainingAndPriceLessThanEqual(title, price);
    }

    @Override
    public List<Product> findByCategoryId(Long id) {
        return repository.findByCategoryIdAndSoldFalse(id);
    }

    @Override
    public List<Product> findByType(Long typeID) {
        return repository.findByTypeId(typeID);
    }

    @Override
    public List<Product> findByBrand(Long name) {
        return repository.findByBrandId(name);
    }

    @Override
    public List<Product> findByCurrency(Long name) {
        return repository.findByCurrencyId(name);
    }

    @Override
    public List<Product> findByCategoryIdAndTypeId(Long categoryId, Long typeId) {
        return repository.findByCategoryIdAndTypeId(categoryId, typeId);
    }


}
