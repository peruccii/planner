package com.perucci.planner.services;

import com.perucci.planner.domain.activities.Activity;
import com.perucci.planner.domain.trip.Trip;
import com.perucci.planner.dtos.ActivityDTO;
import com.perucci.planner.presenters.ActivityPresenter;
import com.perucci.planner.repositories.IActivityRepository;
import com.perucci.planner.repositories.ITripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;


@Service
public class ActivityService {

    @Autowired
    private IActivityRepository activityRepository;

    @Autowired
    private ITripRepository tripRepository;

    public ActivityPresenter createActivity(ActivityDTO data, Trip tripData) {

        Activity activity = new Activity(data, tripData);
        this.activityRepository.save(activity);


        return new ActivityPresenter(
                activity.getId(),
                activity.getTitle(),
                activity.getOccursAt()
        );
    }

    public ResponseEntity<ActivityPresenter> registerActivity(UUID tripId, ActivityDTO data) {
        Optional<Trip> trip = tripRepository.findById(tripId);

        if (trip.isPresent()) {
            Trip rawTrip = trip.get();

            ActivityPresenter activityResponse = this.createActivity(data, rawTrip);

            return ResponseEntity.ok(activityResponse);
        }

        return ResponseEntity.notFound().build();

    }
}
