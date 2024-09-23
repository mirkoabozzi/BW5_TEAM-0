package BW5_TEAM_1.EPIC.ENERGY.SERVICES.services;

import BW5_TEAM_1.EPIC.ENERGY.SERVICES.dto.AddressesDTO;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.entities.Address;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.exceptions.BadRequestException;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.exceptions.NotFoundException;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.repositories.AddressesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AddressesService {
    @Autowired
    private AddressesRepository addressesRepository;

    //POST SAVE
    public Address saveAddress(AddressesDTO payload) {
        if (this.addressesRepository.existsByZipNumberAndStreetAndStreetNumber(payload.zipNumber(), payload.street(), payload.streetNumber()))
            throw new BadRequestException("Address already on DB");
        Address newAddress = new Address(payload.street(), payload.streetNumber(), payload.location(), payload.zipNumber());
        return this.addressesRepository.save(newAddress);
    }

    //GET
    public Address findByID(UUID id) {
        return this.addressesRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    //PUT
    public Address update(UUID id, AddressesDTO payload) {
        Address addressFound = this.findByID(id);
        addressFound.setStreet(payload.street());
        addressFound.setStreetNumber(payload.streetNumber());
        addressFound.setLocation(payload.location());
        addressFound.setZipNumber(payload.zipNumber());
        return this.addressesRepository.save(addressFound);
    }

    //DELETE
    public void delete(UUID id) {
        this.addressesRepository.delete(this.findByID(id));
    }
}
