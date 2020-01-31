package mz.co.zonal.service;

import mz.co.zonal.models.Currency;
import mz.co.zonal.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CurrencyService implements CurrencyServiceImpl{

    @Autowired
    private CurrencyRepository currencyRepository;

    @Override
    public ArrayList<Currency> allCurrency() {
        return new  ArrayList<>(currencyRepository.findAll());
    }

    @Override
    public Optional<Currency> currencyByID(Long id) {
        return currencyRepository.findById(id);
    }

    @Override
    public Currency save(Currency currency) {
        return currencyRepository.save(currency);
    }
}
