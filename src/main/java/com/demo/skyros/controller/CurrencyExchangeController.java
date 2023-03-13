package com.demo.skyros.controller;

import com.demo.skyros.service.CurrencyExchangeService;
import com.demo.skyros.entity.CurrencyExchangeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {


    @Autowired
    private CurrencyExchangeService currencyExchangeService;

    @GetMapping("currency-exchange/from/{from}/to/{to}")
    public CurrencyExchangeEntity exchangeCurrency(@PathVariable String from, @PathVariable String to) {
        return currencyExchangeService.exchangeCurrency(from, to);
    }
}
