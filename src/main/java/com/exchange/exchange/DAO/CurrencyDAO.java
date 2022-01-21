package com.exchange.exchange.DAO;

import com.exchange.exchange.models.Currency;
import com.exchange.exchange.repositories.CurrencyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrencyDAO {

    private final CurrencyRepository currencyRepository;

    public CurrencyDAO(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public List<String> getCurrencyNameList() {
        List<Currency> currencyList = currencyRepository.findAll();
        List<String> currencyNameList = currencyList
                .stream()
                .map(currency -> currency.getCharCode() + " (" + currency.getName() + ")")
                .sorted()
                .collect(Collectors.toList());
        return currencyNameList;
    }
}
