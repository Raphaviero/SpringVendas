package com.example.raphaviero.vendas.domain.repository;

import com.example.raphaviero.vendas.domain.entity.Client;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ClientRepository {


    @Autowired
    private EntityManager entityManager;

    @Transactional
    public Client saveClient(Client client) {
        entityManager.persist(client);
        return client;
    }

    @Transactional
    public Client updateClient(Client client) {
        entityManager.merge(client);
        return client;
    }

    @Transactional
    public void deleteClient(Client client) {
        if (!entityManager.contains(client)) {

            client = entityManager.merge(client);
        }
        entityManager.remove(client);
    }

    @Transactional
    public void deleteClientById(Integer id) {
        Client client = entityManager.find(Client.class, id);
        deleteClient(client);
    }

    @Transactional
    public List<Client> findByName(String name) {
        String jpql = " selec c from Client c where c.name like = :name ";
        TypedQuery<Client> query = entityManager.createQuery(jpql, Client.class);
        query.setParameter("name", "%" + name + "%");
        return query.getResultList();
    }

    @Transactional
    public List<Client> listAll() {
        return entityManager.createQuery("from Client", Client.class).getResultList();
    }

}

