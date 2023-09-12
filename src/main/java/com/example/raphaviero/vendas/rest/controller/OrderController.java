package com.example.raphaviero.vendas.rest.controller;

import com.example.raphaviero.vendas.Services.OrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/orders")
public class OrderController {

private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


}
