package com.perucci.planner.services;

import com.perucci.planner.domain.link.Link;
import com.perucci.planner.dtos.LinkDTO;
import com.perucci.planner.presenters.LinkPresenter;
import com.perucci.planner.repositories.ILinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class LinkService {

    @Autowired
    private ILinkRepository linkRepository;

    public LinkPresenter createLink(LinkDTO payload) {
        Link newLink = new Link(payload);

        this.linkRepository.save(newLink);

        return new LinkPresenter(newLink.getId(), newLink.getTitle(), newLink.getUrl());
    }

    public ResponseEntity<LinkPresenter> createLinkInTrip(UUID tripId, LinkDTO payload) {
        Optional<Link> link = this.linkRepository.findById(tripId);

        if (link.isPresent()) {
            LinkPresenter rawLink = this.createLink(payload);

            return ResponseEntity.ok(rawLink);
        }

        return ResponseEntity.notFound().build();
    }
}
