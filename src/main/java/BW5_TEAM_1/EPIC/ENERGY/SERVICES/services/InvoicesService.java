package BW5_TEAM_1.EPIC.ENERGY.SERVICES.services;

import BW5_TEAM_1.EPIC.ENERGY.SERVICES.dto.InvoicesDTO;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.entities.Client;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.entities.Invoice;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.enums.InvoicesState;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.exceptions.BadRequestException;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.exceptions.NotFoundException;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.repositories.InvoicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class InvoicesService {
    @Autowired
    private InvoicesRepository invoicesRepository;
    @Autowired
    private ClientsService clientsService;

    //POST SAVE
    public Invoice saveInvoice(InvoicesDTO payload) {
        if (this.invoicesRepository.existsByInvoiceNumber(payload.invoiceNumber()))
            throw new BadRequestException("Invoice number already used!");
        Client clientFound = this.clientsService.findByID(UUID.fromString(payload.clientId()));
        Invoice newInvoice = new Invoice(LocalDate.now(), payload.totalAmount(), payload.invoiceNumber(), InvoicesState.valueOf(payload.invoicesState().toUpperCase()), clientFound);
        return invoicesRepository.save(newInvoice);
    }

    //GET
    public Invoice findByID(UUID id) {
        return this.invoicesRepository.findById(id).orElseThrow(() -> new NotFoundException("Invoice with id " + id + " not found"));
    }

    // GET PAGES
    public Page<Invoice> getAllInvoices(int pages, int size, String sortBy) {
        Pageable pageable = PageRequest.of(pages, size, Sort.by(sortBy));
        return this.invoicesRepository.findAll(pageable);
    }

    // FILTER BY CLIENT
    public Page<Invoice> filterByClients(int pages, int size, String sortBy) {
        Client clientFormDb = this.clientsService.findByID();
        Pageable pageable = PageRequest.of(pages, size, Sort.by(sortBy));
        return this.invoicesRepository.findByClient(pageable);
    }

    // PUT Update
    public Invoice updateInvoice(UUID id, InvoicesDTO payload) {
        Invoice invoiceFound = this.findByID(id);
        invoiceFound.setTotalAmount(payload.totalAmount());
        invoiceFound.setInvoicesState(InvoicesState.valueOf(payload.invoicesState().toUpperCase()));
        return this.invoicesRepository.save(invoiceFound);
    }

    //DELETE
    public void delete(UUID id) {
        this.invoicesRepository.delete(this.findByID(id));
    }
}
