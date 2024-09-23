package BW5_TEAM_1.EPIC.ENERGY.SERVICES.dto;

import jakarta.validation.constraints.NotEmpty;

public record UserLoginDTO(@NotEmpty(message = "Email  is required")
                           String email,
                           @NotEmpty(message = "Password  is required")
                           String password) {
}
