package com.perucci.planner.controllers;

import com.perucci.planner.dtos.LinkDTO;
import com.perucci.planner.presenters.LinkPresenter;
import com.perucci.planner.services.LinkService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/links")
public class LinkController {

    @Autowired
    private LinkService linkService;


    public ResponseEntity<LinkPresenter> createLink(@PathVariable UUID id, @Valid LinkDTO payload) {
        return this.linkService.createLinkInTrip(id, payload);
    }
}
