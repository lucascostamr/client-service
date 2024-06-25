package com.packetdelivery.clientservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import static org.mockito.ArgumentMatchers.any;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ClientRepositoryTests {

    @Mock
    private IJpaRepository repository;

    @InjectMocks
    private ClientRepository sut;

    private static AddClientModel fakeAddClientModel;
    private static ClientModel fakeClientModel;
    private List<ClientModel> fakeClientModelList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        fakeAddClientModel = mock(AddClientModel.class);
        fakeClientModel = mock(ClientModel.class);
        fakeClientModelList = mock(ArrayList.class);
    }

    @Nested
    class AddClient {
        @Test
        void return_client_on_success() throws Exception {
            when(repository.save(any(ClientModel.class))).thenReturn(fakeClientModel);
            when(fakeClientModel.getId()).thenReturn("any_id");
            ClientModel response = sut.add(fakeAddClientModel);
            assertNotNull(response.getId());
        }

        @Test
        void throw_if_email_already_registered() {
            when(fakeAddClientModel.getEmail()).thenReturn("any_email");
            when(repository.findByEmail("any_email")).thenReturn(fakeClientModelList);
            EmailException exception = assertThrows(EmailException.class, () -> sut.add(ClientRepositoryTests.fakeAddClientModel));
            assertEquals("Email already in use", exception.getMessage());
        }

        @Test
        void throw_if_cnpj_already_registered() {
            when(fakeAddClientModel.getCnpj()).thenReturn("any_cnpj");
            when(repository.findByCnpj("any_cnpj")).thenReturn(fakeClientModelList);
            CnpjException exception = assertThrows(CnpjException.class, () -> sut.add(ClientRepositoryTests.fakeAddClientModel));
            assertEquals("Cnpj already in use", exception.getMessage());
        }
    }

    @Nested
    class UpdateClient {

        @Test
        void throw_if_email_already_registered() {
            ClientModel anotherFakeClinetModel = mock(ClientModel.class);
            when(anotherFakeClinetModel.getEmail()).thenReturn("another_email");
            when(fakeClientModelList.isEmpty()).thenReturn(false);
            when(fakeClientModel.getEmail()).thenReturn("any_email");
            when(fakeClientModel.getUUID()).thenReturn(UUID.randomUUID());
            when(repository.findById(any(UUID.class))).thenReturn(Optional.of(anotherFakeClinetModel));
            when(repository.findByEmail("any_email")).thenReturn(fakeClientModelList);
            EmailException exception = assertThrows(EmailException.class,
                    () -> sut.update(ClientRepositoryTests.fakeClientModel));
            assertEquals("Email already in use", exception.getMessage());
        }

        @Test
        void throw_if_cnpj_already_registered() {
            ClientModel anotherFakeClinetModel = mock(ClientModel.class);
            when(anotherFakeClinetModel.getCnpj()).thenReturn("another_cnpj");
            when(anotherFakeClinetModel.getEmail()).thenReturn("any_email");
            when(fakeClientModelList.isEmpty()).thenReturn(false);
            when(fakeClientModel.getCnpj()).thenReturn("any_cnpj");
            when(fakeClientModel.getEmail()).thenReturn("any_email");
            when(fakeClientModel.getUUID()).thenReturn(UUID.randomUUID());
            when(repository.findById(any(UUID.class))).thenReturn(Optional.of(anotherFakeClinetModel));
            when(repository.findByCnpj("any_cnpj")).thenReturn(fakeClientModelList);
            CnpjException exception = assertThrows(CnpjException.class,
                    () -> sut.update(ClientRepositoryTests.fakeClientModel));
            assertEquals("Cnpj already in use", exception.getMessage());
        }

        @Test
        void throw_if_client_not_found() {
            UUID id = UUID.randomUUID();
            when(fakeClientModel.getUUID()).thenReturn(id);
            when(repository.findById(id)).thenReturn(Optional.empty());
            NotFoundException exception = assertThrows(NotFoundException.class,
                    () -> sut.update(ClientRepositoryTests.fakeClientModel));
            assertEquals(exception.getMessage(), "Not found: No client found");
        }

        @Test
        void should_update_on_success() throws Exception {
            UUID id = UUID.randomUUID();
            ClientModel updatedClientModel = mock(ClientModel.class);
            when(updatedClientModel.getUUID()).thenReturn(id);
            when(updatedClientModel.getEmail()).thenReturn("any_email");
            when(updatedClientModel.getCnpj()).thenReturn("any_cnpj");
            when(fakeClientModel.getEmail()).thenReturn("any_email");
            when(fakeClientModel.getCnpj()).thenReturn("any_cnpj");
            when(repository.findById(id)).thenReturn(Optional.of(fakeClientModel));
            sut.update(updatedClientModel);
            verify(repository).save(fakeClientModel);
        }
    }

    @Nested
    class RemoveClient {
        private static UUID id;

        @Test
        void throw_if_client_not_found() {
            id = UUID.randomUUID();
            when(fakeClientModel.getUUID()).thenReturn(id);
            when(repository.findById(id)).thenReturn(Optional.empty());
            NotFoundException exception = assertThrows(NotFoundException.class, () -> sut.remove(RemoveClient.id));
            assertEquals(exception.getMessage(), "Not found: No client found");
        }
    }
}
