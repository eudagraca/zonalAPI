package mz.co.zonal.service;

import mz.co.zonal.models.User;

import java.util.ArrayList;
import java.util.Optional;

public interface UserServiceImpl {
    ArrayList<User> allUsers();
    Optional<User> userByID(Long id);
    User signUp(User user);
    boolean deleteUser(Long id);
    boolean updateUser(User user);
    User getOne(Long id);
    boolean updateImage(String path, Long id);
    User login(User user);
}
