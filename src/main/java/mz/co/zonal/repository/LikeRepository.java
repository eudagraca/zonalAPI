package mz.co.zonal.repository;

import mz.co.zonal.models.Product;
import mz.co.zonal.models.ProductLikes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Repository
public interface LikeRepository  extends JpaRepository<ProductLikes, Long> {

    ProductLikes findByProductIdAndUserId(Long productId, Long userId);

    ArrayList<ProductLikes> findByUserId(Long userId);

    @Transactional
    void deleteByProductIdAndUserId(Long productId, Long userId);

}
