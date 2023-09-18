package com.example.raphaviero.vendas.domain.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformationOrderDTO {

    private Integer code;
    private String date;
    private String cpf;
    private String clientName;
    private BigDecimal total;

    @JsonIgnore
    private String OrderDate;

    private String status;
    private List<InformationItemOrderDTO> items;

}
