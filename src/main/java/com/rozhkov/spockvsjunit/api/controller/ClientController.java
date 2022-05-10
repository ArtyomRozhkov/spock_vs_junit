package com.rozhkov.spockvsjunit.api.controller;

import com.rozhkov.spockvsjunit.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/client")
public class ClientController {

    private final ClientService service;

    @GetMapping(path = "/{id}/name")
    public String getName(@PathVariable String id) {
        return service.fetchClientName(id);
    }
}

