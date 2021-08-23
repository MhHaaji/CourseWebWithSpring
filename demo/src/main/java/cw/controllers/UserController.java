package cw.controllers;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
    public CollectionModel<EntityModel<MyUser>> userList() {
        List<EntityModel<MyUser>> employees = userRepo.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());
        return CollectionModel.of(employees, linkTo(methodOn(UserController.class).userList()).withSelfRel());

    }

    @GetMapping("/{id}")
    public EntityModel<MyUser> userRead(@PathVariable Long id) {
        MyUser myUser = userRepo.findById(id).orElseThrow(() -> new UserNotFoundEx(id));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof MyUserDetails) {
            if (((MyUserDetails) authentication.getPrincipal()).getUserID() != id)
                throw new AccessDeniedEx("Hoy! fouzool ://");

            else return assembler.toModel(myUser);
        } else throw new UnknownErrorEx();
    }

    public ResponseEntity<?> userCreate (@RequestBody MyUser user){
        EntityModel<MyUser> entityModel = assembler.toModel(userRepo.save(user));
        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

}
