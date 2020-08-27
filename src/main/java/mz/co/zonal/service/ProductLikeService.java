package mz.co.zonal.service;

import mz.co.zonal.models.Product;
import mz.co.zonal.models.ProductLikes;
import mz.co.zonal.repository.LikeRepository;
import mz.co.zonal.repository.ProductRepository;
import mz.co.zonal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductLikeService implements ProductLikeServiceImpl{

    @Autowired
    private LikeRepository repository;

    @Autowired
    private ProductRepository product;

    @Autowired
    private UserRepository user;

    @Override
    public Boolean likeAndDislike(Long productId, Long userId) {

        var isLiked = repository.findByProductIdAndUserId(productId, userId);
        if (isLiked == null){
            var userF = user.findUserById(userId);
            var productF = product.findOneById(productId);
            repository.save(new ProductLikes(userF, productF));
            productF.setLikesCount(productF.getLikesCount() +1);
            product.save(productF);
            return true;
        }else {
            var productF = product.findOneById(productId);
            productF.setLikesCount(productF.getLikesCount() -1);
            product.save(productF);
            repository.deleteByProductIdAndUserId(productId, userId);
            return false;
        }
    }

    @Override
    public ArrayList<Product> productLiked(Long userId) {
        var productsList = repository.findByUserId(userId);
        var products = new ArrayList<Product>();
        for (var product: productsList) {
            products.add(product.getProduct());
        }
        return products;
    }
}
