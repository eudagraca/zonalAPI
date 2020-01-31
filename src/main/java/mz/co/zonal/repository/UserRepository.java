package mz.co.zonal.repository;

import mz.co.zonal.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String name);

    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(int phoneNumber);

    User findByPassword(String password);
}
