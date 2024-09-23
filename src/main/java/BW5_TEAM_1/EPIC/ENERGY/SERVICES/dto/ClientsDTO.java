package BW5_TEAM_1.EPIC.ENERGY.SERVICES.dto;

import BW5_TEAM_1.EPIC.ENERGY.SERVICES.enums.CompanyType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record ClientsDTO(
        @NotNull(message = "Company is required ")
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
        String contactName,
        @NotNull(message = "Contact surname is required ")
        String contactSurname,
        @NotNull(message = "Contact number is required ")
        long contactNumber,

        CompanyType companyType
) {
}
