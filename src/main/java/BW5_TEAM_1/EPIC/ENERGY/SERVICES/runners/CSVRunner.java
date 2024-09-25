package BW5_TEAM_1.EPIC.ENERGY.SERVICES.runners;

import BW5_TEAM_1.EPIC.ENERGY.SERVICES.services.CitiesService;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.services.ProvincesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CSVRunner implements CommandLineRunner {
    @Autowired
    private ProvincesService provincesService;
    @Autowired
    private CitiesService citiesService;

    @Override
    public void run(String... args) throws Exception {

        // import provinces on DB
//        provincesService.loadProvinces();

        // import cities DB
//        citiesService.loadCities();

    }
}
