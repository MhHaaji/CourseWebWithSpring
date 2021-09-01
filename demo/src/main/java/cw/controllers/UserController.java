package cw.controllers;

import cw.DTO.userDTO.CreateUserDTO;
import cw.DTO.userDTO.ListUserDTO;
import cw.Service.UserControllerService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")

@RequiredArgsConstructor
public class UserController {

    private final UserControllerService service;


    @GetMapping("/all")
    public List<ListUserDTO> userList() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_STUDENT', 'ROLE_ADMIN', 'ROLE_INSTRUCTOR', 'ROLE_STAFF')")
    public EntityModel<?> userRead(@PathVariable Long id) {
        return service.readUser(id);

    }

    @PostMapping
    @PreAuthorize("permitAll()")
    public String userCreate(@RequestBody CreateUserDTO userDTO) {
        return service.createUser(userDTO);
    }

    @PutMapping("/management/enable{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String activateUser(@PathVariable Long id) {
        return service.enableUser(id);
    }


    @PutMapping("management/disable{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deactivateUser(@PathVariable Long id) {
        return service.disableUser(id);

    }

    @PutMapping("{userID}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> update(@RequestBody CreateUserDTO userDTO, @PathVariable Long userID) {
        return service.updateUser(userDTO, userID);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')")
    public void delete(@PathVariable Long id) {
        service.deleteUser(id);
    }


}
