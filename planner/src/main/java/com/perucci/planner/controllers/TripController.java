package com.perucci.planner.controllers;

import com.perucci.planner.domain.participant.Participant;
import com.perucci.planner.domain.trip.Trip;
import com.perucci.planner.dtos.ActivityDTO;
import com.perucci.planner.dtos.ParticipantDTO;
import com.perucci.planner.dtos.TripDTO;
import com.perucci.planner.presenters.ActivityPresenter;
import com.perucci.planner.presenters.ParticipantCreatePresenter;
import com.perucci.planner.presenters.ParticipantsPresenter;
import com.perucci.planner.presenters.TripPresenter;
import com.perucci.planner.repositories.ITripRepository;
import com.perucci.planner.services.ActivityService;
import com.perucci.planner.services.ParticipantService;
import com.perucci.planner.services.TripService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import java.util.UUID;

@RestController
@RequestMapping("/trips")

public class TripController {

    @Autowired
    private ITripRepository tripRepository;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ParticipantService participantService;

    @Autowired
    private TripService tripService;

    @PostMapping
    public ResponseEntity<TripPresenter> createTrip(@Valid @RequestBody TripDTO data) {
      return this.tripService.createTrip(data);
    }

    @PostMapping("/invite/{id}")
    public ResponseEntity<ParticipantCreatePresenter> inviteParticipant(@PathVariable UUID id, @RequestBody ParticipantDTO data) {
        return this.tripService.inviteToTrip(id, data);
    }

    @PostMapping("/activity/{id}")
    public ResponseEntity<ActivityPresenter> registerActivity(@PathVariable UUID id, @RequestBody ActivityDTO data) {
        return this.activityService.registerActivity(id, data);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Trip> updateTrip(@PathVariable UUID id, @RequestBody TripDTO data) {
       return this.tripService.updateTrip(id, data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trip> getTripDetails(@PathVariable UUID id) {
        return this.tripService.getTripDetails(id);
    }

    @GetMapping("/activity/{id}")
    public ResponseEntity<List<ActivityPresenter>> getAllActivities(@PathVariable UUID id) {
        return this.activityService.getAllActivitiesFromId(id);
    }


    @GetMapping("/confirm/{id}")
    public ResponseEntity<Trip> confirmTrip(@PathVariable UUID id) {
       return this.tripService.confirmTrip(id);
    }

    @GetMapping("/participants/{id}")
    public ResponseEntity<List<ParticipantsPresenter>> getAllParticipants(@PathVariable UUID id) {
        return tripService.getAllParticipants(id);
    }
}
