package BW5_TEAM_1.EPIC.ENERGY.SERVICES.services;


import BW5_TEAM_1.EPIC.ENERGY.SERVICES.entities.Province;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.repositories.ProvincesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvincesService {
    @Autowired
    private ProvincesRepository provincesRepository;

    public List<Province> getProvinces() {
        return provincesRepository.findAll();
    }

}
