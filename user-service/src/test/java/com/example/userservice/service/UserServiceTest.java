package com.example.userservice.service;
import com.example.userservice.model.User;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
class UserServiceTest {
    @Test void createShouldAssignId() {
        UserService service = new UserService();
        User result = service.create(new User(null, "Ashok", "ashok@example.com"));
        assertThat(result.id()).isPositive();
        assertThat(result.name()).isEqualTo("Ashok");
    }
    @Test void findByIdShouldReturnUser() {
        UserService service = new UserService();
        User created = service.create(new User(null, "Test", "test@example.com"));
        assertThat(service.findById(created.id())).isEqualTo(created);
    }
}
