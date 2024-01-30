package com.dsc.frm.orderrabbitmqsrv.controller;

import com.dsc.frm.orderrabbitmqsrv.dto.Order;
import com.dsc.frm.orderrabbitmqsrv.dto.OrderEvent;
import com.dsc.frm.orderrabbitmqsrv.publisher.OrderProducer;
import com.dsc.frm.orderrabbitmqsrv.publisher.OrderStockProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author DSchneider
 */
@RestController
@RequestMapping("/api/v1")
public class OrderController {
    private OrderProducer orderProducer;

    private OrderStockProducer orderStockProducer;

    //AUTRE PBTE / FAIRE UN 2ND CONTROLLER DEDICATED POUR STOCK

    public OrderController(OrderProducer orderProducer, OrderStockProducer orderStockProducer) {
        this.orderProducer = orderProducer;
        this.orderStockProducer = orderStockProducer;
    }

    @PostMapping("/orders")
    public String sendOrder(@RequestBody Order order){
        order.setOrderId(UUID.randomUUID().toString());

        OrderEvent event = new OrderEvent();
        event.setStatus("PENDING");
        event.setMessage("receive email Order : pending");
        event.setOrder(order);

        orderProducer.sendMessage(event);
        return "Order sent to rabbitmq with routing key EMail.";
    }

    @PostMapping("/stockorders")
    public String sendstockOrder(@RequestBody Order order){
        order.setOrderId(UUID.randomUUID().toString());

        OrderEvent event = new OrderEvent();
        event.setStatus("PENDING");
        event.setMessage("receive stock Order : pending");
        event.setOrder(order);

        orderStockProducer.sendStockMessage(event);
        return "Order sent to rabbitmq with routing key STOCK.";
    }

}
