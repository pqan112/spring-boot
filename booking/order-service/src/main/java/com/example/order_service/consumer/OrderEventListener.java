package com.example.order_service.consumer;

import com.example.order_service.client.InventoryClient;
import com.example.order_service.entity.Order;
import com.example.order_service.event.BookingEvent;
import com.example.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderEventListener {
    private final OrderService orderService;
    private final InventoryClient inventoryClient;

    @KafkaListener(topics = "booking", groupId = "order-service")
    public void handleOrderEvent(BookingEvent bookingEvent) {

        log.info("received order event={}", bookingEvent);
        // Create order object and save to db
        Order order = orderService.createOrder(bookingEvent);
        // Update inventory
        inventoryClient.updateEventCapacity(order.getEventId(), order.getTicketCount());
    }

}
