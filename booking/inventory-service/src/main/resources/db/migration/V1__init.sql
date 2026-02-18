-- ============================
-- V1__init_schema.sql
-- Initial schema for inventory-service
-- ============================

CREATE TABLE venue (
                       id BIGINT NOT NULL,
                       name VARCHAR(255) NOT NULL,
                       total_capacity BIGINT NOT NULL,
                       address VARCHAR(255),
                       PRIMARY KEY (id)
);

CREATE TABLE event (
                       id BIGINT NOT NULL,
                       name VARCHAR(255) NOT NULL,
                       total_capacity BIGINT NOT NULL,
                       left_capacity BIGINT NOT NULL,
                       venue_id BIGINT NOT NULL,
                       PRIMARY KEY (id),
                       CONSTRAINT fk_event_venue
                           FOREIGN KEY (venue_id)
                               REFERENCES venue(id)
                               ON DELETE CASCADE
);
