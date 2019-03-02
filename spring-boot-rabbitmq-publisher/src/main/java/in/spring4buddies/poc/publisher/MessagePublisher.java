package in.spring4buddies.poc.publisher;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import in.spring4buddies.poc.data.Order;

@Component
public class MessagePublisher {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Value("${poc.rabbitmq.exchange}")
	private String exchange;

	@Value("${poc.rabbitmq.routingkey}")
	private String routingKey;

	public void publish(Order order) {
		rabbitTemplate.convertAndSend(exchange, routingKey, order);
		System.out.println("Send msg = " + order);
	}
}
