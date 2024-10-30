package org.msoct.pompei.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyExchangeService {

	
	@Autowired
	CurrencyExchangeRepository repository;
	
	
	public boolean saveRates(List<CurrencyExchange> currencyExchanges) {
		repository.saveAll(currencyExchanges);
		return true;
		
		
	}
}
