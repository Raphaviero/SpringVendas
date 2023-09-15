package com.example.raphaviero.vendas.domain.repository;

import com.example.raphaviero.vendas.domain.entity.Client;
import com.example.raphaviero.vendas.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByClient(Client client);


    @Query(" select p from Order p left join fetch p.itemOrderList where p.id = :id ")
    Optional <Order> findByIdFetchitemOrderList(@Param("id") Integer id);
}
