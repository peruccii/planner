package com.perucci.planner.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record LinkDTO(
        @Valid

        @NotBlank(message = "title is required")
        String title,
        @NotBlank(message = "url is required")
        String url) {
}
