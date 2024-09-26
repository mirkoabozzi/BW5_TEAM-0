package BW5_TEAM_1.EPIC.ENERGY.SERVICES.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
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

    @ManyToOne
    @JoinColumn(name = "company_type_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "work_address_id")
    private Address workAddress;

    @ManyToOne
    @JoinColumn(name = "operative_address_id")
    private Address operativeAddress;


    public Client(String companyName, long vat, String email, LocalDate insertDate, LocalDate lastContactDate, long annualTurnover, String pec, long telNumber, String contactEmail, String contactName, String contactSurname, long contactNumber, String companyLogo, Company company, Address workAddress, Address operativeAddress) {
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
        this.company = company;
        this.workAddress = workAddress;
        this.operativeAddress = operativeAddress;
    }
}
