package com.example.inventory_service.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VenueInventoryResponse {
    private Long id;
    private String venueName;
    private Long totalCapacity;
}
