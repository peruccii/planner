package com.perucci.planner.controllers;

import com.perucci.planner.domain.participant.Participant;
import com.perucci.planner.dtos.ParticipantDTO;
import com.perucci.planner.repositories.IParticipantRepository;
import com.perucci.planner.services.ParticipantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.UUID;

@RestController
@RequestMapping("/participants")
public class ParticipantController {

    @Autowired
    private IParticipantRepository participantRepository;

    @Autowired
    private ParticipantService participantService;

    @PostMapping("/confirm/{id}")
    public ResponseEntity<Participant> confirmParticipant(@PathVariable UUID id, @RequestBody @Valid ParticipantDTO data) {
        return this.participantService.confirmParticipant(id, data);
    }

}
