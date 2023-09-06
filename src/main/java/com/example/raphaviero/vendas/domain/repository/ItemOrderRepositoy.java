package com.example.raphaviero.vendas.domain.repository;

import com.example.raphaviero.vendas.domain.entity.ItemOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemOrderRepositoy  extends JpaRepository<ItemOrder, Integer> {

}
