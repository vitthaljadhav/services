package com.web.spring.cloud.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.spring.cloud.model.CurrencyExchange;

@Repository
public interface CurrencyExchangeReposistory extends JpaRepository<CurrencyExchange, Long> {

	public CurrencyExchange findByFromAndTo(String from, String to);

}
