package com.example.inventory_service.service.impl;

import com.example.inventory_service.dto.response.EventInventoryResponse;
import com.example.inventory_service.dto.response.VenueInventoryResponse;
import com.example.inventory_service.entity.Event;
import com.example.inventory_service.entity.Venue;
import com.example.inventory_service.repo.EventRepo;
import com.example.inventory_service.repo.VenueRepo;
import com.example.inventory_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final EventRepo eventRepo;
    private final VenueRepo venueRepo;

    @Override
    public List<EventInventoryResponse> getAllEvents() {
        List<Event> events = eventRepo.findAll();

        return events.stream().map(evt -> EventInventoryResponse
                .builder()
                .id(evt.getId())
                .name(evt.getName())
                .capacity(evt.getLeftCapacity())
                .venue(evt.getVenue())
                .build())
                .toList();
    }

    @Override
    public VenueInventoryResponse getVenueInformation(Long venueId) {
        Venue venue = venueRepo.findById(venueId).orElse(null);

        return VenueInventoryResponse.builder()
                .id(venue.getId())
                .venueName(venue.getName())
                .totalCapacity(venue.getTotalCapacity())
                .build();
    }

    @Override
    public EventInventoryResponse getEventInventory(Long eventId) {
        Event event = eventRepo.findById(eventId).orElse(null);

        return EventInventoryResponse.builder()
                .id(event.getId())
                .name(event.getName())
                .capacity(event.getLeftCapacity())
                .venue(event.getVenue())
                .ticketPrice(event.getTicketPrice())
                .build();
    }

    @Override
    public void updateEventCapacity(Long eventId, Long ticketsBooked) {
        Event event = eventRepo.findById(eventId).orElse(null);
        event.setLeftCapacity(event.getLeftCapacity() - ticketsBooked);
        eventRepo.saveAndFlush(event);
        log.info("Updated event capacity for eventId: {} with tickets booked: {}", eventId, ticketsBooked);
    }
}
