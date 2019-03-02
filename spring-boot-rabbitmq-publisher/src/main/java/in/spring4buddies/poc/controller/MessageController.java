package in.spring4buddies.poc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.spring4buddies.poc.data.Order;
import in.spring4buddies.poc.publisher.MessagePublisher;

@RestController
public class MessageController {

	@Autowired
	private MessagePublisher publisher;

	@PostMapping(value = "/sendMessage")
	public void handleMessage(@RequestBody Order order) {
		publisher.publish(order);
	}
}