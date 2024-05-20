package com.packetdelivery.clientservice;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import lombok.Getter;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;

@SpringBootTest
@AutoConfigureDataJpa
public class ClientRepositoryTests {

    private final IJpaRepository repository;

    @Autowired
    public ClientRepositoryTests(IJpaRepository repository) {
        this.repository = repository;
    }

    public AddClientModel makeFakeAddClientModel() {
        return new AddClientModel("any_name", "any_email", "any_cnpj", "any_phone");
    }

    @Getter
    @AllArgsConstructor
    class SutTypes {
        ClientRepository sut;
    }

    public SutTypes makeSut() {
        ClientRepository sut = new ClientRepository(repository);
        return new SutTypes(sut);
    }

    @BeforeEach
    void cleanUpDatabase() {
        try {
            repository.deleteAll();
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void return_client_on_success() {
        try {
            SutTypes sutTypes = makeSut();
            ClientRepository sut = sutTypes.getSut();
            ClientModel response = sut.add(makeFakeAddClientModel());
            assertNotNull(response.getId());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void throw_if_email_already_registered() {
        try {
            SutTypes sutTypes = makeSut();
            ClientRepository sut = sutTypes.getSut();
            sut.add(makeFakeAddClientModel());
            sut.add(makeFakeAddClientModel());
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Email already in use");
        }
    }
}
