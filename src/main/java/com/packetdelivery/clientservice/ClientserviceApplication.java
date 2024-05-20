package com.packetdelivery.clientservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ClientserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientserviceApplication.class, args);
    }

}
