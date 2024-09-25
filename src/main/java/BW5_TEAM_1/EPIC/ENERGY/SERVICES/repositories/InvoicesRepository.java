package BW5_TEAM_1.EPIC.ENERGY.SERVICES.repositories;

import BW5_TEAM_1.EPIC.ENERGY.SERVICES.entities.Client;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.entities.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InvoicesRepository extends JpaRepository<Invoice, UUID> {
    boolean existsByInvoiceNumber(int invoiceNumber);

    Page<Invoice> findByClient(Pageable pageable, Client client);
}
