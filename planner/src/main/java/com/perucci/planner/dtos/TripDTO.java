package com.perucci.planner.dtos;



import jakarta.validation.Valid;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


import java.util.List;


public record TripDTO(

        @Valid

        @NotBlank(message = "destination is required")
        @NotNull(message = "destination is required")
        String destination,
        @NotEmpty
        @NotBlank
        String starts_at,
        @NotEmpty
        @NotBlank
        String ends_at,
        List<String> emails_to_invite,
        @NotEmpty
        @NotBlank
        String owner_name,
        @NotEmpty
        @NotBlank
        String owner_email,
        Boolean is_confirmed) {

}
