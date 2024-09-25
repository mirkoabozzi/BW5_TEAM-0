package BW5_TEAM_1.EPIC.ENERGY.SERVICES.services;

import BW5_TEAM_1.EPIC.ENERGY.SERVICES.dto.AddressesDTO;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.entities.Address;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.entities.City;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.exceptions.BadRequestException;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.exceptions.NotFoundException;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.repositories.AddressesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AddressesService {
    @Autowired
    private AddressesRepository addressesRepository;
    @Autowired
    private CitiesService citiesService;

    //POST SAVE
    public Address saveAddress(AddressesDTO payload) {
        if (this.addressesRepository.existsByZipNumberAndStreetAndStreetNumber(payload.zipNumber(), payload.street(), payload.streetNumber()))
            throw new BadRequestException("Address already on DB");
        City cityFound = this.citiesService.findByID(UUID.fromString(payload.city()));
        Address newAddress = new Address(payload.street(), payload.streetNumber(), payload.location(), payload.zipNumber(), cityFound);
        return this.addressesRepository.save(newAddress);
    }

    //GET
    public Address findByID(UUID id) {
        return this.addressesRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    // GET PAGES
    public Page<Address> getAllAddresses(int pages, int size, String sortBy) {
        Pageable pageable = PageRequest.of(pages, size, Sort.by(sortBy));
        return this.addressesRepository.findAll(pageable);
    }

    //PUT UPDATE
    public Address updateAddress(UUID id, AddressesDTO payload) {
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
