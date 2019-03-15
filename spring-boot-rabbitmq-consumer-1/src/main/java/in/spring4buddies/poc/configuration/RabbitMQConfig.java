package in.spring4buddies.poc.configuration;

import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.messaging.handler.annotation.support.MessageHandlerMethodFactory;

@ConfigurationProperties(prefix = "poc.rabbitmq")
@Configuration
public class RabbitMQConfig implements RabbitListenerConfigurer {

	@Value("${poc.rabbitmq.queue}")
	private String queue;

	@Value("${poc.rabbitmq.exchange}")
	private String exchange;

	@Value("${poc.rabbitmq.routingkey}")
	private String routingKey;

	private Map<String, Object> headers;

	public Map<String, Object> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, Object> headers) {
		this.headers = headers;
	}

	@Bean
	public Queue queue() {
		return new Queue(queue, false);
	}

	@Bean
	public HeadersExchange exchange() {
		return new HeadersExchange(exchange);
	}

	@Bean
	public Binding binding(Queue queue, Exchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(routingKey).and(headers);
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
