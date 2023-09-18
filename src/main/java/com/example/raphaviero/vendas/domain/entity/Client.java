package com.example.raphaviero.vendas.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_CLIENT")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    @Column(name = "name", length = 100)
    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String name;

    @Column(name = "cpf", length = 11)
    @NotEmpty(message = "{campo.cpf.obrigatorio}")
    @CPF(message = "{campo.cpf.invalido}")
    private String cpf;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private Set<Order> Orders;

    @JsonIgnore
    public Set<Order> getOrders() {
        return Orders;
    }

    @JsonIgnore
    public void setOrders(Set<Order> orders) {
        Orders = orders;
    }
}





