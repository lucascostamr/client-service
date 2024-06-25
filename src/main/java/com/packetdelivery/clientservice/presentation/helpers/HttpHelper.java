package com.packetdelivery.clientservice;

public class HttpHelper {
    private HttpHelper() {
    }

    public static HttpRes badRequest(Object body) {
        return new HttpRes(400, body);
    }

    public static HttpRes ok(Object body) {
        return new HttpRes(200, body);
    }

    public static HttpRes notFound(Object body) {
        return new HttpRes(404, body);
    }

    public static HttpRes serverError(Object body) {
        return new HttpRes(500, body);
    }

    public static HttpRes created(Object body) {
        return new HttpRes(201, body);
    }

    public static HttpRes noContent() {
        return new HttpRes(204, null);
    }
}
