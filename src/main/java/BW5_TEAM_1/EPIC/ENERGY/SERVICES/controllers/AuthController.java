package BW5_TEAM_1.EPIC.ENERGY.SERVICES.controllers;


import BW5_TEAM_1.EPIC.ENERGY.SERVICES.dto.NewUserDTO;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.dto.UserDTO;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.dto.UserLoginDTO;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.dto.UserRespDTO;
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
    public UserRespDTO login(@RequestBody UserLoginDTO user) {
        return new UserRespDTO(this.authService.checkCredentialAndGenerateToken(user));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NewUserDTO save(@RequestBody @Validated UserDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String msg = validationResult.getAllErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.joining());
            throw new BadRequestException(msg);
        } else {
            return new NewUserDTO(this.userService.saveUser(body).getId());
        }
    }
}
