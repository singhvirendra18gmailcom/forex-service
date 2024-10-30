package org.msoct.fis;

import java.math.BigDecimal;

public class CurrencyConversionBean {
	private Long id;
	private String from;
	private String to;
	private BigDecimal rate;
	private BigDecimal quantity;
	private BigDecimal totalCalculatedAmount;
	private String port;
	
	
	public CurrencyConversionBean() {
	}
	
	public CurrencyConversionBean(Long id, String from, String to, BigDecimal rate, BigDecimal quantity,
			BigDecimal totalCalculatedAmount, String port) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
		this.setRate(rate);
		this.quantity = quantity;
		this.totalCalculatedAmount = totalCalculatedAmount;
		this.port=port;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	
	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getTotalCalculatedAmount() {
		return totalCalculatedAmount;
	}
	public void setTotalCalculatedAmount(BigDecimal totalCalculatedAmount) {
		this.totalCalculatedAmount = totalCalculatedAmount;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	
	
	
	
	
}

