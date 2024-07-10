package com.perucci.planner.presenters;

import java.util.UUID;

public record ParticipantsPresenter(UUID id, String name, String email, Boolean isConfirmed) {
}
