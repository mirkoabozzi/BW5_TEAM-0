package BW5_TEAM_1.EPIC.ENERGY.SERVICES.entities;

import BW5_TEAM_1.EPIC.ENERGY.SERVICES.enums.CompanyType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;
    private String companyName;
    private long vat;
    private String email;
    private LocalDate insertDate;
    private LocalDate lastContactDate;
    private long annualTurnover;
    private String pec;
    private long telNumber;
    private String contactEmail;
    private String contactName;
    private String contactSurname;
    private long contactNumber;
    private String companyLogo;
    @Enumerated(EnumType.STRING)
    private CompanyType companyType;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "address_id")
    private List<Address> addressList;

    public Client(String companyName, long vat, String email, LocalDate insertDate, LocalDate lastContactDate, long annualTurnover, String pec, long telNumber, String contactEmail, String contactName, String contactSurname, long contactNumber, String companyLogo, CompanyType companyType) {
        this.companyName = companyName;
        this.vat = vat;
        this.email = email;
        this.insertDate = insertDate;
        this.lastContactDate = lastContactDate;
        this.annualTurnover = annualTurnover;
        this.pec = pec;
        this.telNumber = telNumber;
        this.contactEmail = contactEmail;
        this.contactName = contactName;
        this.contactSurname = contactSurname;
        this.contactNumber = contactNumber;
        this.companyLogo = companyLogo;
        this.companyType = companyType;
    }
}
