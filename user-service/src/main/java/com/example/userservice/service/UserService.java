package com.example.userservice.service;
import com.example.userservice.model.User;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
@Service
public class UserService {
    private final ConcurrentHashMap<Long, User> users = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong();
    public UserService() { create(new User(null, "Demo User", "demo@example.com")); }
    public List<User> findAll() { return users.values().stream().toList(); }
    public User findById(Long id) { return users.get(id); }
    public User create(User input) {
        long id = sequence.incrementAndGet();
        User user = new User(id, input.name(), input.email());
        users.put(id, user);
        return user;
    }
}
