package com.demo.skyros.mapper;


import com.demo.skyros.entity.CurrencyExchangeEntity;
import com.demo.skyros.vo.CurrencyExchangeVO;
import org.mapstruct.Mapper;

@Mapper
public interface CurrencyExchangeMapper extends CommonMapper<CurrencyExchangeEntity, CurrencyExchangeVO> {

}
