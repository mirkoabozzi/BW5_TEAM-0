package BW5_TEAM_1.EPIC.ENERGY.SERVICES.dto;

import jakarta.validation.constraints.NotNull;

public record CompaniesDTO(
        @NotNull(message = "Company name is required ")
        String name) {
}
