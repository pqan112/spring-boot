-- ============================
-- V2__add_auto_increment_to_ids.sql
-- Add AUTO_INCREMENT to id columns
-- ============================

-- 1️⃣ Drop foreign key trước (vì MySQL yêu cầu)
ALTER TABLE event
DROP FOREIGN KEY fk_event_venue;

-- 2️⃣ Modify id của venue
ALTER TABLE venue
    MODIFY id BIGINT NOT NULL AUTO_INCREMENT;

-- 3️⃣ Modify id của event
ALTER TABLE event
    MODIFY id BIGINT NOT NULL AUTO_INCREMENT;

-- 4️⃣ Add lại foreign key
ALTER TABLE event
    ADD CONSTRAINT fk_event_venue
        FOREIGN KEY (venue_id)
            REFERENCES venue(id)
            ON DELETE CASCADE;
