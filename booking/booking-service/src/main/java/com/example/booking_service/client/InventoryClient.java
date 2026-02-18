package com.example.booking_service.client;

import com.example.booking_service.dto.response.EventInventoryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "inventory-service", url = "http://localhost:8080")
public interface InventoryClient {
    @GetMapping("/api/v1/inventory/event/{eventId}")
    public EventInventoryResponse inventoryForEvent(@PathVariable("eventId") Long eventId);
}
