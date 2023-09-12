package com.example.raphaviero.vendas.domain.repository;

import com.example.raphaviero.vendas.domain.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ClientRepository extends JpaRepository<Client, Integer> {

    @Query(value = " SELECT c FROM Client c WHERE c.name = :name ")
    List<Client> encontrarPorNome (@Param("name") String name);

    Boolean existsByName(String name);

    @Query(" select c from Client c left join fetch c.Orders where c.id = :id ")
    Client findClientFetchOrders(@Param("id") Integer id);





}

