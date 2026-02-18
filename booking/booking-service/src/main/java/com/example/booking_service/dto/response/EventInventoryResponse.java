package com.example.booking_service.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventInventoryResponse {
    private Long id;
    private String name;
    private Long capacity;
    private VenueResponse venue;
    private BigDecimal ticketPrice;
}

