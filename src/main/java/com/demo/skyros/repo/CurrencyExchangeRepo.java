package com.demo.skyros.repo;

import com.demo.skyros.entity.CurrencyExchangeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyExchangeRepo extends JpaRepository<CurrencyExchangeEntity, Long> {
    CurrencyExchangeEntity findByFromAndTo(String from, String to);
}
