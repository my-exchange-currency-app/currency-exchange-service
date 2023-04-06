package com.demo.skyros.vo;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CurrencyExchangeVO implements Serializable {

    private Long id;
    private String from;
    private String to;
    private BigDecimal conversionMultiple;
    private String environment;

}
