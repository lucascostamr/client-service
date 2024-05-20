package com.packetdelivery.clientservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("clients")
public class ClientRoutes {
    @Autowired
    private final AddClientController addClientController;

    public ClientRoutes(AddClientController addClientController) {
        this.addClientController = addClientController;
    }

    @PostMapping("/add")
    public ResponseEntity<ClientModel> createClient(@RequestBody AddClientModel client) {
        return ResponseEntityAdapter.adapt(addClientController, client);
    }
}