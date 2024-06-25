package com.packetdelivery.clientservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("clients")
public class ClientRoutes {
    private AddClientController addClientController;
    private UpdateClientController updateClientController;
    private RemoveClientController removeClientController;

    @Autowired
    public ClientRoutes(AddClientController addClientController, UpdateClientController updateClientController,
            RemoveClientController removeClientController) {
        this.addClientController = addClientController;
        this.updateClientController = updateClientController;
        this.removeClientController = removeClientController;
    }

    @PostMapping("/add")
    public ResponseEntity<ClientModel> createClient(@RequestBody AddClientModel client) {
        return ResponseEntityAdapter.adapt(addClientController, client);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ClientModel> updateClient(@PathVariable String id, @RequestBody UpdateClientModel client) {
        client.setToken(id);
        return ResponseEntityAdapter.adapt(updateClientController, client);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ClientModel> deleteClient(@PathVariable String id) {
        return ResponseEntityAdapter.adapt(removeClientController, id);
    }
}