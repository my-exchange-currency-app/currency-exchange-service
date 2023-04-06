package com.demo.skyros.vo;

import lombok.*;

import java.math.BigDecimal;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CurrencyExchangeVO {

    private Long id;
    private String from;
    private String to;
    private BigDecimal conversionMultiple;

}
