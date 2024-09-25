package BW5_TEAM_1.EPIC.ENERGY.SERVICES.repositories;

import BW5_TEAM_1.EPIC.ENERGY.SERVICES.entities.Client;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.entities.Invoice;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.enums.InvoicesState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface InvoicesRepository extends JpaRepository<Invoice, UUID> {
    boolean existsByInvoiceNumber(int invoiceNumber);

    Page<Invoice> findByClient(Pageable pageable, Client client);

    Page<Invoice> findByInvoicesState(Pageable pageable, InvoicesState invoicesState);

    Page<Invoice> findByDate(Pageable pageable, LocalDate data);

    @Query("SELECT i FROM Invoice i WHERE YEAR(i.date) = :year")
    Page<Invoice> findByYear(Pageable pageable, @Param("year") int year);

    @Query("SELECT i FROM Invoice i WHERE i.totalAmount BETWEEN :min AND :max")
    Page<Invoice> findByAmountRange(Pageable pageable, @Param("min") int min, @Param("max") int max);

}
