package com.packetdelivery.clientservice;

import java.util.UUID;

public interface IRemoveClientRepository {
    public void remove(UUID clientId) throws Exception;
}
