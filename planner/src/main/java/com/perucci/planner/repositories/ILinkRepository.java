package com.perucci.planner.repositories;

import com.perucci.planner.domain.link.Link;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ILinkRepository extends JpaRepository<Link, UUID> {
    List<Link> findAllLinksByTripId(UUID trip_id);
}
