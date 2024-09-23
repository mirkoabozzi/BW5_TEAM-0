package BW5_TEAM_1.EPIC.ENERGY.SERVICES.entities;

import BW5_TEAM_1.EPIC.ENERGY.SERVICES.enums.InvoicesState;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "invoices")
@Getter
@Setter
@NoArgsConstructor
public class Invoice {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;
    private LocalDate date;
    private int totalAmount;
    private int invoiceNumber;
    @Enumerated(EnumType.STRING)
    private InvoicesState invoicesState;

    @ManyToOne
    @JoinColumn(name = "clients_id")
    private Client client;

    public Invoice(LocalDate date, int totalAmount, int invoiceNumber, InvoicesState invoicesState, Client client) {
        this.date = date;
        this.totalAmount = totalAmount;
        this.invoiceNumber = invoiceNumber;
        this.invoicesState = invoicesState;
        this.client = client;
    }
}
