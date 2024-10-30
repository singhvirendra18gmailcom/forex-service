package org.msoct.fis;

import java.util.ArrayList;
import java.util.List;

public class CurrencyRatesEvent {


	String source;
	
	List<CurrencyRate> currencyRates=new ArrayList<>(1);

	public List<CurrencyRate> getCurrencyRates() {
		return currencyRates;
	}

	public void setCurrencyRates(List<CurrencyRate> currencyRates) {
		this.currencyRates = currencyRates;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}



}
