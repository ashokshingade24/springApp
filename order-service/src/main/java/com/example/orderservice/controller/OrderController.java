package com.example.orderservice.controller;
import com.example.orderservice.model.Order;
import com.example.orderservice.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService service;
    public OrderController(OrderService service) { this.service = service; }
    @GetMapping public List<Order> getOrders() { return service.findAll(); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public Order create(@Valid @RequestBody Order order) { return service.create(order); }
}
