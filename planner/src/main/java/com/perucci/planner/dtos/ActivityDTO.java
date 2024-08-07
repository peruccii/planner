package com.perucci.planner.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record ActivityDTO(
        @Valid

        @NotBlank(message = "title is required")
        String title,
        @NotBlank(message = "occurs_at is required")
        String occurs_at) {
}
