package com.example.raphaviero.vendas.Services.impl;

import com.example.raphaviero.vendas.Services.OrderService;
import com.example.raphaviero.vendas.domain.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


}
