package BW5_TEAM_1.EPIC.ENERGY.SERVICES.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "addresses")
@Getter
@Setter
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;
    private String street;
    private int streetNumber;
    private String location;
    private int zipNumber;

    @ManyToOne
    @JoinColumn(name = "clients_id")
    private Client client;

    public Address(String street, int streetNumber, String location, int zipNumber) {
        this.street = street;
        this.streetNumber = streetNumber;
        this.location = location;
        this.zipNumber = zipNumber;
    }
}
