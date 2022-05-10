package com.rozhkov.spockvsjunit.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientRepository {

    public String fetchClientName(String id) {
        return "some_name";
    }

    public void addNewClient(String clientName, List<String> phones) {
        // add new client
    }
}
