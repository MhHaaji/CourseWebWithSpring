package cw.controllers;

import cw.entities.User;
import cw.exeptions.UserNotFoundEx;
import cw.controllers.modelAssemblers.UserModelAssembler;
import cw.repositoryInterfaces.UserRepo;
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

    private final UserRepo userRepo;

    private final UserModelAssembler assembler;

    public UserController(UserModelAssembler assembler, UserRepo repo) {
        this.userRepo = repo;
        this.assembler = assembler;
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
