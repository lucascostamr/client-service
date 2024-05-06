package com.packetdelivery.clientservice.protocols;

import com.packetdelivery.clientservice.protocols.HttpReq;
import com.packetdelivery.clientservice.protocols.HttpRes;

public interface IController {
    public HttpRes handle(HttpReq httpRequest);
}
