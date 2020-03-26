package mz.co.zonal.repository;

import mz.co.zonal.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String name);

    User findUserById(Long id);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.latitude = ?1, u.longitude = ?2, u.province =?3," +
            "u.country = ?4, u.city = ?5 WHERE u.id = ?6")
    void setLatAndLong(Double latitude, Double longitude, String province, String country,
                      String city, Long userId);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.picPath = ?1 WHERE u.id = ?2")
    void setImageUrl(String picPath, Long userId);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.token = ?1 WHERE u.id = ?2")
    void setToken(String token, Long user);
}
