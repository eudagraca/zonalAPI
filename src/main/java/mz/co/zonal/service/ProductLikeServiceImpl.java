package mz.co.zonal.service;

public interface ProductLikeServiceImpl {
    Boolean likeAndDislike(Long productId, Long userId);
}
