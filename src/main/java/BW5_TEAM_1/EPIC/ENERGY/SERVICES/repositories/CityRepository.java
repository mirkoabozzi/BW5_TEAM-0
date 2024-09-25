package BW5_TEAM_1.EPIC.ENERGY.SERVICES.repositories;

import BW5_TEAM_1.EPIC.ENERGY.SERVICES.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CityRepository extends JpaRepository<City, UUID> {


}
