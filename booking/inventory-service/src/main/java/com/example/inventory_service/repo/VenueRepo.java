package com.example.inventory_service.repo;

import com.example.inventory_service.entity.Venue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenueRepo extends JpaRepository<Venue, Long> {
}
