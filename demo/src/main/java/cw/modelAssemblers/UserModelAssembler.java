package cw.modelAssemblers;

import cw.controllers.UserController;
import cw.entities.MyUser;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<MyUser, EntityModel<MyUser>> {

    @Override
    public EntityModel<MyUser> toModel(MyUser user) {

        return EntityModel.of(user, //
                linkTo(methodOn(UserController.class).userRead(user.getId())).withSelfRel(),
                linkTo(methodOn(UserController.class).userList()).withRel("user"));
    }


}
