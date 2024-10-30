/*package org.msoct.pompei.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class ExchangeRatesConsumer {
	
	private Logger logger=LoggerFactory.getLogger(ExchangeRatesConsumer.class);
	
	@Autowired
	KafkaTemplate<String, CurrencyRatesEvent> kafkaTemplate;
	
	@Autowired
	CurrencyExchangeService currencyExchangeService;
	
	@KafkaListener(topics="test-topic", groupId ="group-id")
	public boolean consume(String event) throws JsonMappingException, JsonProcessingException {
	
		logger.info("Prcocessing rates started at : " + new java.util.Date());
		
	
		CurrencyRatesEvent currencyRatesEvent=new ObjectMapper().readValue(event,CurrencyRatesEvent.class);
		logger.info("Exchange rate consumer - kafka listener invoked for  " + currencyRatesEvent.getSource());
		List<CurrencyExchange> currencyExchanges=new ArrayList<>();
		for(CurrencyRate currencyRate : currencyRatesEvent.getCurrencyRates()) {
			currencyExchanges.add(
					new CurrencyExchange(currencyRate.getFrom(), currencyRate.getTo(), currencyRate.getRate()));
		}
		
		boolean saved= currencyExchangeService.saveRates(currencyExchanges);
		
		System.out.println("Prcocessing rates finished : " + new java.util.Date());
		
		return saved;
	
	}
}*/
