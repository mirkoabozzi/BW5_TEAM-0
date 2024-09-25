package BW5_TEAM_1.EPIC.ENERGY.SERVICES.repositories;

import BW5_TEAM_1.EPIC.ENERGY.SERVICES.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CompaniesRepository extends JpaRepository<Company, UUID> {

    boolean existsByName(String name);
}
