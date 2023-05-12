package com.web.spring.cloud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.web.spring.cloud.model.CurrencyExchange;

//@FeignClient(name = "currency-exchange",url = "localhost:8000" )
@FeignClient(name = "currency-exchange")
public interface CurrencyExchangeClient {
	@RequestMapping(value = "/currencyExchange/currency-exchange/from/{from}/to/{to}", method = RequestMethod.GET)
	public CurrencyExchange retirveCurrencyExchange(@PathVariable String from, @PathVariable String to);

}
