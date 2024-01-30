package com.dsc.frm.stockrabbitmqsrv.consumer;

import com.dsc.frm.stockrabbitmqsrv.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author DSchneider
 */
@Service
public class OrderConsumer {
    private static Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);

    @RabbitListener(queues = "${rabbitmq.queue.order.stock}")
    public void consume(OrderEvent event) {
        LOGGER.info(String.format("Received stock message : %s", event.toString()));

        //todo save order in db no managed
    }
}
