package com.example.booking_service.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VenueInventoryResponse {
    private Long id;
    private String venueName;
    private Long totalCapacity;
}
