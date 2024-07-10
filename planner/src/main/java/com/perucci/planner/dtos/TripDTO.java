package com.perucci.planner.dtos;

import java.time.LocalDateTime;
import java.util.List;

public record TripDTO(String destination, String starts_at, String ends_at, List<String> emails_to_invite, String owner_name, String owner_email, Boolean is_confirmed) {
}
