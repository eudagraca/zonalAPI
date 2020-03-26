package mz.co.zonal.service;

import mz.co.zonal.models.User;

import java.util.ArrayList;

public interface UserServiceImpl {
    ArrayList<User> allUsers();
    User userByID(Long id);
    User signUp(User user);
    boolean deleteUser(Long id);
    boolean updateUser(User user);
    boolean updateImage(String path, Long id);
    User login(User user);
    User setLatAndLong(User user);

    User setToken(User user);
}
