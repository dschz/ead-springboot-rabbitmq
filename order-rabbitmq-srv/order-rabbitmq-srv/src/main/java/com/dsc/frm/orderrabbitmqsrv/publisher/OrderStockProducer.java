package com.dsc.frm.orderrabbitmqsrv.publisher;

import com.dsc.frm.orderrabbitmqsrv.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author DSchneider
 */
@Service
public class OrderStockProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderStockProducer.class);

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.binding.routing.key.stock}")
    private String stockRoutingKey;

    private RabbitTemplate rabbitTemplate;

    public OrderStockProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendStockMessage(OrderEvent event) {
        LOGGER.info(String.format("Sending message order : %s", event));
        rabbitTemplate.convertAndSend(exchange, stockRoutingKey, event);
    }
}
