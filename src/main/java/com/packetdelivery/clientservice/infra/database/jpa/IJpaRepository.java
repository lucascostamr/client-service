package com.packetdelivery.clientservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface IJpaRepository extends JpaRepository<ClientModel, UUID> {
    ClientModel save(ClientModel client);
    
    ClientModel findByEmail(String email);
}