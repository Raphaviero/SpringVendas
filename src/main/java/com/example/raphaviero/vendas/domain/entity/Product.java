package com.example.raphaviero.vendas.domain.entity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_PRODUCT")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id") //Não é necessario se for o mesmo nome da coluna da tabela.
    private Integer id;

    private String name;

    private String description;

    private BigDecimal price;

}
