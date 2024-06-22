package com.packetdelivery.clientservice;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import org.springframework.http.MediaType;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class ClientRoutesTests {

    @Autowired
    private IJpaRepository repository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @AfterEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    void should_return_400_if_no_name_provided() throws Exception {
        AddClientModel fakeAddClientModel = new AddClientModel(null, "email@mail.com", "19.009.518/0001-14",
                "00000000000");
        String jsonRequestBody = this.objectMapper.writeValueAsString(fakeAddClientModel);
        mockMvc.perform(post("/clients/add").content(jsonRequestBody).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }

    @Test
    void should_return_201_on_add_success() throws Exception {
        AddClientModel fakeAddClientModel = new AddClientModel("Lucas", "email@mail.com", "19.009.518/0001-14",
                "00000000000");
        String jsonRequestBody = this.objectMapper.writeValueAsString(fakeAddClientModel);
        mockMvc.perform(post("/clients/add").content(jsonRequestBody).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }

    @Test
    void should_return_500_if_invalid_token_provided() throws Exception {
        UpdateClientModel fakeUpdateClientModel = new UpdateClientModel(null, "daniel", "email@mail.com",
                "19.009.518/0001-14", "00000000000");
        String jsonUpdateRequestBody = this.objectMapper.writeValueAsString(fakeUpdateClientModel);
        mockMvc.perform(put("/clients/update/anyid").content(jsonUpdateRequestBody)
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void should_return_404_if_client_not_found() throws Exception {
        UpdateClientModel fakeUpdateClientModel = new UpdateClientModel(null, "daniel", "email@mail.com",
                "19.009.518/0001-14", "00000000000");
        String jsonUpdateRequestBody = this.objectMapper.writeValueAsString(fakeUpdateClientModel);
        UUID uuid = UUID.randomUUID();
        JwtAdapter jwt = new JwtAdapter();
        String token = jwt.encode(uuid.toString());
        mockMvc.perform(put("/clients/update/" + token).content(jsonUpdateRequestBody)
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void should_return_200_on_update_success() throws Exception {
        AddClientModel fakeAddClientModel = new AddClientModel("Lucas", "email@mail.com", "19.009.518/0001-14",
                "00000000000");
        String jsonRequestBody = this.objectMapper.writeValueAsString(fakeAddClientModel);
        MvcResult response = mockMvc.perform(post("/clients/add").content(jsonRequestBody)
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();
        String token = response.getResponse().getContentAsString();
        UpdateClientModel fakeUpdateClientModel = new UpdateClientModel(null, "daniel", "email@mail.com",
                "19.009.518/0001-14", "00000000000");
        String jsonUpdateRequestBody = this.objectMapper.writeValueAsString(fakeUpdateClientModel);
        mockMvc.perform(put("/clients/update/" + token).content(jsonUpdateRequestBody)
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }
}
