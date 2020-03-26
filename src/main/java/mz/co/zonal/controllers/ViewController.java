package mz.co.zonal.controllers;

import mz.co.zonal.service.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/v01/views/")
public class ViewController {

    @Autowired
    private ViewService service;

    @PostMapping(value = "set/{productId}/{userId}")
    private void setViewCount(@PathVariable("productId") Long productId, @PathVariable("userId") Long userId){
        service.view(productId, userId);
    }
}
