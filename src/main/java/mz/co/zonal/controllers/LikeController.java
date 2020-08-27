package mz.co.zonal.controllers;

import mz.co.zonal.models.Product;
import mz.co.zonal.service.ProductLikeService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static mz.co.zonal.utils.ImageConverterToByte.convertImages;

@RestController
@RequestMapping("/rest/v01/likes/")
public class LikeController {

    private final ProductLikeService likeService;

    public LikeController(ProductLikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping(value = "likeOrDislike/{productId}/{userId}")
    @ResponseBody
    private boolean likeAndDislike(@PathVariable("productId") Long productId, @PathVariable("userId") Long userId){
        return likeService.likeAndDislike(productId, userId);
    }

    @GetMapping(value = "user/{userId}")
    private ArrayList<Product> productLiked(@PathVariable("userId") Long userId){
        return convertImages(likeService.productLiked(userId));
    }
}
