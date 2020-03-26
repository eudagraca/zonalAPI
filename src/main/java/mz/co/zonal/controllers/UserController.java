package mz.co.zonal.controllers;

import mz.co.zonal.models.User;
import mz.co.zonal.service.UserService;
import mz.co.zonal.utils.Disk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.FileTypeMap;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

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
    private User getUserById(@PathVariable("id") Long id) {
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

    @PutMapping(value = "upload", headers = ("content-type=multipart/*"), consumes = "multipart/form-data")
    public User uploadFile(@RequestParam("picPath") MultipartFile picPath, @RequestParam("userId") Long id) throws IOException {
        var disk = new Disk("user");
        userService.updateImage(disk.saveImage(picPath), id);
        return userService.userByID(id);
    }

    @GetMapping(value = "/photo/{userId}")
    public ResponseEntity<byte[]> getUserPhoto(@PathVariable Long userId) throws IOException {
        var user = userService.userByID(userId);
        System.out.println(user.getPicPath());
        File f = new File(user.getPicPath());
        var imagem = Files.readAllBytes(f.toPath());
//        return ResponseEntity.ok().contentType(MediaType.parseMediaType(imagem.)).body(imagem);
        byte[] data = new byte[0];
        try {
            assert user.getPicPath() != null;
            if
            (!user.getPicPath().isEmpty()) {
                String picPath = user.getPicPath();
                Path path = Paths.get(picPath);
                data = Files.readAllBytes(path);
            }
        } catch (IOException e) {
            data = null;
        }
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }

    @PostMapping(value = "login")
    private User loginUser(@RequestBody User user) {
        return userService.login(user);
    }

    @PutMapping(value = "setCoordinators")
    private User setLatitudeAndLongitude(@RequestBody User user){
        return userService.setLatAndLong(user);
    }

    @PutMapping(value = "setToken")
    private User setToken(@RequestBody User user){
        return userService.setToken(user);
    }
}