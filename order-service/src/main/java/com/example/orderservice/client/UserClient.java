package com.example.orderservice.client;
import com.example.orderservice.model.UserResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
@Component
public class UserClient {
    private final RestClient client;
    public UserClient(@Value("${user-service.url}") String baseUrl) {
        this.client = RestClient.builder().baseUrl(baseUrl).build();
    }
    public UserResponse getUser(Long id) {
        return client.get().uri("/api/users/{id}", id).retrieve().body(UserResponse.class);
    }
}
