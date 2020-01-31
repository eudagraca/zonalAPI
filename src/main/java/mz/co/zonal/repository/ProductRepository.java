package mz.co.zonal.repository;

import mz.co.zonal.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    ArrayList<Product> findAllByOrderByCreatedDateAsc();
}
