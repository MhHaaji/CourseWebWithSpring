package cw.Service;

import cw.DTO.userDTO.CreateUserDTO;
import cw.DTO.userDTO.ListUserDTO;
import cw.entities.MyUser;
import cw.exeptions.AccessDeniedEx;
import cw.exeptions.UnknownErrorEx;
import cw.exeptions.UserNotFoundEx;
import cw.repositoryInterfaces.UserRepo;
import cw.security.MyUserDetails;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserControllerService {
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;



    public List<ListUserDTO> getAll(){
        List<ListUserDTO> userDTOS = new ArrayList<>();
        userRepo.findAll().forEach(user -> userDTOS.add(modelMapper.map(user, ListUserDTO.class)));
        return userDTOS;
    }

    public EntityModel<?> readUser(Long id){
        MyUser myUser = userRepo.findById(id).orElseThrow(() -> new UserNotFoundEx(id));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getPrincipal() instanceof MyUserDetails) {
            System.out.println(((MyUserDetails) authentication.getPrincipal()).getUserID());
            if (!((MyUserDetails) authentication.getPrincipal()).getUserID().equals(id)){
                throw new AccessDeniedEx("Hoy! fouzool ://");
            }


            else return EntityModel.of(myUser);
        } else throw new UnknownErrorEx();
    }


    public String createUser(CreateUserDTO userDTO) {
        MyUser myUser = modelMapper.map(userDTO, MyUser.class);
        myUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        myUser.setActive(false);
        myUser.setName(userDTO.getFirstname() + " " + userDTO.getLastname());
        userRepo.save(myUser);
        return "Ok! user created successfully! id: " + myUser.getId();
    }

    public String enableUser(Long id) {
        MyUser user = userRepo.getById(id);
        if (user.isActive()) {
            return "user is activate already!";
        } else {
            user.setActive(true);
            userRepo.save(user);
        }
        return "successful";
    }

    public String disableUser(Long id) {
        MyUser user = userRepo.getById(id);
        if (!user.isActive())
            return "user is deactivate already!";
        else {
            user.setActive(false);
            userRepo.save(user);
            return "successful";
        }
    }

    public ResponseEntity<?> updateUser(CreateUserDTO userDTO, Long userID) {
        MyUser user = userRepo.findById(userID).orElseThrow(() -> new UserNotFoundEx(userID));
        if (!userDTO.getFirstname().isEmpty()) {
            user.setFirstname(userDTO.getFirstname());
        }
        if (!userDTO.getLastname().isEmpty()) {
            user.setLastname(userDTO.getLastname());
        }
        if (!userDTO.getUsername().isEmpty()) {
            user.setUsername(userDTO.getUsername());
        }
        if (!userDTO.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }
        if (!userDTO.getPhone().isEmpty()) {
            user.setPhone(userDTO.getPhone());
        }
        return ResponseEntity.ok().body(userRepo.save(user));
    }

    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }
}
