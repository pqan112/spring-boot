package com.example.booking_service.service;

import com.example.booking_service.dto.request.BookingRequest;
import com.example.booking_service.dto.response.BookingResponse;

public interface BookingService {
    BookingResponse createBooking(BookingRequest request);
}
