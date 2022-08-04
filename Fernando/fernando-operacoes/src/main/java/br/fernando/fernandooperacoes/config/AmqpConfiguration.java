package br.fernando.fernandooperacoes.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfiguration {

	@Value("${amqp.username}")
	private String userName;

	@Value("${amqp.password}")
	private String password;

	@Value("${amqp.virtualHost}")
	private String virtualHost;

	@Value("${amqp.host}")
	private String host;

	@Value("${amqp.port}")
	private String port;

	@Value("${amqp.uri}")
	private String uri;
	
	@Value("${thanos.fila.autor.queue}")
	private String filaAutorQueue;

	@Value("${thanos.direct.exchange.estudos.operacao}")
	private String directExchange;

	@Bean
	public ConnectionFactory jmsConnectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setUsername(userName);
		connectionFactory.setPassword(password);
		connectionFactory.setVirtualHost(virtualHost);
		connectionFactory.setPort(Integer.valueOf(port));
		connectionFactory.setUri(uri);
		connectionFactory.setHost(host);
		return connectionFactory;
	}

	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		return rabbitTemplate;
	}

	@Bean
	public SimpleRabbitListenerContainerFactory tenantRabbitListenerContainerFactory(
			SimpleRabbitListenerContainerFactoryConfigurer configurer, ConnectionFactory connectionFactory) {
		SimpleRabbitListenerContainerFactory f = new SimpleRabbitListenerContainerFactory();
		configurer.configure(f, connectionFactory);
		return f;
	}

	@Bean
	public Queue filaAutorQueue() {
		return new Queue(filaAutorQueue);
	}

	@Bean
	public DirectExchange exchange() {
		return new DirectExchange(directExchange);
	}
	
	@Bean
	Binding bindingfilaAutorQueue() {
		return BindingBuilder.bind(filaAutorQueue()).to(exchange()).with("filaAutorQueue");
	}	

}
