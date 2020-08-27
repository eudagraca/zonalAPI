package mz.co.zonal.repository;

import mz.co.zonal.models.Images;
import mz.co.zonal.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ImagesRepository extends JpaRepository<Images, Long> {
    // NOTE: you have return void
    @Modifying
    @Transactional
    @Query(value="delete from Images p where p.product = ?1")
    int deleteImagesByProductId(Product product);
}
