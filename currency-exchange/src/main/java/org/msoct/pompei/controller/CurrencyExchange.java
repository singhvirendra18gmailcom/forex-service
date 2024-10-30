package org.msoct.pompei.controller;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="EXCHANGE_VALUE")
public class CurrencyExchange {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="CURRENCY_FROM")
	private String from;
	
	@Column(name="CURRENCY_TO")
	private String to;
	
	private BigDecimal rate;
	
	private String port;
	
	public CurrencyExchange() {
		super();
	}
	
	public CurrencyExchange(Long id, String from, String to, BigDecimal rate) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
		this.rate = rate;
	}
	
	public CurrencyExchange(String from, String to, BigDecimal rate) {
		super();
		this.from = from;
		this.to = to;
		this.rate = rate;
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
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	
	public String toString() {
	    
        String str = "";
        // Converts object to json string using GSON
        // Gson gson = new Gson();
        // str = gson.toJson(this);
        
        //Converts object to json string using Jaxson
        ObjectMapper mapper = new ObjectMapper();
        
        try {
            str = mapper.writeValueAsString(this);
            System.out.print(str);
        } catch (Exception exception) {
            System.err.print("abc");
        }
        return str;

}
	
/*
 * public static void main(String[] args) {
 * 
 * CurrencyExchange currencyExchange=new CurrencyExchange(1001L, "YEN",
 * "INR",BigDecimal.valueOf(76L)); System.out.print(currencyExchange); }
 */

}
