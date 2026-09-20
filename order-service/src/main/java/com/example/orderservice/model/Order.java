package com.example.orderservice.model;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
public record Order(Long id, @NotNull Long userId, @NotBlank String product, @Min(1) int quantity) {}
