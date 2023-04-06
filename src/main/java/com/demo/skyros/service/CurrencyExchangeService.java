package com.demo.skyros.service;

import com.demo.skyros.entity.CurrencyExchangeEntity;
import com.demo.skyros.exception.AppResponse;
import com.demo.skyros.exception.ResourceNotFoundException;
import com.demo.skyros.mapper.CurrencyExchangeMapper;
import com.demo.skyros.repo.CurrencyExchangeRepo;
import com.demo.skyros.vo.CurrencyExchangeVO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Getter
@Setter
public class CurrencyExchangeService {

    @Autowired
    private CurrencyExchangeRepo currencyExchangeRepo;
    @Autowired
    private Environment environment;
    @Autowired
    private CurrencyExchangeMapper currencyExchangeMapper;


    @Cacheable(value = "currencyExchange", key = "new org.springframework.cache.interceptor.SimpleKey(#vo.from, #vo.to)")
    public AppResponse exchangeCurrency(CurrencyExchangeVO vo) {
        CurrencyExchangeEntity currencyExchangeEntity = getCurrencyExchangeRepo().findByFromAndTo(vo.getFrom(), vo.getTo());
        if (null == currencyExchangeEntity) {
            currencyExchangeEntity = getCurrencyExchangeRepo().findByFromAndTo(vo.getTo(), vo.getFrom());
            if (null != currencyExchangeEntity) {
                BigDecimal conversionMultiple = new BigDecimal(1).divide(currencyExchangeEntity.getConversionMultiple(), MathContext.DECIMAL32);
                currencyExchangeEntity.setFrom(vo.getFrom());
                currencyExchangeEntity.setTo(vo.getTo());
                currencyExchangeEntity.setConversionMultiple(conversionMultiple);
            } else {
                throw new ResourceNotFoundException("currencies are not supported yet");
            }
        }
        CurrencyExchangeVO currencyExchangeVO = getCurrencyExchangeMapper().entityToVO(currencyExchangeEntity);
        currencyExchangeVO.setEnvironment(environment.getProperty("local.server.port"));
        return prepareAppResponse(currencyExchangeVO, null);
    }

    public AppResponse add(CurrencyExchangeVO vo) {
        vo.setId(null);
        CurrencyExchangeEntity currencyExchangeEntity = getCurrencyExchangeMapper().VOToEntity(vo);
        CurrencyExchangeEntity savedCurrency = getCurrencyExchangeRepo().save(currencyExchangeEntity);
        CurrencyExchangeVO currencyExchangeVO = getCurrencyExchangeMapper().entityToVO(savedCurrency);
        return prepareAppResponse(currencyExchangeVO, null);
    }

    public AppResponse update(CurrencyExchangeVO vo) {
        CurrencyExchangeEntity currencyExchangeEntity = getCurrencyExchangeMapper().VOToEntity(vo);
        CurrencyExchangeEntity savedCurrency = getCurrencyExchangeRepo().save(currencyExchangeEntity);
        CurrencyExchangeVO currencyExchangeVO = getCurrencyExchangeMapper().entityToVO(savedCurrency);
        return prepareAppResponse(currencyExchangeVO, null);
    }

    public AppResponse findAll() {
        List<CurrencyExchangeEntity> entities = getCurrencyExchangeRepo().findAll();
        List<CurrencyExchangeVO> currencyExchangeVOs = getCurrencyExchangeMapper().entityListToVOList(entities);
        return prepareAppResponse(currencyExchangeVOs, null);
    }

    public AppResponse findOne(Long id) {
        CurrencyExchangeVO currencyExchangeVO = new CurrencyExchangeVO();
        Optional<CurrencyExchangeEntity> currencyExchange = getCurrencyExchangeRepo().findById(id);
        if (currencyExchange.isPresent()) {
            currencyExchangeVO = getCurrencyExchangeMapper().entityToVO(currencyExchange.get());
        } else {
            throw new ResourceNotFoundException("currency not found");
        }
        return prepareAppResponse(currencyExchangeVO, null);
    }

    public AppResponse delete(Long id) {
        getCurrencyExchangeRepo().deleteById(id);
        return prepareAppResponse(null, "currency deleted successfully ");
    }

    private AppResponse prepareAppResponse(Object data, String message) {
        AppResponse appResponse = new AppResponse(message);
        appResponse.setData(data);
        appResponse.setResponseDate(new Date());
        appResponse.setHttpStatus(HttpStatus.OK);
        return appResponse;
    }

}
