package BW5_TEAM_1.EPIC.ENERGY.SERVICES.controllers;

import BW5_TEAM_1.EPIC.ENERGY.SERVICES.dto.InvoicesDTO;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.entities.Invoice;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.exceptions.BadRequestException;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.services.InvoicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/invoices")
public class InvoicesController {
    @Autowired
    private InvoicesService invoicesService;

    //POST SAVE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Invoice saveInvoice(@RequestBody @Validated InvoicesDTO payload, BindingResult validation) {
        if (validation.hasErrors()) {
            String msg = validation.getAllErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.joining());
            throw new BadRequestException("Payload error: " + msg);
        } else {
            return invoicesService.saveInvoice(payload);
        }
    }

    // GET ALL
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Invoice> getAllInvoices(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "15") int size,
                                        @RequestParam(defaultValue = "invoiceNumber") String sortBy) {
        return this.invoicesService.getAllInvoices(page, size, sortBy);
    }

    // FILTER By CLIENT
    @GetMapping("/clients")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Invoice> filterInvoiceByClients(@RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "15") int size,
                                                @RequestParam(defaultValue = "invoiceNumber") String sortBy,
                                                @RequestParam UUID clientId) {
        return this.invoicesService.filterByClients(page, size, sortBy, clientId);
    }

    // FILTER BI INVOICE STATE
    @GetMapping("/state")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Invoice> filterInvoiceByInvoiceState(@RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "15") int size,
                                                     @RequestParam(defaultValue = "invoiceNumber") String sortBy,
                                                     @RequestParam String state) {
        return this.invoicesService.filterByInvoiceState(page, size, sortBy, state);
    }

    // FILTER BY INVOICE DATE
    @GetMapping("/date")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Invoice> filterInvoiceByDate(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "15") int size,
                                             @RequestParam(defaultValue = "invoiceNumber") String sortBy,
                                             @RequestParam LocalDate date) {
        return this.invoicesService.filterByInvoiceDate(page, size, sortBy, date);
    }

    // FILTER INVOICE BY YEAR
    @GetMapping("/year")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Invoice> filterInvoiceByYear(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "15") int size,
                                             @RequestParam(defaultValue = "invoiceNumber") String sortBy,
                                             @RequestParam int year) {
        return this.invoicesService.filterInvoiceByYear(page, size, sortBy, year);
    }

    // FILTER INVOICE BY AMOUNT RANGE
    @GetMapping("/amountRange")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Invoice> filterInvoiceByAmountRange(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "15") int size,
                                                    @RequestParam(defaultValue = "invoiceNumber") String sortBy,
                                                    @RequestParam int min,
                                                    @RequestParam int max) {
        return this.invoicesService.filterInvoiceByAmountRange(page, size, sortBy, min, max);
    }

    //GET BY ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Invoice findById(@PathVariable UUID id) {
        return this.invoicesService.findByID(id);
    }

    //PUT UPDATE
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Invoice updateInvoice(@PathVariable UUID id, @RequestBody @Validated InvoicesDTO payload, BindingResult validation) {
        if (validation.hasErrors()) {
            String msg = validation.getAllErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.joining());
            throw new BadRequestException("Payload error: " + msg);
        } else {
            return this.invoicesService.updateInvoice(id, payload);
        }
    }

    //DELETE BY ID
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        this.invoicesService.delete(id);
    }
}
