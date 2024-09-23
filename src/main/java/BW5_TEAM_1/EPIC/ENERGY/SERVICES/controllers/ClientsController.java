package BW5_TEAM_1.EPIC.ENERGY.SERVICES.controllers;

import BW5_TEAM_1.EPIC.ENERGY.SERVICES.services.ClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
public class ClientsController {
    @Autowired
    private ClientsService clientsService;
}
