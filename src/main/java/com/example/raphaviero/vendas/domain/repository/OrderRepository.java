package com.example.raphaviero.vendas.domain.repository;

import com.example.raphaviero.vendas.domain.entity.Client;
import com.example.raphaviero.vendas.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByClient(Client client);

}
