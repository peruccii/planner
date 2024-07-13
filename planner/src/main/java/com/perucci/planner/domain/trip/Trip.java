package com.perucci.planner.domain.trip;

import com.perucci.planner.dtos.TripDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Entity(name = "trips")
@Table(name = "trips")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @JoinColumn(nullable = false)
    private String destination;

    @JoinColumn(name = "starts_at", nullable = false)
    private LocalDateTime startsAt;

    @JoinColumn(name = "ends_at", nullable = false)
    private LocalDateTime endsAt;

    @JoinColumn(name = "is_confirmed", nullable = false)
    private Boolean isConfirmed;

    @JoinColumn(name = "owner_name", nullable = false)
    private String ownerName;

    @JoinColumn(name = "owner_email", nullable = false)
    private String ownerEmail;


    public Trip(TripDTO data) {
        this.destination = data.destination();
        this.isConfirmed = false;
        this.ownerEmail = data.owner_email();
        this.ownerName = data.owner_name();
        this.startsAt = LocalDateTime.parse(data.starts_at(), DateTimeFormatter.ISO_DATE_TIME);
        this.endsAt = LocalDateTime.parse(data.ends_at(), DateTimeFormatter.ISO_DATE_TIME);
    }
}
