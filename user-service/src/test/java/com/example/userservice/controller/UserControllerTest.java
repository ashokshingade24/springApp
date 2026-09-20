package com.example.userservice.controller;
import com.example.userservice.model.User;
import com.example.userservice.service.UserService;
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
@WebMvcTest(UserController.class)
class UserControllerTest {
    @Autowired MockMvc mvc;
    @MockBean UserService service;
    @Test void createUserShouldReturn201() throws Exception {
        when(service.create(any())).thenReturn(new User(1L, "Ashok", "ashok@example.com"));
        mvc.perform(post("/api/users").contentType(MediaType.APPLICATION_JSON).content("""
            {"name":"Ashok","email":"ashok@example.com"}
        """)).andExpect(status().isCreated()).andExpect(jsonPath("$.id").value(1));
    }
    @Test void invalidEmailShouldReturn400() throws Exception {
        mvc.perform(post("/api/users").contentType(MediaType.APPLICATION_JSON).content("""
            {"name":"Ashok","email":"not-an-email"}
        """)).andExpect(status().isBadRequest());
    }
}
