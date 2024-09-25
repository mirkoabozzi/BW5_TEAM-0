package BW5_TEAM_1.EPIC.ENERGY.SERVICES.services;

import BW5_TEAM_1.EPIC.ENERGY.SERVICES.dto.CompaniesDTO;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.entities.Company;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.exceptions.BadRequestException;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.exceptions.NotFoundException;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.repositories.CompaniesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CompaniesService {
    @Autowired
    private CompaniesRepository companiesRepository;

    // POST SAVE COMPANY
    public Company saveCompany(CompaniesDTO payload) {
        if (this.companiesRepository.existsByName(payload.name()))
            throw new BadRequestException("Company type " + payload.name() + " already exists on DB");
        Company newCompany = new Company(payload.name().toUpperCase());
        return this.companiesRepository.save(newCompany);
    }

    public Company findByID(UUID id) {
        return this.companiesRepository.findById(id).orElseThrow(() -> new NotFoundException("Company with id " + id + " not found"));
    }
}
