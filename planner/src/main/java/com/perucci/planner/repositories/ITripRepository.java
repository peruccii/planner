package com.perucci.planner.repositories;

import com.perucci.planner.domain.trip.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ITripRepository extends JpaRepository<Trip, UUID> {
}
