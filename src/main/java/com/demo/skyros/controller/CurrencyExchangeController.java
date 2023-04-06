package com.demo.skyros.controller;

import com.demo.skyros.exception.AppResponse;
import com.demo.skyros.service.CurrencyExchangeService;
import com.demo.skyros.vo.CurrencyExchangeVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("currency")
public class CurrencyExchangeController {

    Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);
    @Autowired
    private CurrencyExchangeService currencyExchangeService;

    @PostMapping("exchange")
    public AppResponse exchangeCurrency(@RequestBody CurrencyExchangeVO vo) {
        return currencyExchangeService.exchangeCurrency(vo);
    }

    @PostMapping("add")
    public AppResponse add(@RequestBody CurrencyExchangeVO vo) {
        return currencyExchangeService.add(vo);
    }

    @PutMapping("update")
    public AppResponse update(@RequestBody CurrencyExchangeVO vo) {
        return currencyExchangeService.update(vo);
    }

    @GetMapping("findAll")
    public AppResponse findAll() {
        return currencyExchangeService.findAll();
    }

    @GetMapping("find/{id}")
    public AppResponse find(@PathVariable long id) {
        return currencyExchangeService.findOne(id);
    }

    @DeleteMapping("delete/{id}")
    public AppResponse delete(@PathVariable long id) {
        return currencyExchangeService.delete(id);
    }

}
