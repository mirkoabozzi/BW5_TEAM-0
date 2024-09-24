package BW5_TEAM_1.EPIC.ENERGY.SERVICES.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record InvoicesDTO(
        @NotNull(message = "Date is required ")
        LocalDate date,
        @NotNull(message = "Total amount is required ")
        int totalAmount,
        @NotNull(message = "Invoice number is required ")
        int invoiceNumber,
        @NotNull(message = "Invoice state is required ")
        String invoicesState,
        @NotNull(message = "Client Id is required ")
        String clientId
) {
}
