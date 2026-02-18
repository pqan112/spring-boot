package com.example.order_service.service.impl;

import com.example.order_service.entity.Order;
import com.example.order_service.event.BookingEvent;
import com.example.order_service.repo.OrderRepo;
import com.example.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepo orderRepo;

    @Override
    public Order createOrder(BookingEvent bookingEvent) {
        Order order = new Order();
        order.setCustomerId(bookingEvent.getEventId());
        order.setEventId(bookingEvent.getEventId());
        order.setTicketCount(bookingEvent.getTicketCount());
        order.setTotalPrice(bookingEvent.getTotalPrice());
        return orderRepo.saveAndFlush(order);
    }
}
