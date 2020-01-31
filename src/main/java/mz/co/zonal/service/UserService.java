package mz.co.zonal.service;

import mz.co.zonal.models.User;
import mz.co.zonal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService implements UserServiceImpl {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public ArrayList<User> allUsers() {
        return new ArrayList<>(userRepository.findAll());
    }

    @Override
    public Optional<User> userByID(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User signUp(@Valid User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean deleteUser(Long id) {
        var user = userRepository.getOne(id);
        userRepository.delete(user);
        return user.getEmail() != null;
    }

    @Override
    public boolean updateUser(User user) {
        try {
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public User getOne(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public boolean updateImage(String path, Long id) {
        var user = userRepository.getOne(id);
        if (user.getEmail() != null || userRepository.existsById(id)) {
            user.setPicPath(path);
            userRepository.save(user);
            System.out.println("--------  " + path);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public User login(User user) {
        User userInDataBase = userRepository.findByEmail(user.getEmail());

        if (user == null || userInDataBase == null) {
            return null;
        }

        return userInDataBase.getEmail().equals(user.getEmail())
                && userInDataBase.getPassword().equals(user.getPassword())
                ? userInDataBase : null;
    }
}