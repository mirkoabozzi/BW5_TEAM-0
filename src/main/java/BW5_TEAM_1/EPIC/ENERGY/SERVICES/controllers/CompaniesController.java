package BW5_TEAM_1.EPIC.ENERGY.SERVICES.controllers;

import BW5_TEAM_1.EPIC.ENERGY.SERVICES.dto.CompaniesDTO;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.entities.Company;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.exceptions.BadRequestException;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.services.CompaniesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/companyType")
public class CompaniesController {

    @Autowired
    private CompaniesService companiesService;

    //POST SAVE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Company saveCompanyType(@RequestBody @Validated CompaniesDTO payload, BindingResult validation) {
        if (validation.hasErrors()) {
            String msg = validation.getAllErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.joining());
            throw new BadRequestException("Payload error: " + msg);
        } else {
            return this.companiesService.saveCompany(payload);
        }
    }
}
