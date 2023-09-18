package com.example.raphaviero.vendas.exception;

public class OrderNotFoundException extends RuntimeException{

    public OrderNotFoundException (){super("Pedido não encontrado.");}
}
