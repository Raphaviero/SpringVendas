package com.example.raphaviero.vendas;

import com.example.raphaviero.vendas.domain.entity.Client;
import com.example.raphaviero.vendas.domain.entity.Order;
import com.example.raphaviero.vendas.domain.repository.ClientRepository;
import com.example.raphaviero.vendas.domain.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@SpringBootApplication
public class Application {

    @Bean
    public CommandLineRunner init(

            @Autowired ClientRepository clientRepository,
            @Autowired OrderRepository orderRepository) {

        return args -> {

            System.out.println("Salvando Clientes");
            Client c1 = new Client("Raphael");
            clientRepository.save(c1);

            Order order1 = new Order();
            order1.setClient(c1);
            order1.setDateOrder(LocalDate.now());
            order1.setTotalPrice(BigDecimal.valueOf(100));

            orderRepository.save(order1);

            //  Client c2 = clientRepository.findClientFetchOrders(c1.getId());
            //   System.out.println(c2);
            //   System.out.println(c2.getOrders());

            orderRepository.findByClient(c1).forEach(System.out::println);

        };

    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
