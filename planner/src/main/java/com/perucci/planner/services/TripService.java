package com.perucci.planner.services;

import com.perucci.planner.domain.participant.Participant;
import com.perucci.planner.domain.trip.Trip;
import com.perucci.planner.dtos.ParticipantDTO;
import com.perucci.planner.dtos.TripDTO;
import com.perucci.planner.presenters.ParticipantCreatePresenter;
import com.perucci.planner.presenters.ParticipantsPresenter;
import com.perucci.planner.presenters.TripPresenter;
import com.perucci.planner.repositories.ITripRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TripService {

    @Autowired
    private ITripRepository tripRepository;

    @Autowired
    private ParticipantService participantService;

    public ResponseEntity<TripPresenter> createTrip(TripDTO data) {
        Trip newTrip = new Trip(data);

        this.tripRepository.save(newTrip);

        this.participantService.registerParticipantsToTrip(data.emails_to_invite(), newTrip);

        return ResponseEntity.ok(new TripPresenter(newTrip.getId()));
    }

    public ResponseEntity<Trip> getTripDetails(UUID id) {
        Optional<Trip> trip = this.tripRepository.findById(id);

        return trip.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Trip> updateTrip(UUID id, TripDTO data) {
        Optional<Trip> trip = this.tripRepository.findById(id);

        if(trip.isPresent()) {
            Trip updatedTrip = trip.get();
            updatedTrip.setDestination(data.destination());
            updatedTrip.setStartsAt(LocalDateTime.parse(data.starts_at(), DateTimeFormatter.ISO_DATE_TIME));
            updatedTrip.setEndsAt(LocalDateTime.parse(data.ends_at(), DateTimeFormatter.ISO_DATE_TIME));

            this.tripRepository.save(updatedTrip);

            return ResponseEntity.ok(updatedTrip);
        }

        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Trip> confirmTrip(@PathVariable UUID id) {
        Optional<Trip> trip = this.tripRepository.findById(id);

        if(trip.isPresent()) {
            Trip updatedTrip = trip.get();

            updatedTrip.setIsConfirmed(true);

            this.participantService.triggerConfirmationEmailToParticipants(id, trip);

            this.tripRepository.save(updatedTrip);

            return ResponseEntity.ok(updatedTrip);
        }

        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<ParticipantCreatePresenter> inviteToTrip(UUID id, ParticipantDTO data) {
        Optional<Trip> trip = this.tripRepository.findById(id);

        if(trip.isPresent()) {
            Trip rawTrip = trip.get();

            ParticipantCreatePresenter participantResponse = this.participantService.registerParticipantToTrip(data.email(), rawTrip);

            if(rawTrip.getIsConfirmed()) this.participantService.triggerConfirmationEmailToParticipant(data.email());

            return ResponseEntity.ok(participantResponse);

        }

        return ResponseEntity.notFound().build();
    }


    public ResponseEntity<List<ParticipantsPresenter>> getAllParticipants(UUID id) {
        List<ParticipantsPresenter> participantsList = this.participantService.getAllParticipantsFromTrip(id);

        return ResponseEntity.ok(participantsList);
    }
}
