package mz.co.zonal.controllers;

import mz.co.zonal.models.Message;
import mz.co.zonal.models.User;
import mz.co.zonal.service.MessagesService;
import mz.co.zonal.service.ProductService;
import mz.co.zonal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/rest/v01/messages/")
public class MessageController {

    private final MessagesService service;
    private final UserService userService;
    private final ProductService productService;

    public MessageController(MessagesService service, UserService userService, ProductService productService) {
        this.service = service;
        this.userService = userService;
        this.productService = productService;
    }

    @PostMapping(value = "send")
    private Message sendMessage(@RequestBody HashMap<String, String> map) {
        System.out.println(map);
        var message = new Message(userService.userByID(Long.parseLong(map.get("sender"))),
                userService.userByID(Long.parseLong(map.get("receiver"))),
                productService.findOne(Long.parseLong(map.get("product"))),
                map.get("message"));
        return service.sendMessage(message);
    }

    @GetMapping(value = "history/{productId}/{userId}/{senderId}")
    private int fetchHistory(@PathVariable("productId") Long productId, @PathVariable("userId") Long userId, @PathVariable("senderId") Long senderId ){
        return service.fetchHistory(productId, senderId, userId);
    }
}
