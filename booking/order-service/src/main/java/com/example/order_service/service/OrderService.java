package com.example.order_service.service;

import com.example.order_service.entity.Order;
import com.example.order_service.event.BookingEvent;

public interface OrderService {
    Order createOrder(BookingEvent bookingEvent);
}
