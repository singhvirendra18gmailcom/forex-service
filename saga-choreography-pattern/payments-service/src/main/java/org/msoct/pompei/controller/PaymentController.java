package org.msoct.pompei.controller;

import org.msoct.pompei.dto.CustomerOrder;
import org.msoct.pompei.dto.OrderEvent;
import org.msoct.pompei.dto.Payment;
import org.msoct.pompei.dto.PaymentEvent;
import org.msoct.pompei.dto.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class PaymentController {

	@Autowired
	PaymentRepository paymentRepository;

	/*
	 * @Autowired KafkaTemplate<String, PaymentEvent> kafkaTemplate;
	 */

	@Autowired
	KafkaTemplate<String, OrderEvent> kafkaTemplate;

	@KafkaListener(topics = "new-order", groupId = "orders-group")
	public void processPayment(String event) throws Exception {

		System.out.println("processing payment for order event" + event);
		OrderEvent orderEvent = new ObjectMapper().readValue(event, OrderEvent.class);
		CustomerOrder customerOrder = orderEvent.getCustomerOrder();
		Payment payment = new Payment();
		payment.setAmount(customerOrder.getAmount());
		payment.setMode(customerOrder.getPaymentMethod());
		payment.setOrderId(customerOrder.getOrderId());
		payment.setStatus("SUCCESS");

		try {

			// orderKafkaTemplate.send("reversed-orders", orderEvent);
        // throw new RuntimeException("testing roll back");
			
			paymentRepository.save(payment); 
			 OrderEvent oe = new OrderEvent();
			 oe.setCustomerOrder(customerOrder);
			 oe.setType("SUCCESS"); 
			 kafkaTemplate.send("processed-order",oe);
		

		} catch (Exception e) {
			payment.setOrderId(customerOrder.getOrderId());
			payment.setStatus("FAILED");
			paymentRepository.save(payment);
			OrderEvent oe = new OrderEvent();
			oe.setCustomerOrder(customerOrder);
			oe.setType("ORDER_REVERSED");
			kafkaTemplate.send("reveresed-order", oe);
		}

	}

	private void testRollback() {
		// TODO Auto-generated method stub

	}

}
