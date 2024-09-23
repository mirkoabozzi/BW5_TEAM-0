package BW5_TEAM_1.EPIC.ENERGY.SERVICES.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "provinces")
@Getter
@Setter
@NoArgsConstructor
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    private UUID id;
    private String initial;
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "province")
    private Set<City> city;

    public Province(String initial, String name) {
        this.initial = initial;
        this.name = name;
    }

}
