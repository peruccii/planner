package com.perucci.planner.presenters;

import java.time.LocalDateTime;
import java.util.UUID;

public record ActivityPresenter(UUID id, String title, LocalDateTime occurs_at) {
}
