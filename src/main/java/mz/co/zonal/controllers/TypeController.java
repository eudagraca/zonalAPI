package mz.co.zonal.controllers;

import mz.co.zonal.models.Type;
import mz.co.zonal.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/rest/v01/type/")
public class TypeController {

    @Autowired
    private TypeRepository repository;

    @GetMapping(path = "{id}/")
    private Optional<Type> type(@PathVariable("id") Long id){
        return repository.findById(id);
    }
    @GetMapping
    private ArrayList<Type> type(){
        return new ArrayList<>(repository.findAll());
    }

    @GetMapping("admin/create")
    private ModelAndView form(){
        ModelAndView mav = new ModelAndView("type/create");
        mav.addObject("types", repository.findAll());
        return mav;
    }
    @PostMapping(value = "admin/save")
    private Type save(@Valid @RequestParam("name") String name){
        var type = new Type();
        type.setName(name);
        return repository.save(type);
    }

}
