package com.perucci.planner.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record ParticipantDTO(
        @Valid

        @NotBlank(message = "name is required")
        String name,
        String email) {
}
