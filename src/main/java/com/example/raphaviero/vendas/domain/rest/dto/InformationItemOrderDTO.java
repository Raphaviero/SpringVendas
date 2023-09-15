package com.example.raphaviero.vendas.domain.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformationItemOrderDTO {

    private String description;
    private BigDecimal unityPrice;
    private Integer quantity;


}
