package BW5_TEAM_1.EPIC.ENERGY.SERVICES.services;

import BW5_TEAM_1.EPIC.ENERGY.SERVICES.dto.ClientsDTO;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.entities.Address;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.entities.Client;
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

    //POST SAVE
    public Client saveClient(ClientsDTO payload) {
        if (clientsRepository.existsByVat(payload.vat()))
            throw new BadRequestException("Client with VAT " + payload.vat() + " already on DB");
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
                payload.companyType(),
                addressesList);
        return this.clientsRepository.save(newClient);
    }

    //GET
    public Client findByID(UUID id) {
        return this.clientsRepository.findById(id).orElseThrow(() -> new NotFoundException("Address with id " + id + " not found"));
    }

    // GET PAGES
    public Page<Client> getAllClients(int pages, int size, String sortBy) {
        Pageable pageable = PageRequest.of(pages, size, Sort.by(sortBy));
        return this.clientsRepository.findAll(pageable);
    }

    //PUT
    public Client update(UUID id, ClientsDTO payload) {
        Client clientFound = this.findByID(id);
        clientFound.setCompanyName(payload.companyName());
        clientFound.setVat(payload.vat());
        clientFound.setEmail(payload.email());
        clientFound.setPec(payload.pec());
        clientFound.setTelNumber(payload.telNumber());
        clientFound.setContactEmail(payload.contactEmail());
        clientFound.setContactSurname(payload.contactSurname());
        clientFound.setContactNumber(payload.contactNumber());
        return this.clientsRepository.save(clientFound);
    }

    //DELETE
    public void delete(UUID id) {
        this.clientsRepository.delete(this.findByID(id));
    }
}
