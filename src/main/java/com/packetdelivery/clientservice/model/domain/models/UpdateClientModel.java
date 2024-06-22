package com.packetdelivery.clientservice;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
public class UpdateClientModel implements IEmail, ICnpj {
    private String token;
    private String name;
    private String email;
    private String cnpj;
    private String phone;
}
