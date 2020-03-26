package mz.co.zonal.controllers;

import com.pusher.rest.Pusher;
import mz.co.zonal.service.ProductLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;

@RestController
@RequestMapping("/rest/v01/likes/")
public class LikeController {

    @Autowired
    private ProductLikeService likeService;

    @PostMapping(value = "likeOrDislike/{productId}/{userId}")
    @ResponseBody
    private boolean likeAndDislike(@PathVariable("productId") Long productId, @PathVariable("userId") Long userId){
        return likeService.likeAndDislike(productId, userId);
    }
}
