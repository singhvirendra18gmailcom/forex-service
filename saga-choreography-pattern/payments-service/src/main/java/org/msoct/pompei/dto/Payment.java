package org.msoct.pompei.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PAYMENT_TABLE")
public class Payment {

	private long id;

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	@Column
	private String mode;

	@Column
	private double amount;

	@Column
	private String status;

	@Column
	private long orderId;

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}



	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
}
