package com.demo.skyros.service;

import com.demo.skyros.entity.CurrencyExchangeEntity;
import com.demo.skyros.exception.AppResponse;
import com.demo.skyros.exception.ResourceNotFoundException;
import com.demo.skyros.repo.CurrencyExchangeRepo;
import com.demo.skyros.vo.CurrencyExchangeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Date;

@Service
public class CurrencyExchangeService {

    @Autowired
    private CurrencyExchangeRepo currencyExchangeRepo;
    @Autowired
    private Environment environment;

    @Cacheable(value = "currency-exchange", key = "new org.springframework.cache.interceptor.SimpleKey(#vo.from, #vo.to)")
    public AppResponse exchangeCurrency(CurrencyExchangeVO vo) {
        CurrencyExchangeEntity currencyExchangeEntity = currencyExchangeRepo.findByFromAndTo(vo.getFrom(), vo.getTo());
        if (null == currencyExchangeEntity) {
            currencyExchangeEntity = currencyExchangeRepo.findByFromAndTo(vo.getTo(), vo.getFrom());
            if (null != currencyExchangeEntity) {
                BigDecimal conversionMultiple = new BigDecimal(1).divide(currencyExchangeEntity.getConversionMultiple(), MathContext.DECIMAL32);
                currencyExchangeEntity.setFrom(vo.getFrom());
                currencyExchangeEntity.setTo(vo.getTo());
                currencyExchangeEntity.setConversionMultiple(conversionMultiple);
            } else {
                throw new ResourceNotFoundException("currencies are not supported yet");
            }
        }
        currencyExchangeEntity.setEnvironment(environment.getProperty("local.server.port"));
        return prepareAppResponse(currencyExchangeEntity, null);
    }

    private AppResponse prepareAppResponse(Object data, String message) {
        AppResponse appResponse = new AppResponse(message);
        appResponse.setData(data);
        appResponse.setResponseDate(new Date());
        appResponse.setHttpStatus(HttpStatus.OK);
        return appResponse;
    }

}
