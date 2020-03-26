package mz.co.zonal.controllers;

import mz.co.zonal.models.Message;
import mz.co.zonal.models.User;
import mz.co.zonal.service.MessagesService;
import mz.co.zonal.service.ProductService;
import mz.co.zonal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/rest/v01/messages/")
public class MessageController {

    @Autowired
    private MessagesService service;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    @PostMapping(value = "send")
    private Message sendMessage(@RequestBody HashMap<String, String> map) {
        var message = new Message(userService.userByID(Long.parseLong(map.get("sender"))),
                userService.userByID(Long.parseLong(map.get("receiver"))),
                productService.findOne(Long.parseLong(map.get("product"))),
                map.get("message"));
        return service.sendMessage(message);
    }

//    private
}
