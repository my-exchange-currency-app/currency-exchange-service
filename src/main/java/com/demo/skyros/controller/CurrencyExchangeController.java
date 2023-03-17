package com.demo.skyros.controller;

import com.demo.skyros.entity.CurrencyExchangeEntity;
import com.demo.skyros.service.CurrencyExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {


    @Autowired
    private CurrencyExchangeService currencyExchangeService;
    @Autowired
    private Environment environment;

    @GetMapping("currency-exchange/from/{from}/to/{to}")
    public CurrencyExchangeEntity exchangeCurrency(@PathVariable String from, @PathVariable String to) {
        CurrencyExchangeEntity currencyExchangeEntity = currencyExchangeService.exchangeCurrency(from, to);
        currencyExchangeEntity.setEnvironment(environment.getProperty("local.server.port"));
        return currencyExchangeEntity;
    }
}
