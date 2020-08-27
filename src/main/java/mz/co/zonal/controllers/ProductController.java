package mz.co.zonal.controllers;

import mz.co.zonal.errors.ResourceNotFoundException;
import mz.co.zonal.models.Images;
import mz.co.zonal.models.Message;
import mz.co.zonal.models.Product;
import mz.co.zonal.service.ImagesService;
import mz.co.zonal.service.ProductService;
import mz.co.zonal.service.UserService;
import mz.co.zonal.utils.Disk;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static mz.co.zonal.utils.ImageConverterToByte.convertImages;
import static mz.co.zonal.utils.ImageConverterToByte.convertSingleProductImages;

@RestController
@RequestMapping("/rest/v01/product/")
public class ProductController {
    Logger logger = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private ProductService service;
    @Autowired
    private ImagesService imagesService;
    @Autowired
    private UserService userService;

    /**
     * Gets
     */
    @GetMapping
    private List<Product> allProducts() {
        var productList = service.allProducts();
        return convertImages(productList);
    }

    @GetMapping("all")
    private List<Product> allProduct() {
        var productList = service.allProducts();
        return productList;
    }

    @GetMapping(path = "{id}")
    private ResponseEntity<Product> product(@PathVariable("id") Long id) {

        var product = service.findOne(id);
        if (product != null) {
            return  ResponseEntity.status(HttpStatus.OK).body(convertSingleProductImages(product));
        }else {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Product());
        }
    }

    @GetMapping(path = "user/{userId}")
    private ArrayList<Product> productsByUser(@PathVariable("userId") Long userId) {
            var products = service.findByUserIdAndSoldFalse(userId);
            if (!products.isEmpty()){
                return convertImages(products);
            }else {
                return (ArrayList<Product>) products;
            }
    }

    @GetMapping(path = "category/{categoryId}")
    private List<Product> productByCategory(@PathVariable("categoryId") Long categoryId) {
        try {
            var products = service.findProductsByCategoryId(categoryId);
            return convertImages(products);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @GetMapping(path = "search/{title}")
    private List<Product> findProductByName(@PathVariable("title") String title) {
        try {
            var productList = service.findByNameLike(title);
            return convertImages(productList);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @GetMapping(path = "search/type/{id}")
    private List<Product> findProductType(@PathVariable("id") Long id) {
        try {
            var products = service.findByType(id);
            return convertImages(products);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @GetMapping(path = "brand/{id}")
    private List<Product> findProductBrand(@PathVariable("id") Long title) {
        return service.findByBrand(title);
    }

    @GetMapping(path = "currency/{id}")
    private List<Product> findProductByCurrency(@PathVariable("id") Long id) {
        var products = service.findByCurrency(id);
        return convertImages(products);
    }

    @PostMapping(path = "sell/{id}")
    private int soldProduct(@PathVariable("id") Long id) {
        return service.sold(id);
    }

    @GetMapping(path = "sold/user/{userId}")
    private List<Product> productsSoldByUser(@PathVariable("userId") Long userId) {
        var products = service.findByUserIdAndSoldTrue(userId);
        return convertImages(products);
    }

    @GetMapping(path = "sold/count/{userId}")
    private Long countSold(@PathVariable("userId") Long userId) {
        return service.countByUserIdAndSoldTrue(userId);
    }

    @GetMapping(path = "selling/count/{userId}")
    private Long countSelling(@PathVariable("userId") Long userId) {
        return service.countByUserIdAndSoldFalse(userId);
    }
    /*
      Filters
     */

    /**
     * @param title    - of product
     * @param category - of product
     * @param type     - of product
     * @return Products
     */
    @GetMapping(value = "search/{title}/{category}/{type}")
    public List<Product> findByTitleCategoryAndType(@PathVariable("title") String title
            , @PathVariable("category") Long category, @PathVariable("type") Long type) {
        var products = service.findByTitleContainingAndCategoryIdAndTypeId(title, category, type);
        return convertImages(products);
    }

    @GetMapping(value = "search/category/{categoryId}/type/{typeId}")
    public List<Product> findByCategoryAndType(@PathVariable("categoryId") Long categoryId, @PathVariable("typeId") Long typeId) {
        var products = service.findByCategoryIdAndTypeId(categoryId, typeId);
        return convertImages(products);
    }

    /**
     * @param title        - of product
     * @param category     - of product
     * @param type         - of product
     * @param priceLess    - of product
     * @param priceGreater - of product
     * @return Products have some category  and  type.... And price less than pricesLess and priceGreater
     */
    @GetMapping(value = "search/{title}/{category}/{type}/{priceLess}/{priceGreater}")
    public List<Product> findByTitleCategoryAndTypePriceLessPriceThan(@PathVariable("title") String title
            , @PathVariable("category") Long category
            , @PathVariable("type") Long type
            , @PathVariable("priceLess") Double priceLess
            , @PathVariable("priceGreater") Double priceGreater) {
        var products = service.findByTitleContainingAndCategoryIdAndTypeIdAndPriceLessThanAndPriceGreaterThan(title, category, type, priceLess, priceGreater);
        return convertImages(products);
    }

    /**
     * @param title        - of product
     * @param category     - of product
     * @param priceLess    - of product
     * @param priceGreater - of product
     * @return Products who have name like @param title and price less @param
     * priceLess and price greater @param priceGreater
     */

    @GetMapping(value = "search/{title}/category/{category}/price/{priceLess}/{priceGreater}")
    private List<Product> findByTitleCategoryIdAndPriceLess(@PathVariable("title") String title
            , @PathVariable("category") Long category
            , @PathVariable("priceLess") Double priceLess
            , @PathVariable("priceGreater") Double priceGreater) {
        var products = service.findByTitleContainingAndCategoryIdAndPriceLessThanEqual(title, category, priceLess, priceGreater);
        return convertImages(products);
    }

    /**
     * @param title of product
     * @param type  of product
     * @return Products who have name like @param title and is type @param type
     */
    @GetMapping(value = "search/{title}/{type}")
    private List<Product> findByTitleTypeId(@PathVariable("title") String title
            , @PathVariable("type") Long type) {
        var products = service.findByTitleContainingAndTypeId(title, type);
        return convertImages(products);
    }

    /**
     * @param title - of product
     * @param price - of product
     * @return Products who have name like @param title and price less @param price
     */
    @GetMapping(value = "search/{title}/less/{price}")
    private List<Product> findByTitleByPriceLess(@PathVariable("title") String title
            , @PathVariable("price") Double price) {
        var products = service.findByTitleContainingAndPriceLessThan(title, price);
        return convertImages(products);
    }

    /**
     * @param title - of product
     * @param price - of product
     * @return Products who have name like title and price greater @param price
     */
    @GetMapping(value = "search/{title}/greater/{price}")
    private List<Product> findByTitleByPriceThan(@PathVariable("title") String title
            , @PathVariable("price") Double price) {
        var products = service.findByTitleContainingAndPriceGreaterThan(title, price);
        return convertImages(products);
    }

    @GetMapping(value = "messages/{userId}/{productId}")
    private ArrayList<Message> productStartMessage(@PathVariable("userId") Long userId,
                                                   @PathVariable("productId") Long productId) {
        var product = service.findOne(productId);
        var user = userService.userByID(userId);
        var messages = new ArrayList<Message>();
        if(product == null){
        }
        else if (product.getMessages().size() > 0) {
            for (Message message : product.getMessages()) {
                if (message.getSender().getId().equals(user.getId()) || message.getReceiver().getId().equals(user.getId())) {
                    messages.add(message);
                }
            }
            if (messages.size() > 0) {
                product.setMessages(messages);
            }
        }
        return messages;
    }

    @GetMapping(value = "messages/{userId}")
    private List<Product> productsHaveMessage(@PathVariable("userId") Long userId) {
        var products = service.allProducts();
        var user = userService.userByID(userId);
        var productsMessage = new ArrayList<Product>();
        var messages = new ArrayList<Message>();
        if (user != null) {
            for (Product product : products) {
                if (product.getMessages().size() > 0) {
                    for (Message message : product.getMessages()) {
                        if (message.getSender().getId().equals(user.getId()) || message.getReceiver().getId().equals(user.getId())) {
                            messages.add(message);
                        }
                    }
                    if (messages.size() > 0) {
                        product.setMessages(messages);
                        productsMessage.add(product);
                    }
                }
            }
        }
        return convertImages(productsMessage);
    }
    /*
      End of filters
     */

    @Transactional
    void deleteProductImage(List<Images> imageList, Long id) {
        System.out.println(id+"\n");
        try {
            if (imagesService.deleteImages(id) == 1) {
                for (Images image : imageList) {
                    File file = new File(image.getImagePath());
                    if (file.delete()) {
                        System.out.println(file.getName() + " is deleted!");
                    } else {
                        System.out.println("Delete operation is failed.");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Failed to Delete image !!   "+e);
        }
    }

    /**
     * Delete
     */

    @DeleteMapping(path = "{id}")
    private int delete(@PathVariable("id") Long id) {
        try {
            var product = service.findOne(id);
            if (service.delete(id) == 1) {
                deleteProductImage(product.getImages(), id);
            }
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Posts
     */

    @PostMapping
    private Product save(@RequestBody Product product) {
        return service.save(product);
    }

    @PostMapping(value = "images/", headers = ("content-type=multipart/*"), consumes = "multipart/form-data")
    private Boolean saveImages(@RequestParam("productId") Long id,
                               @RequestParam("files") MultipartFile[] files) {
        var disk = new Disk("product");
        var product = service.findOne(id);

        if (product.getImages() != null && product.getImages().size() > 0) {
            deleteProductImage(product.getImages(), id);
        }
        String fileName;
        try {
            if (files != null && files.length > 0) {
                if (product.getId().equals(id)) {
                    for (MultipartFile file : files) {
                        fileName = disk.saveImage(file);
                        Images image = new Images(fileName, product);
                        imagesService.saveImage(image);
                    }
                    return true;
                }
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    /**
     * Put
     */

    @PutMapping
    private Product update(@RequestBody Product product) {
        return service.update(product);
    }

}
