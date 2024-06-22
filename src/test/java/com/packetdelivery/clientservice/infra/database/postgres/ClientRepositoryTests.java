package com.packetdelivery.clientservice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.UUID;

import static org.mockito.Mockito.doThrow;
import static org.mockito.ArgumentMatchers.any;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ClientRepositoryTests {

    @Mock
    private IJpaRepository repository;

    @InjectMocks
    private ClientRepository sut;

    private AddClientModel fakeAddClientModel;
    private ClientModel fakeClientModel;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        fakeAddClientModel = mock(AddClientModel.class);
        fakeClientModel = mock(ClientModel.class);
    }

    @Test
    void return_client_on_success() {
        try {
            when(repository.save(any(ClientModel.class))).thenReturn(fakeClientModel);
            when(fakeClientModel.getId()).thenReturn("any_id");
            ClientModel response = sut.add(fakeAddClientModel);
            assertNotNull(response.getId());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void throw_if_email_already_registered() {
        try {
            when(fakeAddClientModel.getEmail()).thenReturn("any_email");
            when(repository.findByEmail("any_email")).thenReturn(fakeClientModel);
            sut.add(fakeAddClientModel);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Email already in use");
        }
    }

    @Test
    void throw_if_cnpj_already_registered() {
        try {
            when(fakeAddClientModel.getCnpj()).thenReturn("any_cnpj");
            when(repository.findByCnpj("any_cnpj")).thenReturn(fakeClientModel);
            sut.add(fakeAddClientModel);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Cnpj already in use");
        }
    }

    @Test
    void throw_if_client_not_found() {
        try {
            UUID id = UUID.randomUUID();
            when(fakeClientModel.getUUID()).thenReturn(id);
            when(repository.getOne(id)).thenReturn(null);
            sut.update(fakeClientModel);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Not found: No client found");
        }
    }

    @Test
    void should_call_save_with_correct_values() {
        try {
            UUID id = UUID.randomUUID();
            ClientModel newClientModel = new ClientModel(id, "any_name", "any_email", "any_cnpj", "any_phone");
            ClientModel currentClientModel = new ClientModel(id, "current_name", "current_email", "current_cnpj",
                    "current_phone");
            when(repository.getOne(id)).thenReturn(currentClientModel);
            sut.update(newClientModel);
            verify(repository).save(currentClientModel);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
