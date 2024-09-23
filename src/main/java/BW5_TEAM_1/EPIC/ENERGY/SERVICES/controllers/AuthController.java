package BW5_TEAM_1.EPIC.ENERGY.SERVICES.controllers;


import BW5_TEAM_1.EPIC.ENERGY.SERVICES.entities.User;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.exceptions.BadRequestException;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.services.AuthService;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    UserService userService;

    @PostMapping("/login")
    // TODO: cambiare lo user in userDTO
    public User login(@RequestBody User user) {
        return new User(this.authService.checkCredentialAndGenerateToken(user));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    // TODO: cambiare user in newUserDTO
    public User save(@RequestBody @Validated User body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String msg = validationResult.getAllErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.joining());
            throw new BadRequestException(msg);
        } else {
            return new User(this.userService.saveEmployee(body).getId());
        }
    }
}
