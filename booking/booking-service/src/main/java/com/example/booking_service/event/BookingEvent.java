package com.example.booking_service.event;


import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingEvent {
    private Long userId;
    private Long eventId;
    private Long ticketCount;
    private BigDecimal totalPrice;
}
