package BW5_TEAM_1.EPIC.ENERGY.SERVICES.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record InvoicesDTO(
        @NotNull(message = "Date is required ")
        LocalDate date,
        @NotNull(message = "Total amount is required ")
        double totalAmount,
        @NotNull(message = "Invoice number is required ")
        int invoiceNumber,
        @NotEmpty(message = "Invoice state is required ")
        String invoicesState,
        @NotEmpty(message = "Client Id is required ")
        String clientId
) {
}
