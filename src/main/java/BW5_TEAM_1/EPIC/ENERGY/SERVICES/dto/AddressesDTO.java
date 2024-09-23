package BW5_TEAM_1.EPIC.ENERGY.SERVICES.dto;

import jakarta.validation.constraints.NotNull;

public record AddressesDTO(
        @NotNull(message = "Street is required ")
        String street,
        @NotNull(message = "Street number is required ")
        int streetNumber,
        @NotNull(message = "Location is required ")
        String location,
        @NotNull(message = "ZIP number is required ")
        int zipNumber
) {
}
