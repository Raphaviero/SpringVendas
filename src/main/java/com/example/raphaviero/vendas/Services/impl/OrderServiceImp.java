package com.example.raphaviero.vendas.Services.impl;

import com.example.raphaviero.vendas.Services.OrderService;
import com.example.raphaviero.vendas.domain.entity.Client;
import com.example.raphaviero.vendas.domain.entity.ItemOrder;
import com.example.raphaviero.vendas.domain.entity.Order;
import com.example.raphaviero.vendas.domain.entity.Product;
import com.example.raphaviero.vendas.domain.repository.ClientRepository;
import com.example.raphaviero.vendas.domain.repository.ItemOrderRepositoy;
import com.example.raphaviero.vendas.domain.repository.OrderRepository;
import com.example.raphaviero.vendas.domain.repository.ProductRepository;
import com.example.raphaviero.vendas.exception.BusinessRuleException;
import com.example.raphaviero.vendas.domain.rest.dto.ItemOrderDTO;
import com.example.raphaviero.vendas.domain.rest.dto.OrderDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImp implements OrderService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;
    private final ItemOrderRepositoy itemOrderRepositoy;


    @Override
    @Transactional
    public Order save(OrderDTO dto) {
        Integer idClient = dto.getClient();
        Client client = clientRepository
                .findById(idClient)
                .orElseThrow(() -> new BusinessRuleException("Código do cliente inválido: " + idClient));

        Order order = new Order();
        order.setTotalPrice(dto.getTotal());
        order.setDateOrder(LocalDate.now());
        order.setClient(client);

        List<ItemOrder> itemOrderList = convertItems(order, dto.getItems());
        orderRepository.save(order);
        itemOrderRepositoy.saveAll(itemOrderList);
        order.setItemOrderList(itemOrderList);

        return order;
    }

    @Override
    public Optional<Order> takeCompleteOrder(Integer id) {
        return orderRepository.findByIdFetchitemOrderList(id);
    }

    private List<ItemOrder> convertItems(Order order, List<ItemOrderDTO> items) {
        if (items.isEmpty()) {
            throw new BusinessRuleException("Não é possivel realizar um pedido sem itens.");
        }
        return items
                .stream()
                .map(dto -> {
            Integer idProduct = dto.getProduct();
            Product product = productRepository
                    .findById(idProduct)
                    .orElseThrow(() -> new BusinessRuleException("Codigo do produto inválido: " + idProduct));
            ;


            ItemOrder itemOrder = new ItemOrder();
            itemOrder.setQuantity(dto.getQuantity());
            itemOrder.setOrder(order);
            itemOrder.setProduct(product);
            return itemOrder;

        }).collect(Collectors.toList());
    }
}
