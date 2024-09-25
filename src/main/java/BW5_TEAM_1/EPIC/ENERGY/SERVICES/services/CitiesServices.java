package BW5_TEAM_1.EPIC.ENERGY.SERVICES.services;

import BW5_TEAM_1.EPIC.ENERGY.SERVICES.entities.City;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitiesServices {
    @Autowired
    private CityRepository cityRepository;

    public List<City> getCities() {
        return cityRepository.findAll();
    }
}
