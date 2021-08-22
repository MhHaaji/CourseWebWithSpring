package cw.controllers;

import cw.entities.User;
import cw.exeptions.AccessDeniedEx;
import cw.exeptions.UnknownErrorEx;
import cw.exeptions.UserNotFoundEx;
import cw.modelAssemblers.UserModelAssembler;
import cw.repositoryInterfaces.CourseRepo;
import cw.repositoryInterfaces.CourseSectionRegistrationRepo;
import cw.repositoryInterfaces.CourseSectionRepo;
import cw.repositoryInterfaces.UserRepo;
import cw.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/user")

//@EnableJpaRepositories("cw.repositoryInterfaces")
//@ComponentScan("cw.repositoryInterfaces")


public class UserController {

    @Autowired
    private final UserRepo userRepo;
    private final CourseRepo courseRepo;
    private final CourseSectionRepo courseSectionRepo;
    private final CourseSectionRegistrationRepo courseSectionRegistrationRepo;
    @Autowired
    private final UserDetailsService userDetailsService;
    private final UserModelAssembler assembler;

    public UserController(UserModelAssembler assembler, UserRepo repo, CourseRepo courseRepo, CourseSectionRepo courseSectionRepo, CourseSectionRegistrationRepo courseSectionRegistrationRepo, UserDetailsService userDetailsService) {
        this.userRepo = repo;
        this.assembler = assembler;
        this.courseRepo = courseRepo;
        this.courseSectionRepo = courseSectionRepo;
        this.courseSectionRegistrationRepo = courseSectionRegistrationRepo;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')")
    public CollectionModel<EntityModel<User>> all() {
        List<EntityModel<User>> employees = userRepo.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());
        return CollectionModel.of(employees, linkTo(methodOn(UserController.class).all()).withSelfRel());

    }

    @GetMapping("/{id}")
    public EntityModel<User> one(@PathVariable Long id) {
        User user = userRepo.findById(id).orElseThrow(() -> new UserNotFoundEx(id));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof MyUserDetails) {
            if (((MyUserDetails) authentication.getPrincipal()).getUserID() != id)
                throw new AccessDeniedEx("Hoy! fouzool ://");

            else return assembler.toModel(user);
        } else throw new UnknownErrorEx();
    }
}
