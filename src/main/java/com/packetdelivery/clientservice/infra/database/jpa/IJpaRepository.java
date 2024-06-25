package com.packetdelivery.clientservice;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IJpaRepository extends JpaRepository<ClientModel, UUID> {
    ClientModel save(ClientModel client);

    List<ClientModel> findByEmail(String email);

    List<ClientModel> findByCnpj(String cnpj);
}