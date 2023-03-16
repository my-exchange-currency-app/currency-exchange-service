package com.demo.skyros.controller;

import com.demo.skyros.entity.CurrencyExchangeEntity;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

    @GetMapping("sample-api")
    //@Retry(name = "sample-api", fallbackMethod = "getDefaultCurrencyExchange")
    @CircuitBreaker(name = "sample-api", fallbackMethod = "getDefaultCurrencyExchange")
    @RateLimiter(name = "sample-api") // with 10s , the allowed calls is 10,000(10k)
    @Bulkhead(name = "sample-api")
    public CurrencyExchangeEntity sampleApi() {
        logger.info("sampleApi for CircuitBreaker");
        //CurrencyExchangeEntity currencyExchangeEntity = testDummyApi();
        return new CurrencyExchangeEntity();
    }

    private CurrencyExchangeEntity testDummyApi() {
        String uri = "http://localhost:8080/test";
        return restTemplate.getForObject(uri, CurrencyExchangeEntity.class);
    }

    private CurrencyExchangeEntity getDefaultCurrencyExchange(Exception ex) {
        return new CurrencyExchangeEntity();
    }

}
