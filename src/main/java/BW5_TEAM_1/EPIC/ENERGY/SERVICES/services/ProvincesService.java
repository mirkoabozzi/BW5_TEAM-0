package BW5_TEAM_1.EPIC.ENERGY.SERVICES.services;


import BW5_TEAM_1.EPIC.ENERGY.SERVICES.entities.Province;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.exceptions.NotFoundException;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.repositories.ProvincesRepository;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class ProvincesService {
    @Autowired
    private ProvincesRepository provincesRepository;

    public List<Province> getProvinces() {
        return provincesRepository.findAll();
    }

    public Province getProvinceByName(String name) {
        List<Province> provinceList = this.provincesRepository.findByName(name);
        if (provinceList.size() == 1) {
            return provinceList.get(0);
        } else if (provinceList.size() > 1) {
            return provinceList.get(0);
        } else {
            return null;
        }
    }


    public Province findById(UUID id) {
        return provincesRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    // SAVE PROVINCES
    public void loadProvinces() throws IOException, CsvException {
        String csvFile = "src/main/resources/data/province-italiane.csv";
        try (CSVReader csvReader = new CSVReaderBuilder(new FileReader(csvFile))
                .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
                .build()) {

            List<String[]> lines = csvReader.readAll();
            if (!lines.isEmpty()) {
                lines.removeFirst();
            }
            for (String[] line : lines) {
                for (int i = 0; i < line.length; i++) {
                    line[i] = line[i].trim();
                }
                if (line.length >= 3) {
                    String codiceProvincia = line[0];
                    String nomeProvincia = line[1];
                    switch (nomeProvincia) {
                        case "Verbania" -> nomeProvincia = "Verbano-Cusio-Ossola";
                        case "Aosta" -> nomeProvincia = "Valle d'Aosta/Vallée d'Aoste";
                        case "Monza-Brianza" -> nomeProvincia = "Monza e della Brianza";
                        case "Bolzano" -> nomeProvincia = "Bolzano/Bozen";
                        case "La-Spezia" -> nomeProvincia = "La Spezia";
                        case "Reggio-Emilia" -> nomeProvincia = "Reggio nell'Emilia";
                        case "Forli-Cesena" -> nomeProvincia = "Forlì-Cesena";
                        case "Pesaro-Urbino" -> nomeProvincia = "Pesaro e Urbino";
                        case "Ascoli-Piceno" -> nomeProvincia = "Ascoli Piceno";
                        case "Reggio-Calabria" -> nomeProvincia = "Reggio Calabria";
                        case "Vibo-Valentia" -> nomeProvincia = "Vibo Valentia";
                        case "Carbonia Iglesias", "Medio Campidano" -> {
                            nomeProvincia = "Sud Sardegna";
                            codiceProvincia = "SU";
                        }
                        case "Olbia Tempio" -> nomeProvincia = "Sassari";
                        case "Ogliastra" -> nomeProvincia = "Nuoro";
                        case "Roma" -> codiceProvincia = "RM";
                    }
                    Province province = new Province();
                    province.setName(nomeProvincia);
                    province.setInitial(codiceProvincia);
                    this.provincesRepository.save(province);
                } else {
                    System.out.println("Dati importati con successo nella tabella Province!");
                }
            }
        }
    }


}
