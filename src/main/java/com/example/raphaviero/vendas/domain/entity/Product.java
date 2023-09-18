package com.example.raphaviero.vendas.domain.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

    @NotEmpty(message = "{campo.descricao.obrigatorio}")
    private String description;

    @NotNull(message = "{campo.preco.obrigatorio}")
    private BigDecimal price;

}
