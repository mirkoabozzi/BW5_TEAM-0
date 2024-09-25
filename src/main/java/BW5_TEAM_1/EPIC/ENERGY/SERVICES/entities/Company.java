package BW5_TEAM_1.EPIC.ENERGY.SERVICES.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "company_type")
@Getter
@Setter
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;

    public Company(String name) {
        this.name = name;
    }
}
