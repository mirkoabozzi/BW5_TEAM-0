package BW5_TEAM_1.EPIC.ENERGY.SERVICES.runners;

import BW5_TEAM_1.EPIC.ENERGY.SERVICES.entities.City;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.entities.Province;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.repositories.CityRepository;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.repositories.ProvincesRepository;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Configuration
public class CsvToProvince {

    @Autowired
    private CityRepository cityRepository;
    private ProvincesRepository provincesRepository;

    @Bean
    public CommandLineRunner loadData() {
        return args -> {
            loadProvinces();

        };
    }

    private void loadProvinces() {
        String csvFile = "src/main/resources/data/province-italiane.csv";
        String jdbcUrl = "jdbc:postgresql://localhost:5432/EPIC-ENERGY-SERVICES";
        String username = "postgres";
        String password = "1234";

        String insertQuery = "INSERT INTO public.provinces (id, initial, name) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             CSVReader csvReader = new CSVReaderBuilder(new FileReader(csvFile))
                     .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
                     .build()) {

            String[] nextLine;
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            Set<String> provinceInserite = new HashSet<>();
            boolean isFirstLine = true;

            while ((nextLine = csvReader.readNext()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                if (nextLine.length < 2) {
                    System.out.println("Riga ignorata (meno di 2 colonne): " + String.join(",", nextLine));
                    continue;
                }

                String codiceProvincia = nextLine[0];
                String nomeProvincia = nextLine[1];

                if (!provinceInserite.contains(codiceProvincia)) {
                    provinceInserite.add(codiceProvincia);
                    preparedStatement.setObject(1, UUID.randomUUID());
                    preparedStatement.setString(2, codiceProvincia);
                    preparedStatement.setString(3, nomeProvincia);
                    preparedStatement.addBatch();
                }
            }
            preparedStatement.executeBatch();
            connection.commit();
            System.out.println("Dati importati con successo nella tabella Province!");

        } catch (Exception e) {
            System.err.println("Errore durante l'importazione dei dati delle province: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public void loadCities() {
        String csvFile = "src/main/resources/data/comuni-italiani.csv";
        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            String[] lineInArray;
            while ((lineInArray = reader.readNext()) != null) {
                String codiceProvincia = lineInArray[0];
                String nomeCittà = lineInArray[3];
                UUID provinceId = getProvinceIdByCodice(codiceProvincia);

                if (provinceId != null) {
                    City city = new City();
                    city.setId(UUID.randomUUID());
                    city.setName(nomeCittà);
                    city.setProvinceId(provinceId); // Associa provinceId
                    cityRepository.save(city);
                }
            }
            System.out.println("Dati importati con successo nella tabella Cities!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private UUID getProvinceIdByCodice(String codiceProvincia) {
        return provincesRepository.findByInitial(codiceProvincia)
                .map(Province::getId)
                .orElse(null);
    }


}
