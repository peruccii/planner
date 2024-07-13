package com.perucci.planner.domain.link;

import com.perucci.planner.domain.trip.Trip;
import com.perucci.planner.dtos.LinkDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity(name = "links")
@Table(name = "links")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String url;

    @ManyToOne
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;

    public Link(LinkDTO payload) {
        this.title = payload.title();
        this.url = payload.url();
        this.trip = trip;
    }
}
