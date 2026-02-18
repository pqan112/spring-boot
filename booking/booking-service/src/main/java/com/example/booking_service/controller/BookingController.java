package com.example.booking_service.controller;

import com.example.booking_service.dto.request.BookingRequest;
import com.example.booking_service.dto.response.BookingResponse;
import com.example.booking_service.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @PostMapping("/booking")
    public @ResponseBody BookingResponse createBooking(@RequestBody BookingRequest request) {
        return bookingService.createBooking(request);
    }
}
