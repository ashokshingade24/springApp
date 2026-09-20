package com.example.orderservice.controller;
import com.example.orderservice.model.Order;
import com.example.orderservice.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(OrderController.class)
class OrderControllerTest {
    @Autowired MockMvc mvc;
    @MockBean OrderService service;
    @Test void createOrderShouldReturn201() throws Exception {
        when(service.create(any())).thenReturn(new Order(1L, 1L, "Laptop", 1));
        mvc.perform(post("/api/orders").contentType(MediaType.APPLICATION_JSON).content("""
            {"userId":1,"product":"Laptop","quantity":1}
        """)).andExpect(status().isCreated()).andExpect(jsonPath("$.id").value(1));
    }
}
