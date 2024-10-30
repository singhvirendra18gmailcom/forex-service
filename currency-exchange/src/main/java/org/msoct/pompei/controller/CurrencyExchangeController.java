package org.msoct.pompei.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

	private Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);

	@Autowired
	CurrencyExchangeRepository repository;

	@Autowired
	private Environment environment;

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange exchange(@PathVariable String from, @PathVariable String to) {

		logger.info(" exchange method called with from {} to {}", from, to);

		CurrencyExchange ex = repository.findByFromAndTo(from, to);
		ex.setPort(environment.getProperty("local.server.port"));
		return ex;
	}

/*	@PostMapping("/currency-exchange")
	public ResponseEntity<Boolean> saveCurrencyRates(@RequestBody List<CurrencyExchange> currencyExchanges) {
		logger.info("save Currency Rates called");

		repository.saveAll(currencyExchanges);
		return ResponseEntity.ok(Boolean.TRUE);

	}
	*/
}
