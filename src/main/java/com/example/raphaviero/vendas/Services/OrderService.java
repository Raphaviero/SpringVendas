package com.example.raphaviero.vendas.Services;

import com.example.raphaviero.vendas.domain.entity.Order;
import com.example.raphaviero.vendas.domain.enums.OrderStatus;
import com.example.raphaviero.vendas.domain.rest.dto.OrderDTO;

import java.util.Optional;

public interface OrderService {

    Order save (OrderDTO dto);
    Optional<Order> takeCompleteOrder (Integer id);
    void updateStatus(Integer id, OrderStatus orderStatus);



}
