package mz.co.zonal.repository;

import mz.co.zonal.models.ProductLikes;
import mz.co.zonal.models.View;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViewsRepository extends JpaRepository<View, Long> {

    View findByProductIdAndUserId(Long productId, Long userId);

}
