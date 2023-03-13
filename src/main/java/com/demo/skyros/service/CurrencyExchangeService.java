package com.demo.skyros.service;

import com.demo.skyros.entity.CurrencyExchangeEntity;
import com.demo.skyros.repo.CurrencyExchangeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class CurrencyExchangeService {

    @Autowired
    private CurrencyExchangeRepo currencyExchangeRepo;

    @Autowired
    private Environment environment;

    public CurrencyExchangeEntity exchangeCurrency(String from, String to) {
        CurrencyExchangeEntity currencyExchangeEntity = currencyExchangeRepo.findByFromAndTo(from, to);
        currencyExchangeEntity.setEnvironment(environment.getProperty("local.server.port"));
        return currencyExchangeEntity;
    }
}
