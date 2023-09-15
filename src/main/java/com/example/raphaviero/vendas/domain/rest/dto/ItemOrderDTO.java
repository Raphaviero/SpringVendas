package com.example.raphaviero.vendas.domain.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemOrderDTO {

    private Integer product;
    private Integer quantity;
}
