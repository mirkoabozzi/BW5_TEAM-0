package BW5_TEAM_1.EPIC.ENERGY.SERVICES.dto;

import jakarta.validation.constraints.NotEmpty;

public record CompaniesDTO(
        @NotEmpty(message = "Company name is required ")
        String name) {
}
