package com.web.spring.cloud.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.web.spring.cloud.model.CurrencyExchange;
import com.web.spring.cloud.respository.CurrencyExchangeReposistory;

@RestController
@RequestMapping(value = "/currencyExchange")
public class CurrencyExchangeController {
	@Autowired
	private CurrencyExchangeReposistory currencyExchangeReposistory;
	@Autowired
	private Environment environment;

//localhost:8000/currencyExchange/greedy
	@RequestMapping(value = "/greedy", method = RequestMethod.GET)
	public String greedy() {
		return "welcome to Currency Exchange Service";
	}
	//localhost:8000/currencyExchange/currency-exchange/from/USD/to/INR
	@GetMapping(value = "/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retirveCurrencyExchange(@PathVariable String from, @PathVariable String to) {
		CurrencyExchange currencyExchange = currencyExchangeReposistory.findByFromAndTo(from, to);
		String port = environment.getProperty("local.server.port");
		currencyExchange.setEnv(port);
		return currencyExchange;
	}
}
