package com.packetdelivery.clientservice.helpers;

import com.packetdelivery.clientservice.protocols.HttpRes;

public class HttpHelper {
    private HttpHelper() {
    }

    public static HttpRes badRequest(Object body) {
        return new HttpRes(400, body);
    }

    public static HttpRes ok(Object body) {
        return new HttpRes(200, body);
    }
}