package com.example.order_service.service.impl;

import com.example.order_service.entity.Order;
import com.example.order_service.repository.OrderRepo;
import com.example.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepo orderRepo;

    @Override
    public Order createOrder(Order order) {
        return orderRepo.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }
}
