package com.packetdelivery.clientservice;

import lombok.Getter;
import lombok.AllArgsConstructor;

@Getter
@AllArgsConstructor
public class AddClientModel implements IEmail, ICnpj {
    private String name;
    private String email;
    private String cnpj;
    private String phone;
}
