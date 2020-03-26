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
        ModelAndView mav = new ModelAndView("currency/create");
        mav.addObject("currencies", currencyService.allCurrency());
        return mav;
    }

    @PostMapping(value = "admin/save")
    private Currency save(@Valid @RequestParam("code") String code,
                          @Valid @RequestParam("region_country") String region_country,
                          @Valid @RequestParam("currency") String currencyName ){
        var currency = new Currency();
        currency.setCode(code);
        currency.setRegion_country(region_country);
        currency.setCurrency(currencyName);
        return currencyService.save(currency);
    }

    @GetMapping(path = "{id}")
    private Optional<Currency> findOne(@PathVariable("id") Long id ){
        return currencyService.currencyByID(id);
    }
}
