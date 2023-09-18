package com.example.raphaviero.vendas.domain.rest.controller;

import com.example.raphaviero.vendas.Services.OrderService;
import com.example.raphaviero.vendas.domain.entity.ItemOrder;
import com.example.raphaviero.vendas.domain.entity.Order;
import com.example.raphaviero.vendas.domain.enums.OrderStatus;
import com.example.raphaviero.vendas.domain.rest.dto.InformationItemOrderDTO;
import com.example.raphaviero.vendas.domain.rest.dto.InformationOrderDTO;
import com.example.raphaviero.vendas.domain.rest.dto.OrderDTO;
import com.example.raphaviero.vendas.domain.rest.dto.StatusUpdateDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/orders")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody @Valid OrderDTO orderDTO) {
        Order order = orderService.save(orderDTO);
        return order.getId();
    }

    @GetMapping("{id}")
    public InformationOrderDTO getById(@PathVariable Integer id) {
        return orderService.takeCompleteOrder(id)
                .map(p -> convert(p))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void updateStatus(@RequestBody @Valid StatusUpdateDTO dto,
                              @PathVariable Integer id){
        String newStatus = dto.getNewStatus();
        orderService.updateStatus(id, OrderStatus.valueOf(newStatus));

    }

    private InformationOrderDTO convert(Order order) {
        return InformationOrderDTO
                .builder()
                .code(order.getId())
                .date(order.getDateOrder().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(order.getClient().getCpf())
                .clientName(order.getClient().getName())
                .total(order.getTotalPrice())
                .status(order.getOrderStatus().name())
                .items(converter(order.getItemOrderList()))
                .build();
    }

    private List<InformationItemOrderDTO> converter(List<ItemOrder> items) {
        if (items.isEmpty()) {
            return Collections.emptyList();
        }

        return items
                .stream()
                .map(item -> InformationItemOrderDTO
                        .builder()
                        .description(item.getProduct().getDescription())
                        .unityPrice(item.getProduct().getPrice()).quantity(item.getQuantity())
                        .build()).collect(Collectors.toList());

    }


}