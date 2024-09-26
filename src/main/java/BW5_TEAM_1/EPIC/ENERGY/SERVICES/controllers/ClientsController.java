package BW5_TEAM_1.EPIC.ENERGY.SERVICES.controllers;

import BW5_TEAM_1.EPIC.ENERGY.SERVICES.dto.ClientsDTO;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.entities.Client;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.exceptions.BadRequestException;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.services.ClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clients")
public class ClientsController {
    @Autowired
    private ClientsService clientsService;

    //POST SAVE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Client saveClient(@RequestBody @Validated ClientsDTO payload, BindingResult validation) {
        if (validation.hasErrors()) {
            String msg = validation.getAllErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.joining());
            throw new BadRequestException("Payload error: " + msg);
        } else {
            return clientsService.saveClient(payload);
        }
    }

    // GET ALL
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Client> getAllClients(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "15") int size,
                                      @RequestParam(defaultValue = "companyName") String sortBy) {
        return this.clientsService.getAllClients(page, size, sortBy);
    }

    //GET BY ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Client findById(@PathVariable UUID id) {
        return this.clientsService.findByID(id);
    }

    //PUT UPDATE
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Client updateClient(@PathVariable UUID id, @RequestBody @Validated ClientsDTO payload, BindingResult validation) {
        if (validation.hasErrors()) {
            String msg = validation.getAllErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.joining());
            throw new BadRequestException("Payload error: " + msg);
        } else {
            return this.clientsService.updateClient(id, payload);
        }
    }

    //DELETE BY ID
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        this.clientsService.delete(id);
    }

    //GET FILTER BY ANNUAL TURNOVER
    @GetMapping("/turnover")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Client> filterClientsByAnnualTurnover(@RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "15") int size,
                                                      @RequestParam(defaultValue = "companyName") String sortBy,
                                                      @RequestParam(defaultValue = "0") long annualTurnover) {
        return this.clientsService.filterClientsByAnnualTurnover(page, size, sortBy, annualTurnover);
    }

    //GET FILTER BY INSERT DATE
    @GetMapping("/insertDate")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Client> filterClientsByInsertDate(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "15") int size,
                                                  @RequestParam(defaultValue = "companyName") String sortBy,
                                                  @RequestParam LocalDate insertDate) {
        return this.clientsService.filterClientsByInsertDate(page, size, sortBy, insertDate);
    }

    //GET FILTER BY LAST CONTACT DATE
    @GetMapping("/contactDate")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Client> filterClientsByLastContactDate(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "15") int size,
                                                       @RequestParam(defaultValue = "companyName") String sortBy,
                                                       @RequestParam LocalDate lastContactDate) {
        return this.clientsService.filterClientsByLastContactDate(page, size, sortBy, lastContactDate);
    }

    //GET FIND BY PROVINCE NAME
    @GetMapping("/provinceName")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Client> findByProvinceName(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "15") int size,
                                           @RequestParam(defaultValue = "companyName") String sortBy,
                                           @RequestParam String provinceName) {
        return this.clientsService.findByProvinceName(page, size, sortBy, provinceName);
    }

    //GET FIND BY PROVINCE NAME
    @GetMapping("/containsName")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Client> findByClientsContainsName(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "15") int size,
                                                  @RequestParam(defaultValue = "companyName") String sortBy,
                                                  @RequestParam String name) {
        return this.clientsService.findByClientsContainsName(page, size, sortBy, name);
    }

    //POST CLIENTS IMG
    @PostMapping("/logo")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void imgUpload(@RequestParam("logo") MultipartFile img,
                          @RequestParam("clientId") String id) throws IOException, MaxUploadSizeExceededException {
        this.clientsService.imgUpload(img, id);
    }
}
