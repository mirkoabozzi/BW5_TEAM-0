package BW5_TEAM_1.EPIC.ENERGY.SERVICES.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ClientsDTO(
        @NotNull(message = "Company is required ")
        @Size(min = 3, max = 20, message = "Company size must be from 3 to 20 character. ")
        String companyName,
        @NotNull(message = "VAT is required ")
        long vat,
        @Email(message = "Email is required ")
        String email,
        @NotNull(message = "PEC is required ")
        String pec,
        @NotNull(message = "Annual turnover is required ")
        long annualTurnover,
        @NotNull(message = "Tel. number is required ")
        long telNumber,
        @Email(message = "Contact email is required ")
        String contactEmail,
        @NotNull(message = "Contact name is required ")
        @Size(min = 3, max = 20, message = "Size must be from 3 to 20 character. ")
        String contactName,
        @NotNull(message = "Contact surname is required ")
        @Size(min = 3, max = 20, message = "Size must be from 3 to 20 character. ")
        String contactSurname,
        @NotNull(message = "Contact number is required ")
        long contactNumber,
        @NotNull(message = "Company type is required ")
        String companyType,
        @NotNull(message = "Work addresses is required ")
        String workAddress,
        @NotNull(message = "Operative addresses is required ")
        String operativeAddress
) {
}
