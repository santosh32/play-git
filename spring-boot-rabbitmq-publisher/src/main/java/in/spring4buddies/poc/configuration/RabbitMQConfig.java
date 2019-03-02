package in.spring4buddies.poc.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

	@Value("${poc.rabbitmq.queue}")
	private String queueName;

	@Value("${poc.rabbitmq.exchange}")
	private String exchange;

	@Value("${poc.rabbitmq.routingkey}")
	private String routingKey;

	@Bean
	public Queue queue() {
		return new Queue(queueName, false);
	}

	@Bean
	public DirectExchange exchange() {
		return new DirectExchange(exchange);
	}

	@Bean
	public Binding binding(Queue queue, DirectExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(routingKey);
	}

	@Bean
	public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
		return rabbitTemplate;
	}

	@Bean
	public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

}
