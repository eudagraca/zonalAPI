package mz.co.zonal.service;

import mz.co.zonal.models.Product;

import java.util.ArrayList;

public interface ProductLikeServiceImpl {
    Boolean likeAndDislike(Long productId, Long userId);

    ArrayList<Product> productLiked(Long userId);
}
