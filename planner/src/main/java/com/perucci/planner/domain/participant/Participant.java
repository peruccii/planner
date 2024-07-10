package com.perucci.planner.domain.participant;

import com.perucci.planner.domain.trip.Trip;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity(name = "participants")
@Table(name = "participants")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @JoinColumn(nullable = false)
    private Boolean isConfirmed;

    @JoinColumn(nullable = false)
    private String name;

    @JoinColumn(nullable = false)
    private String email;

    @ManyToOne
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;

    public Participant(String email, Trip trip) {
        this.email = email;
        this.trip = trip;
        this.isConfirmed = false;
        this.name = "";
    }

}
