package com.example.inventory_service.repo;

import com.example.inventory_service.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepo extends JpaRepository<Event, Long> {
}
