package com.example.orderservice.service;
import com.example.orderservice.client.UserClient;
import com.example.orderservice.model.Order;
import com.example.orderservice.model.UserResponse;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;
@Service
public class OrderService {
    private final UserClient userClient;
    private final List<Order> orders = new CopyOnWriteArrayList<>();
    private final AtomicLong sequence = new AtomicLong();
    public OrderService(UserClient userClient) { this.userClient = userClient; }
    public List<Order> findAll() { return List.copyOf(orders); }
    public Order create(Order input) {
        UserResponse user = userClient.getUser(input.userId());
        if (user == null) throw new IllegalArgumentException("User not found");
        Order order = new Order(sequence.incrementAndGet(), input.userId(), input.product(), input.quantity());
        orders.add(order);
        return order;
    }
}
