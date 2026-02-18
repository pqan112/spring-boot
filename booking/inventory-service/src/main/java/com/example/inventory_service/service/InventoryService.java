package com.example.inventory_service.service;

import com.example.inventory_service.dto.response.EventInventoryResponse;
import com.example.inventory_service.dto.response.VenueInventoryResponse;

import java.util.List;

public interface InventoryService {
    List<EventInventoryResponse> getAllEvents();

    VenueInventoryResponse getVenueInformation(Long venueId);

    EventInventoryResponse getEventInventory(Long eventId);

    void updateEventCapacity(Long eventId, Long ticketsBooked);
}
