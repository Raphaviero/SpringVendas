package com.example.raphaviero.vendas;

import com.example.raphaviero.vendas.domain.entity.Client;
import com.example.raphaviero.vendas.domain.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Application {


    @Bean
    public CommandLineRunner init(@Autowired ClientRepository clientRepository) {
        return args -> {
            System.out.println("Salvando Clientes");
            clientRepository.saveClient(new Client("Raphael"));
            clientRepository.saveClient(new Client("Joao"));

            List<Client> allClients = clientRepository.listAll();
            allClients.forEach(System.out::println);

            System.out.println(" Atualizando clientes ");
            allClients.forEach(c -> {
                c.setName(c.getName() + " atualizado. ");
                clientRepository.updateClient(c);
            });

            allClients = clientRepository.listAll();
            if (allClients.isEmpty()) {
                System.out.println(" Nenhum cliente encontrado. ");
            } else {
                allClients.forEach(System.out::println);
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
