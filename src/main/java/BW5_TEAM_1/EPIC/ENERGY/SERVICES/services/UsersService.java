package BW5_TEAM_1.EPIC.ENERGY.SERVICES.services;


import BW5_TEAM_1.EPIC.ENERGY.SERVICES.dto.UserDTO;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.entities.User;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.exceptions.BadRequestException;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.exceptions.NotFoundException;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.repositories.UsersRepository;
import BW5_TEAM_1.EPIC.ENERGY.SERVICES.tools.MailgunSender;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsersService {

    @Autowired
    public UsersRepository userRepository;
    @Autowired
    public PasswordEncoder bcrypt;
    @Autowired
    MailgunSender mailgunSender;
    @Autowired
    private Cloudinary cloudinary;

    // GET PAGES
    public Page<User> getAllUser(int pages, int size, String sortBy) {
        Pageable pageable = PageRequest.of(pages, size, Sort.by(sortBy));
        return this.userRepository.findAll(pageable);
    }


    // GET by id
    public User findById(UUID id) {
        return this.userRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    // POST per SAVE USER
    public User saveUser(UserDTO body) {
        if (this.userRepository.existsByEmail(body.email())) {
            throw new BadRequestException("User with this email already exists");
        }
        User newUser = new User(
                body.username(),
                body.email(),
                this.bcrypt.encode(body.password()),
                body.name(),
                body.surname(),
                "https://ui-avatars.com/api/?name=" + body.name() + "+" + body.surname());
        User savedUser = this.userRepository.save(newUser);
        this.mailgunSender.sendRegistrationEmail(newUser);
        return savedUser;
    }

    public User findFromEmail(String email) {
        return this.userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException(email));
    }

    // GET user con Optional
    public Optional<User> findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }


    //PUT
    public User update(UUID id, UserDTO payload) {
        User found = this.findById(id);
        found.setUsername(payload.username());
        found.setName(payload.name());
        found.setSurname(payload.surname());
        found.setEmail(payload.email());
        return this.userRepository.save(found);
    }

    //IMG UPLOAD
    public void imgUpload(MultipartFile file, UUID id) throws IOException, MaxUploadSizeExceededException {
        User userFound = this.findById(id);
        String url = (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        userFound.setAvatar(url);
        this.userRepository.save(userFound);
    }

}
