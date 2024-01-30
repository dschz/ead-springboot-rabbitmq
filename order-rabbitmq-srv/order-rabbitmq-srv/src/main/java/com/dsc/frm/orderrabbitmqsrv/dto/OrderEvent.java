package com.dsc.frm.orderrabbitmqsrv.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author DSchneider
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderEvent {
    private String status;//PENDING,PROGRESS,COMPLETED
    private String message;
    private Order order;
}
