package org.msoct.fis;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CurrencyConversionController {
	

	@Autowired
	private CurrencyExchangeServiceProxy  proxy;
	
	@Autowired
	ExchangeRatesProducer exchangeRatesProducer;
	
	private Logger logger=LoggerFactory.getLogger(CurrencyConversionController.class);
	
   	//@CircuitBreaker(name="rest-template-", fallbackMethod = "conversionfallback")
	//@Retry(name="rest-template-retry", fallbackMethod = "conversionfallback"
	
	//@RateLimiter(name="rest-template-rl", fallbackMethod = "conversionfallback")
	//@Bulkhead
	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean conversion(@PathVariable String from, @PathVariable String to,@PathVariable BigDecimal quantity) {
		
		logger.info("call recieved at CurrencyConversionController.. " + LocalTime.now());
		
		RestTemplate restTemplate=new RestTemplate();	
		
		Map<String,String> urlVariables=new HashMap<>();
		urlVariables.put("from", from);
		urlVariables.put("to", to);
		ResponseEntity<CurrencyConversionBean> responseEntity=restTemplate.getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", 
				CurrencyConversionBean.class, urlVariables);
		CurrencyConversionBean currencyConversionBean=responseEntity.getBody();
		
		return new CurrencyConversionBean(currencyConversionBean.getId(), 
				from, to, currencyConversionBean.getRate(), quantity, 
				quantity.multiply(currencyConversionBean.getRate()), currencyConversionBean.getPort() + " rest template");	
		
	}
	
	public CurrencyConversionBean conversionfallback(String from, String to, BigDecimal quantity,Exception e) {
		return new CurrencyConversionBean(9999L, 
				from, to, BigDecimal.valueOf(80L), quantity,
				quantity.multiply(BigDecimal.valueOf(80L)), "9999" + " RestTemplate-fallback");
		
	
	}
	
	
	@GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean conversionFeign(@PathVariable String from, @PathVariable String to,@PathVariable BigDecimal quantity) {
		
		CurrencyConversionBean currencyConversionBean=proxy.exchange(from, to);
		
		return new CurrencyConversionBean(currencyConversionBean.getId(), 
				from, to, currencyConversionBean.getRate(), quantity, 
				quantity.multiply(currencyConversionBean.getRate()), currencyConversionBean.getPort() + " Feign" );	
		
	}
	
	@PostMapping("/exchange")
	public ResponseEntity<Boolean> saveCurrencyRates(@RequestBody List<CurrencyConversionBean> currencyConversionBeans) {	
		System.out.print("save currency rates called." + currencyConversionBeans);
		exchangeRatesProducer.produce("external",currencyConversionBeans);
		return ResponseEntity.ok(Boolean.TRUE);
		
	}

	
	
	
}
