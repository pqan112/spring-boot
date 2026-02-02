package com.example.order_service.controller;

import com.example.order_service.dto.ApiResponse;
import com.example.order_service.entity.Order;
import com.example.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Order> createOrder(@RequestBody Order order) {
        return ApiResponse.<Order>builder()
                .success(true)
                .message("message.create_order_success")
                .status(HttpStatus.CREATED.value())
                .data(orderService.createOrder(order))
                .build();
    }

    @GetMapping
    public ApiResponse<List<Order>> getAllOrders() {
        return ApiResponse.<List<Order>>builder()
                .success(true)
                .message("message.get_orders_success")
                .status(HttpStatus.OK.value())
                .data(orderService.getAllOrders())
                .build();
    }
}
