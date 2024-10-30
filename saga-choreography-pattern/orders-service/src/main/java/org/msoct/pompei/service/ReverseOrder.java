package org.msoct.pompei.service;

import java.util.Optional;

import org.msoct.pompei.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ReverseOrder {

	@Autowired
	private OrderRepository orderRepository;

	@KafkaListener(topics = "reveresed-order", groupId = "payments-group")
	public void reverseOrder(String event) {
		System.out.print("Reverse Order Event" + event);

		try {

			OrderEvent orderEvent = new ObjectMapper().readValue(event, OrderEvent.class);
			Optional<Order> order = orderRepository.findById(orderEvent.getCustomerOrder().getOrderId());
			order.ifPresent(o -> {
				o.setStatus("FAILED");
				orderRepository.save(o);
			});
		} catch (Exception e) {
			System.out.print("Exception while reverting order details");
		}

	}
}
