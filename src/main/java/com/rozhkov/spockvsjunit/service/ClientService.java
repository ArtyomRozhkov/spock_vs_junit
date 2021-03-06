package com.rozhkov.spockvsjunit.service;

import com.rozhkov.spockvsjunit.repository.ClientRepository;
import com.rozhkov.spockvsjunit.repository.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    private final NotificationService notificationService;

    public String fetchClientName(String id) {
        return clientRepository.fetchClientName(id);
    }

    public boolean isFuncAvailable() {
        return true;
    }

    public void addNewClient(String clientName) {
        clientRepository.addNewClient(clientName, List.of());
        notificationService.sendNotification(clientName, "add");
    }
}
