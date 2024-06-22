package com.packetdelivery.clientservice;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IJpaRepository extends JpaRepository<ClientModel, UUID> {
    ClientModel save(ClientModel client);

    ClientModel findByEmail(String email);

    ClientModel findByCnpj(String cnpj);
}