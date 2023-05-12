package com.web.spring.cloud.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.web.spring.cloud.CurrencyExchangeClient;
import com.web.spring.cloud.model.CurrencyConversion;
import com.web.spring.cloud.model.CurrencyExchange;

@RestController

public class CurrencyConversionController {
	@Autowired
	private CurrencyExchangeClient client;

	// localhost:8100/currency-conversion/from/USD/to/INR/quantity/2
	@RequestMapping(value = "/currency-conversion/from/{from}/to/{to}/quantity/{quantity}", method = RequestMethod.GET)
	public CurrencyConversion calculateCurrencyConversion(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		Map<String, String> uriVarible = new HashMap<>();
		uriVarible.put("from", from);
		uriVarible.put("to", to);

		String url = "http://localhost:8000/currencyExchange/currency-exchange/from/{from}/to/{to}";
		ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().getForEntity(url,
				CurrencyConversion.class, uriVarible);
		CurrencyConversion currencyConversion = responseEntity.getBody();
		CurrencyConversion conversion = new CurrencyConversion(currencyConversion.getId(), from, to, quantity,
				currencyConversion.getConversionMultiple(),
				quantity.multiply(currencyConversion.getConversionMultiple()), currencyConversion.getEnv());
		return conversion;
	}

	// localhost:8100/currency-conversion-feigen/from/USD/to/INR/quantity/2

	@RequestMapping(value = "/currency-conversion-feigen/from/{from}/to/{to}/quantity/{quantity}", method = RequestMethod.GET)
	public CurrencyExchange calculateCurrencyConversionUsingFeigen(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {
		CurrencyExchange currencyExchange = client.retirveCurrencyExchange(from, to);
		return currencyExchange;
	}
}
