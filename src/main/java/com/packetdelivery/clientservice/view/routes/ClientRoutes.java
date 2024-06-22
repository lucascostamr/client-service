package com.packetdelivery.clientservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("clients")
public class ClientRoutes {
    private AddClientController addClientController;
    private UpdateClientController updateClientController;

    @Autowired
    public ClientRoutes(AddClientController addClientController, UpdateClientController updateClientController) {
        this.addClientController = addClientController;
        this.updateClientController = updateClientController;
    }

    @PostMapping("/add")
    public ResponseEntity<ClientModel> createClient(@RequestBody AddClientModel client) {
        return ResponseEntityAdapter.adapt(addClientController, client);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ClientModel> createClient(@PathVariable String id, @RequestBody UpdateClientModel client) {
        client.setToken(id);
        return ResponseEntityAdapter.adapt(updateClientController, client);
    }
}