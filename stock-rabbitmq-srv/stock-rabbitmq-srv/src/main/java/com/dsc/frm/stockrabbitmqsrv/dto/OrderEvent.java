package com.dsc.frm.stockrabbitmqsrv.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
