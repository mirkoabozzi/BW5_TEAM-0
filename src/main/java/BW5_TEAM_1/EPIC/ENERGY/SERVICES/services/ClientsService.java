package BW5_TEAM_1.EPIC.ENERGY.SERVICES.services;

import BW5_TEAM_1.EPIC.ENERGY.SERVICES.dto.ClientsDTO;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.entities.Client;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.exceptions.NotFoundException;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.repositories.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class ClientsService {
    @Autowired
    private ClientsRepository clientsRepository;

    //POST SAVE
    public Client saveClient(ClientsDTO payload) {
        if (clientsRepository.existsByVat(payload.vat())) throw new RuntimeException();
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
                payload.companyType());
        return this.clientsRepository.save(newClient);
    }

    //GET
    public Client findByID(UUID id) {
        return this.clientsRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
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
