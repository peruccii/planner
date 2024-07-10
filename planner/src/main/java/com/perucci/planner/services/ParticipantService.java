package com.perucci.planner.services;

import com.perucci.planner.domain.participant.Participant;
import com.perucci.planner.domain.trip.Trip;
import com.perucci.planner.dtos.ParticipantDTO;
import com.perucci.planner.presenters.ParticipantCreatePresenter;
import com.perucci.planner.presenters.ParticipantsPresenter;
import com.perucci.planner.repositories.IParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ParticipantService {

    @Autowired
    private IParticipantRepository participantRepository;

    public ResponseEntity<Participant> confirmParticipant(UUID id, ParticipantDTO data) {
        Optional<Participant> participant = this.participantRepository.findById(id);

        if (participant.isPresent()) {

            Participant confirmatedParticipant = participant.get();

            confirmatedParticipant.setName(data.name());
            confirmatedParticipant.setIsConfirmed(true);

            this.participantRepository.save(confirmatedParticipant);

            return ResponseEntity.ok(confirmatedParticipant);
        }

        return ResponseEntity.notFound().build();
    }

    public void registerParticipantsToTrip(List<String> participants_to_invite, Trip trip) {
        List<Participant> participants = participants_to_invite.stream().map(email -> new Participant(email, trip)).toList();

        this.participantRepository.saveAll(participants);
        System.out.println(participants.get(0).getId());
    }

    public ParticipantCreatePresenter registerParticipantToTrip(String email, Trip trip) {
        Participant newParticipant = new Participant(email, trip);
        this.participantRepository.save(newParticipant);

        return new ParticipantCreatePresenter(newParticipant.getId());
    }

    public List<ParticipantsPresenter> getAllParticipantsFromTrip(UUID tripId) {
        return this.participantRepository.findAllByTripId(tripId).stream().map(participant -> new ParticipantsPresenter(
                participant.getId(),
                participant.getName(),
                participant.getEmail(),
                participant.getIsConfirmed()
        )).toList();
    }

    public void triggerConfirmationEmailToParticipants(UUID tripId) {

    }

    public void triggerConfirmationEmailToParticipant(String email) {

    }
}
