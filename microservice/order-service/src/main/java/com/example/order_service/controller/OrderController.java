package com.example.order_service.controller;

import com.example.order_service.client.UserClient;
import com.example.order_service.dto.ApiResponse;
import com.example.order_service.dto.OrderDto;
import com.example.order_service.dto.OrderResponse;
import com.example.order_service.dto.UserDto;
import com.example.order_service.entity.Order;
import com.example.order_service.repository.OrderRepo;
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
    private final OrderRepo orderRepo;
    private final UserClient userClient;

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

    @GetMapping("/{id}")
    public OrderResponse getOrderById(@PathVariable("id") Long id) {
        Order order = orderRepo
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException("order not found"));
        UserDto user = userClient.getUserById(order.getUserId());

        return new OrderResponse(order.getId(), order.getProduct(), order.getPrice(), user);
    }

    @GetMapping("/by-user/{userId}")
    public List<OrderDto> getOrdersByUserId(@PathVariable Long userId) {
        return orderRepo.findByUserId(userId).stream()
                .map(o -> new OrderDto(o.getId(), o.getProduct(), o.getPrice(), o.getUserId()))
                .toList();
    }
}
