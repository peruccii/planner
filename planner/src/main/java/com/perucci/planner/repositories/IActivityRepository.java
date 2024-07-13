package com.perucci.planner.repositories;

import com.perucci.planner.domain.activities.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface IActivityRepository extends JpaRepository<Activity, UUID> {
    List<Activity> findAllActivitiesByTripId(UUID trip_id);
}
