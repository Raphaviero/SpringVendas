package com.example.raphaviero.vendas.domain.entity;

import com.example.raphaviero.vendas.domain.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "order_date")
    private LocalDate dateOrder;

    @Column(name = "total", precision = 50, scale = 2)
    private BigDecimal totalPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "Status")
    private OrderStatus orderStatus;


    @OneToMany(mappedBy = "order")
    private List<ItemOrder> itemOrderList;
}
