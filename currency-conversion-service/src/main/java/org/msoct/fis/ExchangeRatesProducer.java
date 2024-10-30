package org.msoct.fis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ExchangeRatesProducer {
	
	public static final String TOPIC = "test-topic";
	
	@Autowired
	KafkaTemplate<String, CurrencyRatesEvent> kafkaTemplate;
	
	public void produce(String source, List<CurrencyConversionBean> currencyConversionBeans) {
		
		
		CurrencyRatesEvent currencyRatesEvent = new CurrencyRatesEvent();
		currencyRatesEvent.setSource(source);
		
		for(CurrencyConversionBean csb :currencyConversionBeans) {
			currencyRatesEvent.getCurrencyRates()
					.add(new CurrencyRate(csb.getFrom(), csb.getTo(), csb.getRate()));
		}
		
		for(CurrencyRate currencyRate: currencyRatesEvent.getCurrencyRates()) {
			System.out.println(currencyRate);
		}
		
		this.kafkaTemplate.send(TOPIC, currencyRatesEvent);
		
	}
	

}
