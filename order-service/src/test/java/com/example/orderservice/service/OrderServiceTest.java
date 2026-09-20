package com.example.orderservice.service;
import com.example.orderservice.client.UserClient;
import com.example.orderservice.model.Order;
import com.example.orderservice.model.UserResponse;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
class OrderServiceTest {
    @Test void createShouldCallUserService() {
        UserClient client = mock(UserClient.class);
        when(client.getUser(1L)).thenReturn(new UserResponse(1L, "Ashok", "ashok@example.com"));
        OrderService service = new OrderService(client);
        Order result = service.create(new Order(null, 1L, "Laptop", 1));
        assertThat(result.id()).isEqualTo(1L);
        verify(client).getUser(1L);
    }
}
