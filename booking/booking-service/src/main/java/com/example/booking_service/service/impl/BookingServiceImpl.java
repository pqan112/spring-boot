package com.example.booking_service.service.impl;

import com.example.booking_service.client.InventoryClient;
import com.example.booking_service.dto.request.BookingRequest;
import com.example.booking_service.dto.response.BookingResponse;
import com.example.booking_service.dto.response.EventInventoryResponse;
import com.example.booking_service.entity.Customer;
import com.example.booking_service.event.BookingEvent;
import com.example.booking_service.repo.CustomerRepo;
import com.example.booking_service.service.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.retry.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final CustomerRepo customerRepo;
    private final InventoryClient inventoryClient;
    private final KafkaTemplate<String, BookingEvent> kafkaTemplate;



    @Override
    public BookingResponse createBooking(BookingRequest request) {
        // check if user exists
        Customer customer = customerRepo.findById(request.getUserId()).orElse(null);
        if (customer == null) {
            throw new RuntimeException("Customer not found");
        }
        // check if there is enough inventory
        EventInventoryResponse inventoryResponse = inventoryClient.inventoryForEvent(request.getEventId());
        log.info("inventoryResponse={}", inventoryResponse);
        if(inventoryResponse.getCapacity() < request.getTicketCount()) {
            throw new RuntimeException("Not enough inventory");
        }
        // -- get event information to also get Venue information
        // create booking
        BookingEvent bookingEvent = createBookingEvent(request, customer, inventoryResponse);
        // send booking to Order Service on a Kafka topic
        kafkaTemplate.send("booking", bookingEvent);
        log.info("booking sent to kafka={}", bookingEvent);
        return BookingResponse.builder()
                .userId(bookingEvent.getUserId())
                .eventId(bookingEvent.getEventId())
                .ticketCount(bookingEvent.getTicketCount())
                .totalPrice(bookingEvent.getTotalPrice())
                .build();
    }

    private BookingEvent createBookingEvent(BookingRequest request, Customer customer, EventInventoryResponse inventoryResponse) {
        return BookingEvent.builder()
                .eventId(request.getEventId())
                .userId(customer.getId())
                .ticketCount(request.getTicketCount())
                .totalPrice(inventoryResponse.getTicketPrice().multiply(BigDecimal.valueOf(request.getTicketCount())))
                .build();
    }
}
