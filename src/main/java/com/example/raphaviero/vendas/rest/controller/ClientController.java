package com.example.raphaviero.vendas.rest.controller;

import com.example.raphaviero.vendas.domain.entity.Client;
import com.example.raphaviero.vendas.domain.repository.ClientRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ClientController {

    private ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping("/api/clients/{id}")
    @ResponseBody
    public ResponseEntity<Client> getClientById(@PathVariable Integer id) {
        Optional<Client> client = clientRepository.findById(id);

        return client.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/api/clients")
    @ResponseBody
    public ResponseEntity saveClient(@RequestBody Client client) {
        Client clientSave = clientRepository.save(client);
        return ResponseEntity.ok(clientSave);
    }

    @DeleteMapping("/api/clients/{id}")
    @ResponseBody
    public ResponseEntity delete(@PathVariable Integer id) {
        Optional<Client> clientDelete = clientRepository.findById(id);

        if (clientDelete.isPresent()) {
            clientRepository.delete(clientDelete.get());
            return ResponseEntity.noContent().build();
        } else
            return ResponseEntity.notFound().build();
    }

    @PutMapping("/api/clients/{id}")
    @ResponseBody
    public ResponseEntity updateClient(@PathVariable Integer id,
                                       @RequestBody Client client) {

        return clientRepository.findById(id).map(clientExist -> {
            client.setId(clientExist.getId());
            clientRepository.save(client);
            return ResponseEntity.noContent().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @GetMapping("/api/clients")
    @ResponseBody
    public ResponseEntity findAll(Client listClient) {
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(listClient, matcher);
        List<Client> list = clientRepository.findAll(example);
        return ResponseEntity.ok(list);
    }


}
