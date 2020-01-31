package mz.co.zonal.controllers;

import mz.co.zonal.models.User;
import mz.co.zonal.service.UserService;
import mz.co.zonal.utils.Disk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/rest/v01/users/")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ArrayList<User> listAllUsers() {
        return userService.allUsers();
    }

    @GetMapping(path = "{id}")
    private Optional<User> getUserById(@PathVariable("id") Long id) {
        return userService.userByID(id);
    }

    @PostMapping(value = "signUp")
    private User signUp(@Valid @RequestBody User user) {
        return userService.signUp(user);
    }

    @DeleteMapping(path = "{id}")
    private boolean deleteUser(@PathVariable("id") Long id) {
        return userService.deleteUser(id);
    }

    @PutMapping
    private Boolean updateUser(@Valid @RequestBody User user) {
        return userService.updateUser(user);
    }

    @PutMapping(value = "upload/{id}")
    public boolean uploadFile(@RequestParam("picPath") MultipartFile file, @PathVariable long id) throws IOException {
        var disk = new Disk("user");
        return userService.updateImage(disk.salvar(file), id);
    }

    @PostMapping(value = "login")
    private User loginUser(@RequestBody User user){
        return userService.login(user);
    }
}