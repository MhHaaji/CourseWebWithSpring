package cw.controllers;

import cw.modelAssemblers.UserModelAssembler;
import cw.entities.User;
import cw.exeptions.UserNotFoundEx;
import cw.repositoryInterfaces.CourseRepo;
import cw.repositoryInterfaces.CourseSectionRegistrationRepo;
import cw.repositoryInterfaces.CourseSectionRepo;
import cw.repositoryInterfaces.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
//@EnableJpaRepositories("cw.repositoryInterfaces")
//@ComponentScan("cw.repositoryInterfaces")


public class UserController {

    @Autowired
    private final UserRepo userRepo;
    private final CourseRepo courseRepo;
    private final CourseSectionRepo courseSectionRepo;
    private final CourseSectionRegistrationRepo courseSectionRegistrationRepo;

    private final UserModelAssembler assembler;

    public UserController(UserModelAssembler assembler, UserRepo repo, CourseRepo courseRepo, CourseSectionRepo courseSectionRepo, CourseSectionRegistrationRepo courseSectionRegistrationRepo) {
        this.userRepo = repo;
        this.assembler = assembler;
        this.courseRepo = courseRepo;
        this.courseSectionRepo = courseSectionRepo;
        this.courseSectionRegistrationRepo = courseSectionRegistrationRepo;
    }

    @GetMapping("/users")
    public CollectionModel<EntityModel<User>> all() {
        List<EntityModel<User>> employees = userRepo.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());
        return CollectionModel.of(employees, linkTo(methodOn(UserController.class).all()).withSelfRel());

    }

    @GetMapping("/users/{id}")
    public EntityModel<User> one(@PathVariable Long id) {
        User user = userRepo.findById(id).orElseThrow(() -> new UserNotFoundEx(id));
        return assembler.toModel(user);
    }
}
