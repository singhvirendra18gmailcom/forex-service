package org.msoct.pompei.controller;
import java.util.concurrent.CompletableFuture;
import org.msoct.pompei.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderControler {
	
	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private KafkaTemplate<String, OrderEvent> kafkaTemplate;

	@PostMapping("/orders")
	public void order(@RequestBody CustomerOrder customerOrder) {

		Order order = new Order();
		order.setItem(customerOrder.getItem());
		order.setAmount(customerOrder.getAmount());
		order.setQuantity(customerOrder.getQuantity());
		order.setStatus("CREATED");
		
		try {
		order=repository.save(order);
		
		customerOrder.setOrderId(order.getId());
		OrderEvent orderEvent =new OrderEvent();
		orderEvent.setCustomerOrder(customerOrder);
		orderEvent.setType("ORDER CREATED");
		
		CompletableFuture<SendResult<String, OrderEvent>> future = kafkaTemplate.send("new-order",orderEvent);
		future.whenComplete((result,ex) ->{
			if(ex==null) {
				System.out.println(orderEvent.getType() + "is sent");
			}else {
				System.out.println(orderEvent.getType() + "is not sent");
			}
		});
		}catch(Exception e) {
			order.setStatus("FAILED");
			repository.save(order);
		}
		
	}
}
