package cw.controllers;

import cw.DTO.userDTO.CreateUserDTO;
import cw.DTO.userDTO.ListUserDTO;
import cw.entities.MyUser;
import cw.exeptions.AccessDeniedEx;
import cw.exeptions.UnknownErrorEx;
import cw.exeptions.UserNotFoundEx;
import cw.modelAssemblers.UserModelAssembler;
import cw.repositoryInterfaces.CourseRepo;
import cw.repositoryInterfaces.CourseSectionRegistrationRepo;
import cw.repositoryInterfaces.CourseSectionRepo;
import cw.repositoryInterfaces.UserRepo;
import cw.security.MyUserDetails;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")


public class UserController {

    @Autowired
    private final UserRepo userRepo;
    private final CourseRepo courseRepo;
    private final CourseSectionRepo courseSectionRepo;
    private final CourseSectionRegistrationRepo courseSectionRegistrationRepo;
    @Autowired
    private final UserDetailsService userDetailsService;
    private final UserModelAssembler assembler;
    @Autowired
    ModelMapper modelMapper;

    public UserController(UserModelAssembler assembler, UserRepo repo, CourseRepo courseRepo, CourseSectionRepo courseSectionRepo, CourseSectionRegistrationRepo courseSectionRegistrationRepo, UserDetailsService userDetailsService) {
        this.userRepo = repo;
        this.assembler = assembler;
        this.courseRepo = courseRepo;
        this.courseSectionRepo = courseSectionRepo;
        this.courseSectionRegistrationRepo = courseSectionRegistrationRepo;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/all")
    public List<ListUserDTO> userList() {
        List<ListUserDTO> userDTOS = new ArrayList<>();
        userRepo.findAll().forEach(user -> userDTOS.add(modelMapper.map(user, ListUserDTO.class)));
        return userDTOS;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_STUDENT', 'ROLE_ADMIN', 'ROLE_INSTRUCTOR', 'ROLE_STAFF')")
    public EntityModel<MyUser> userRead(@PathVariable Long id) {
        MyUser myUser = userRepo.findById(id).orElseThrow(() -> new UserNotFoundEx(id));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getPrincipal() instanceof MyUserDetails) {
            if (((MyUserDetails) authentication.getPrincipal()).getUserID() != id)
                throw new AccessDeniedEx("Hoy! fouzool ://");

            else return assembler.toModel(myUser);
        } else throw new UnknownErrorEx();
    }

    @PostMapping
    @PreAuthorize("permitAll()")
    public String userCreate(@RequestBody CreateUserDTO userDTO) {

        MyUser myUser = modelMapper.map(userDTO, MyUser.class);
        myUser.setPassword(getPasswordEncoder().encode(userDTO.getPassword()));
        myUser.setActive(false);
        myUser.setName(userDTO.getFirstname() + " " + userDTO.getLastname());
        userRepo.save(myUser);
        return "Ok! user created successfully! id: " + myUser.getId();
    }

    @PutMapping("/management/enable{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
//    @PreAuthorize("isAuthenticated()")

    public String activateUser(@PathVariable Long id) {
        MyUser user = userRepo.getById(id);
        if (user.isActive()) {
            return "user is activate already!";
        } else {
            user.setActive(true);
            userRepo.save(user);
        }
        return "successful";
    }

    @PutMapping("management/disable{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    @PreAuthorize("isAuthenticated()")
    public String deactivateUser(@PathVariable Long id) {
        MyUser user = userRepo.getById(id);
        if (!user.isActive())
            return "user is deactivate already!";
        else {
            user.setActive(false);
            userRepo.save(user);
            return "successful";
        }

    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')")
    public void delete(@PathVariable Long id) {
        userRepo.deleteById(id);
    }

    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

}
