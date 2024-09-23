package BW5_TEAM_1.EPIC.ENERGY.SERVICES.services;


import BW5_TEAM_1.EPIC.ENERGY.SERVICES.exceptions.UnauthorizedException;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    UserService userService;

    @Autowired
    JWTTools jwtTools;

    @Autowired
    PasswordEncoder bcrypt;


    // TODO: cambiare User in UserDTO
    public String checkCredentialAndGenerateToken(User body) {
        User found = this.userService.findFromEmail(body.email());
        if (bcrypt.matches(body.password(), found.getPassword())) {
            return jwtTools.createToken(found);
        } else {
            throw new UnauthorizedException("CREDENTIAL ARE NOT VALID");
        }
    }

}
