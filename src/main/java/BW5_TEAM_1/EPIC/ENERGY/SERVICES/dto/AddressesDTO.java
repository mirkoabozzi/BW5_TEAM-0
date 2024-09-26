package BW5_TEAM_1.EPIC.ENERGY.SERVICES.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AddressesDTO(
        @NotNull(message = "Street is required ")
        @Size(min = 3, max = 40, message = "Street size must be from 3 to 40 character. ")
        String street,
        @NotNull(message = "Street number is required ")
        int streetNumber,
        @NotNull(message = "Location is required ")
        @Size(min = 3, max = 40, message = "Location size must be from 3 to 40 character. ")
        String location,
        @NotNull(message = "ZIP number is required ")
        int zipNumber,
        @NotNull(message = "City ID is required ")
        String city
) {
}
