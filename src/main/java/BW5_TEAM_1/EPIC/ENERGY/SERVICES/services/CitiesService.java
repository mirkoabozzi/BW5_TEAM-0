package BW5_TEAM_1.EPIC.ENERGY.SERVICES.services;

import BW5_TEAM_1.EPIC.ENERGY.SERVICES.entities.City;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.exceptions.NotFoundException;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.repositories.CitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CitiesService {
    @Autowired
    private CitiesRepository citiesRepository;


    public City findByID(UUID id) {
        return this.citiesRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }
}
