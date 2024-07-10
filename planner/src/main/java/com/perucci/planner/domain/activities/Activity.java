package com.perucci.planner.domain.activities;

import com.perucci.planner.domain.trip.Trip;
import com.perucci.planner.dtos.ActivityDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Entity(name = "activities")
@Table(name = "activities")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(name = "occurs_at", nullable = false)
    private LocalDateTime OccursAt;

    @ManyToOne
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;

    public Activity(ActivityDTO data, Trip trip) {
        this.title = data.title();
        this.OccursAt = LocalDateTime.parse(data.occurs_at(), DateTimeFormatter.ISO_DATE_TIME);
        this.trip = trip;
    }
}
