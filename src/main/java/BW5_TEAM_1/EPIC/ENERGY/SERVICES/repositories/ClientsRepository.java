package BW5_TEAM_1.EPIC.ENERGY.SERVICES.repositories;

import BW5_TEAM_1.EPIC.ENERGY.SERVICES.entities.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface ClientsRepository extends JpaRepository<Client, UUID> {
    boolean existsByVatAndEmail(long vat, String email);

    Page<Client> findByAnnualTurnover(Pageable pageable, long annualTurnover);

    Page<Client> findByInsertDate(Pageable pageable, LocalDate insertDate);

    Page<Client> findByLastContactDate(Pageable pageable, LocalDate lastContactDate);

    @Query("SELECT c FROM Client c JOIN c.workAddress a JOIN a.city ct JOIN ct.province p WHERE p.name = :name")
    Page<Client> findByProvinceName(Pageable pageable, @Param("name") String name);

    Page<Client> findByCompanyNameContaining(Pageable pageable, String name);
}
