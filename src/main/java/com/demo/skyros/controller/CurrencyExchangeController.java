package com.demo.skyros.controller;

import com.demo.skyros.exception.AppResponse;
import com.demo.skyros.service.CurrencyExchangeService;
import com.demo.skyros.vo.CurrencyExchangeVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

    Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);
    @Autowired
    private CurrencyExchangeService currencyExchangeService;

    @PostMapping("currency-exchange")
    public AppResponse exchangeCurrency(@RequestBody CurrencyExchangeVO vo) {
        return currencyExchangeService.exchangeCurrency(vo);
    }
}
