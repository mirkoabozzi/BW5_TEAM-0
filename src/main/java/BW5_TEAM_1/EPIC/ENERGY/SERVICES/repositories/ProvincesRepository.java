package BW5_TEAM_1.EPIC.ENERGY.SERVICES.repositories;


import BW5_TEAM_1.EPIC.ENERGY.SERVICES.entities.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProvincesRepository extends JpaRepository<Province, UUID> {

    Optional<Province> findByInitial(String initial);

}