package com.rozhkov.spockvsjunit.repository;

import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class ContractRepository {

    public String fetchContractNumber(UUID contractId) {
        return "ABC123";
    }
}
