package BW5_TEAM_1.EPIC.ENERGY.SERVICES.services;

import BW5_TEAM_1.EPIC.ENERGY.SERVICES.entities.City;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.entities.Province;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.exceptions.NotFoundException;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.repositories.CitiesRepository;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.util.List;
import java.util.UUID;

@Service
public class CitiesService {
    @Autowired
    private CitiesRepository citiesRepository;
    @Autowired
    private ProvincesService provincesService;


    public City findByID(UUID id) {
        return this.citiesRepository.findById(id).orElseThrow(() -> new NotFoundException("City with id " + id + " not found"));
    }

    // SAVE CITIES
    public void loadCities() {
        String csvFile = "src/main/resources/data/comuni-italiani.csv";
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(csvFile))
                .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
                .build()) {
            List<String[]> lineInArray = reader.readAll();
            if (!lineInArray.isEmpty()) {
                lineInArray.removeFirst();
            }
            for (String[] line : lineInArray) {

                String cityName = line[2];
                String provinceName = line[3];

                List<String[]> sassariCities = lineInArray.stream().filter(city -> city[1].trim().equals("#RIF!")).toList();

                for (int i = 0; i < sassariCities.size(); i++) {
                    if (i < 9) {
                        sassariCities.get(i)[1] = ("00" + (i + 1));
                    } else if (i < 99) {
                        sassariCities.get(i)[1] = ("0" + (i + 1));
                    } else {
                        sassariCities.get(i)[1] = String.valueOf(sassariCities.get(i)[1] + 1);
                    }
                }
                Province provinceId = this.provincesService.getProvinceByName(provinceName);

                if (provinceId != null) {
                    City city = new City();
                    city.setName(cityName);
                    city.setProvince(provinceId);
                    citiesRepository.save(city);
                } else {
                    System.out.println("Province not found: " + provinceName);
                }
            }
            System.out.println("Date imported!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
