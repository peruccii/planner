package com.perucci.planner.repositories;

import com.perucci.planner.domain.participant.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface IParticipantRepository extends JpaRepository<Participant, UUID> {
    List<Participant> findAllByTripId(UUID trip_id);
}
