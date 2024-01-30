package com.dsc.frm.orderrabbitmqsrv.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author DSchneider
 */
@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.exchange.name}")
    private String orderExchange;

    @Value("${rabbitmq.queue.order.email}")
    private String emailQueue;

    @Value("${rabbitmq.queue.order.stock}")
    private String stockQueue;

    @Value("${rabbitmq.binding.routing.key.email}")
    private String routingKeyEmail;

    @Value("${rabbitmq.binding.routing.key.stock}")
    private String routingKeyStock;

    //spring bean for queue order
    @Bean
    public Queue orderQueue() {
        return new Queue(emailQueue);
    }

    //spring bean for queue order
    @Bean
    public Queue orderStockQueue() {
        return new Queue(stockQueue);
    }

    //spring bean for exchange
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(orderExchange);
    }
    //spring bean for binding exchange to queue email
    @Bean public Binding binding() {
        return BindingBuilder.bind(orderQueue()).to(exchange()).with(routingKeyEmail);
    }

    //spring bean for binding exchange to queue stock
    @Bean public Binding stockBinding() {
        return BindingBuilder.bind(orderStockQueue()).to(exchange()).with(routingKeyStock);
    }

    //message json converter
    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    //conf rabbit template
    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
