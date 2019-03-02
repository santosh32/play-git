package in.spring4buddies.poc.subcriber;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import in.spring4buddies.poc.data.Order;

@Component
public class Subcriber {

	@RabbitListener(queues = "${poc.rabbitmq.queue}")
	public void recievedMessage(Order order) {
		System.out.println("Recieved Message: " + order);
	}
}
