package com.packetdelivery.clientservice;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.packetdelivery.clientservice.AddClientModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;

@SpringBootTest
@AutoConfigureMockMvc
public class ClientRoutesTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void should_return_400_if_no_name_provided() throws Exception {
        AddClientModel fakeAddClientModel = new AddClientModel(null, "email@mail.com", "19.009.518/0001-14",
                "00000000000");
        String jsonRequestBody = this.objectMapper.writeValueAsString(fakeAddClientModel);
        mockMvc.perform(post("/clients/add").content(jsonRequestBody).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }

    @Test
    void should_return_201_on_success() throws Exception {
        AddClientModel fakeAddClientModel = new AddClientModel("Lucas", "email@mail.com", "19.009.518/0001-14",
                "00000000000");
        String jsonRequestBody = this.objectMapper.writeValueAsString(fakeAddClientModel);
        mockMvc.perform(post("/clients/add").content(jsonRequestBody).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }
}
