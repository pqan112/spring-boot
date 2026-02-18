package com.example.inventory_service.dto.response;

import com.example.inventory_service.entity.Venue;
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
    private Venue venue;
    private BigDecimal ticketPrice;
}
