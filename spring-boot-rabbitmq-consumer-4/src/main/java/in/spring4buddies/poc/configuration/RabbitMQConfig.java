package in.spring4buddies.poc.configuration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.messaging.handler.annotation.support.MessageHandlerMethodFactory;

@Configuration
public class RabbitMQConfig implements RabbitListenerConfigurer {

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
	public HeadersExchange exchange() {
		return new HeadersExchange(exchange);
	}

	@Bean
	public Binding binding(Queue queue, HeadersExchange exchange) {
		Map<String, Object> headers = new HashMap<>();
		headers.put("Brand", "OLDNAVY");
		headers.put("Market", "US");
		headers.put("Channel", "RTL");
		return BindingBuilder.bind(queue).to(exchange).whereAll(headers).match();
	}

	@Bean
	MessageHandlerMethodFactory messageHandlerMethodFactory() {
		DefaultMessageHandlerMethodFactory messageHandlerMethodFactory = new DefaultMessageHandlerMethodFactory();
		messageHandlerMethodFactory.setMessageConverter(jackson2MessageConverter());
		return messageHandlerMethodFactory;
	}

	@Override
	public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
		registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
	}

	@Bean
	public MappingJackson2MessageConverter jackson2MessageConverter() {
		return new MappingJackson2MessageConverter();
	}

}
