package mz.co.zonal.repository;

import mz.co.zonal.models.Category;
import mz.co.zonal.models.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type, Long> {
}
