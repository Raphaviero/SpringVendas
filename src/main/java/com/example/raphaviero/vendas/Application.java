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
	public CommandLineRunner init(@Autowired ClientRepository clientRepository){
		return  args -> {
			clientRepository.saveClient(new Client("Raphael"));
			clientRepository.saveClient(new Client("Joao"));

			List<Client> allClients = clientRepository.listAll();
			allClients.forEach(System.out::println);

			allClients.forEach(c -> {
				c.setName(c.getName()+ "atualizado.");
				clientRepository.updateClient(c);
			});
		};
	}


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}



}
