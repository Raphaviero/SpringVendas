package com.example.raphaviero.vendas.domain.repository;

import com.example.raphaviero.vendas.domain.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ClientRepository {

    private static String INSERT = "INSERT INTO TB_CLIENT (NAME) VALUES (?)";
    private static String SELECT_ALL = "SELECT * FROM TB_CLIENT";
    private static String UPDATE = "UPDATE TB_CLIENT  SET NAME = ? WHERE ID = ?";
    private static String DELETE = "DELETE FROM TB_CLIENT WHERE ID = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Client saveClient(Client client){
        jdbcTemplate.update(INSERT, new Object[]{client.getName()});
        return client;
    }

    public Client updateClient(Client client){
        jdbcTemplate.update(UPDATE, new Object[]{client.getName(), client.getId()});
        return client;
    }


    public void deleteClient(Client client){
       deleteClient(client.getId());
    }

    public void deleteClient(Integer id){
        jdbcTemplate.update(DELETE, new Object[]{id});
    }

    public List<Client> findByName (String name){
        return jdbcTemplate.query(SELECT_ALL.concat(" where name like = ?"),
                new Object[]{"%" + name + "%"},
                getRowMapper());
    }

    public List<Client> listAll(){
        return jdbcTemplate.query(SELECT_ALL, getRowMapper());
    }


    private static RowMapper<Client> getRowMapper() {
        return new RowMapper<Client>() {
            @Override
            public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                return new Client(id, name);
            }
        };
    }
}
