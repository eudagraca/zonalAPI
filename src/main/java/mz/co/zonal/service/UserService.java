package mz.co.zonal.service;

import mz.co.zonal.models.User;
import mz.co.zonal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;

@Service
public class UserService implements UserServiceImpl {

    @Autowired
    private UserRepository repository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public ArrayList<User> allUsers() {
        return new ArrayList<>(repository.findAll());
    }

    @Override
    public User userByID(Long id) {
        return repository.findUserById(id);
    }

    @Override
    public User userPhone(int phone) {
        return repository.findByPhoneNumber(phone);
    }

    @Override
    public User findByEmail(String mail) {
        return repository.findByEmail(mail);
    }

    @Override
    public User signUp(@Valid User user) {
        return repository.save(user);
    }

    @Override
    public boolean deleteUser(Long id) {
        var user = repository.getOne(id);
        repository.delete(user);
        return user.getEmail() != null;
    }

    @Override
    public boolean updateUser(User user) {
        try {
            repository.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateImage(String path, Long id) {
        var user = repository.getOne(id);
        if (user.getEmail() != null || repository.existsById(id)) {
            repository.setImageUrl(path, id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public User login(User user) {
        User userInDataBase = repository.findByEmail(user.getEmail());

        if (user == null || userInDataBase == null) {
            return new User();
        }

        return userInDataBase.getEmail().equals(user.getEmail())
                && userInDataBase.getPassword().equals(user.getPassword())
                ? userInDataBase : new User();
    }

    @Override
    public User setLatAndLong(User user) {
        repository.setLatAndLong(user.getLatitude(),
                user.getLongitude(), user.getProvince(), user.getCountry(),
                user.getCity(), user.getId());

        return repository.findUserById(user.getId());
    }

    @Override
    public User setToken(User user) {
        repository.setToken(user.getToken(), user.getId());
        return repository.findUserById(user.getId());
    }
}