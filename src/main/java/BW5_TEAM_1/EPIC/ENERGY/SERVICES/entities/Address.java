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
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    private UUID id;
    private String street;
    private int street_address;
    private String location;
    private int zip_code;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    public Address(String street, int street_address, int zip_code, City city) {
        this.street = street;
        this.street_address = street_address;
        this.zip_code = zip_code;
        this.city = city;
    }

}
