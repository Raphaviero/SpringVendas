package com.example.raphaviero.vendas.domain.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "TB_PRODUCT")
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id") //Não é necessario se for o mesmo nome da coluna da tabela.
    private Integer id;

    private String name;

    private  String description;

    private BigDecimal price;

    public Product() {
    }

    public Product(Integer id, String description, BigDecimal price, String name) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
