package mz.co.zonal.repository;

import mz.co.zonal.models.ProductLikes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface LikeRepository  extends JpaRepository<ProductLikes, Long> {

    ProductLikes findByProductIdAndUserId(Long productId, Long userId);

    @Transactional
    void deleteByProductIdAndUserId(Long productId, Long userId);

}
