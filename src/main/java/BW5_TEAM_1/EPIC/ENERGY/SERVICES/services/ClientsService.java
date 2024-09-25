package BW5_TEAM_1.EPIC.ENERGY.SERVICES.services;

import BW5_TEAM_1.EPIC.ENERGY.SERVICES.dto.ClientsDTO;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.entities.Address;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.entities.Client;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.entities.Company;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.exceptions.BadRequestException;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.exceptions.NotFoundException;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.repositories.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class ClientsService {
    @Autowired
    private ClientsRepository clientsRepository;
    @Autowired
    private AddressesService addressesService;
    @Autowired
    private CompaniesService companiesService;

    //POST SAVE
    public Client saveClient(ClientsDTO payload) {
        if (clientsRepository.existsByVatAndEmail(payload.vat(), payload.email()))
            throw new BadRequestException("Client with VAT " + payload.vat() + " and email " + payload.email() + " already on DB");
        Company companyTypeFound = this.companiesService.findByID(UUID.fromString(payload.companyType()));
        List<Address> addressesList = payload.addresses().stream().map(addressId -> addressesService.findByID(UUID.fromString(addressId))).toList();
        Client newClient = new Client(
                payload.companyName(),
                payload.vat(),
                payload.email(),
                LocalDate.now(),
                LocalDate.now(),
                payload.annualTurnover(),
                payload.pec(), payload.telNumber(),
                payload.contactEmail(),
                payload.contactName(),
                payload.contactSurname(),
                payload.contactNumber(),
                "https://ui-avatars.com/api/?name=" + payload.contactName() + "+" + payload.contactSurname(),
                companyTypeFound,
                addressesList);
        return this.clientsRepository.save(newClient);
    }

    //GET
    public Client findByID(UUID id) {
        return this.clientsRepository.findById(id).orElseThrow(() -> new NotFoundException("Client with id " + id + " not found"));
    }

    // GET PAGES
    public Page<Client> getAllClients(int pages, int size, String sortBy) {
        Pageable pageable = PageRequest.of(pages, size, Sort.by(sortBy));
        return this.clientsRepository.findAll(pageable);
    }

    //PUT UPDATE
    public Client updateClient(UUID id, ClientsDTO payload) {
        List<Address> addressesList = payload.addresses().stream().map(addressId -> addressesService.findByID(UUID.fromString(addressId))).toList();
        Client clientFound = this.findByID(id);
        Company companyTypeFound = this.companiesService.findByID(UUID.fromString(payload.companyType()));
        clientFound.setCompanyName(payload.companyName());
        clientFound.setVat(payload.vat());
        clientFound.setEmail(payload.email());
        clientFound.setPec(payload.pec());
        clientFound.setTelNumber(payload.telNumber());
        clientFound.setContactEmail(payload.contactEmail());
        clientFound.setContactSurname(payload.contactSurname());
        clientFound.setContactNumber(payload.contactNumber());
        clientFound.setCompany(companyTypeFound);
        clientFound.getAddressList().clear();
        clientFound.getAddressList().addAll(addressesList);
        return this.clientsRepository.save(clientFound);
    }

    //DELETE
    public void delete(UUID id) {
        this.clientsRepository.delete(this.findByID(id));
    }

    //FILTER BY ANNUAL TURNOVER
    public Page<Client> filterClientsByAnnualTurnover(int pages, int size, String sortBy, long annualTurnover) {
        Pageable pageable = PageRequest.of(pages, size, Sort.by(sortBy));
        return this.clientsRepository.findByAnnualTurnover(pageable, annualTurnover);
    }

    //FILTER BY INSERT DATE
    public Page<Client> filterClientsByInsertDate(int pages, int size, String sortBy, LocalDate localDate) {
        Pageable pageable = PageRequest.of(pages, size, Sort.by(sortBy));
        return this.clientsRepository.findByInsertDate(pageable, localDate);
    }

    //FILTER BY LAST CONTACT DATE
    public Page<Client> filterClientsByLastContactDate(int pages, int size, String sortBy, LocalDate localDate) {
        Pageable pageable = PageRequest.of(pages, size, Sort.by(sortBy));
        return this.clientsRepository.findByLastContactDate(pageable, localDate);
    }

    //FIND BY PROVINCE NAME
    public Page<Client> findByProvinceName(int pages, int size, String sortBy, String provinceName) {
        Pageable pageable = PageRequest.of(pages, size, Sort.by(sortBy));
        return this.clientsRepository.findByProvinceName(pageable, provinceName);
    }

    //FIND BY CLIENTS CONTAINS NAME
    public Page<Client> findByClientsContainsName(int pages, int size, String sortBy, String name) {
        Pageable pageable = PageRequest.of(pages, size, Sort.by(sortBy));
        return this.clientsRepository.findByCompanyNameContaining(pageable, name);
    }
}
