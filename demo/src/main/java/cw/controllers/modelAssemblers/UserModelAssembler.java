package cw.controllers.modelAssemblers;

import cw.controllers.UserController;
import cw.entities.User;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<User, EntityModel<User>> {

    @Override
    public EntityModel<User> toModel(User employee) {

        return EntityModel.of(employee, //
                linkTo(methodOn(UserController.class).one(employee.getId())).withSelfRel(),
                linkTo(methodOn(UserController.class).all()).withRel("users"));
    }


}
