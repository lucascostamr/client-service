package com.packetdelivery.clientservice.protocols;

import lombok.Getter;
import lombok.AllArgsConstructor;

@Getter
@AllArgsConstructor
public class HttpRes {
    private int statusCode = 0;
    private Object body;
}
