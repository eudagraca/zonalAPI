package mz.co.zonal.controllers;

import mz.co.zonal.models.Currency;
import mz.co.zonal.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/rest/v01/currency/")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping
    private ArrayList<Currency> allCurrency(){
        return currencyService.allCurrency();
    }

    @GetMapping("admin/create")
    private ModelAndView form(){
        return new ModelAndView("currency/create");
    }

    @PostMapping(value = "admin/save")
    private Currency save(@Valid @RequestParam("code") String code,
                          @Valid @RequestParam("region_country") String region_country,
                          @Valid @RequestParam("currency") String currency ){
        var c = new Currency();
        c.setCode(code);
        c.setRegion_country(region_country);
        c.setCurrency(currency);
        return currencyService.save(c);
    }

    @GetMapping(path = "{id}")
    private Optional<Currency> findOne(@PathVariable("id") Long id ){
        return currencyService.currencyByID(id);
    }
}
