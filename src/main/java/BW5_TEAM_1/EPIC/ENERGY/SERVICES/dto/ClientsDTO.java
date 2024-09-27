package BW5_TEAM_1.EPIC.ENERGY.SERVICES.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ClientsDTO(
        @NotEmpty(message = "Company is required ")
        @Size(min = 3, max = 40, message = "Company size must be from 3 to 40 character. ")
        String companyName,
        @NotNull(message = "VAT is required ")
        long vat,
        @Email(message = "Email is required ")
        String email,
        @NotEmpty(message = "PEC is required ")
        String pec,
        @NotNull(message = "Annual turnover is required ")
        double annualTurnover,
        @NotNull(message = "Tel. number is required ")
        long telNumber,
        @Email(message = "Contact email is required ")
        String contactEmail,
        @NotEmpty(message = "Contact name is required ")
        @Size(min = 3, max = 20, message = "Size must be from 3 to 20 character. ")
        String contactName,
        @NotEmpty(message = "Contact surname is required ")
        @Size(min = 3, max = 20, message = "Size must be from 3 to 20 character. ")
        String contactSurname,
        @NotNull(message = "Contact number is required ")
        long contactNumber,
        @NotEmpty(message = "Company type is required ")
        String companyType,
        @NotEmpty(message = "Work addresses is required ")
        String workAddress,
        @NotEmpty(message = "Operative addresses is required ")
        String operativeAddress
) {
}
