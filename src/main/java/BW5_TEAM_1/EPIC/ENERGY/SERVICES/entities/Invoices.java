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
@Table
@Getter
@Setter
@NoArgsConstructor
public class Invoices {
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
    private Clients clients;

    public Invoices(LocalDate date, int totalAmount, int invoiceNumber, InvoicesState invoicesState, Clients clients) {
        this.date = date;
        this.totalAmount = totalAmount;
        this.invoiceNumber = invoiceNumber;
        this.invoicesState = invoicesState;
        this.clients = clients;
    }
}
