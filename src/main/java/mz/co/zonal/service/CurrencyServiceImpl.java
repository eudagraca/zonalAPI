package mz.co.zonal.service;

import mz.co.zonal.models.Currency;

import java.util.ArrayList;
import java.util.Optional;

public interface CurrencyServiceImpl {
    ArrayList<Currency> allCurrency();
    Optional<Currency> currencyByID(Long id);
    Currency save(Currency currency);
}
