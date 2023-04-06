package com.demo.skyros.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "CURRENCY_EXCHANGE")
public class CurrencyExchangeEntity implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CURRENCY_EXCHANGE_SEQ")
    @SequenceGenerator(name = "CURRENCY_EXCHANGE_SEQ", sequenceName = "CURRENCY_EXCHANGE_SEQ", allocationSize = 1)
    private Long id;
    @Column(name = "CURRENCY_FROM")
    private String from;
    @Column(name = "CURRENCY_TO")
    private String to;
    @Column(name = "CONVERSION_MULTIPLE")
    private BigDecimal conversionMultiple;
    @Column(name = "ENVIRONMENT")
    private String environment;

}
