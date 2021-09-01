package cw.controllers;

import cw.entities.Instructor;
import cw.exeptions.UserNotFoundEx;
import cw.repositoryInterfaces.InstructorRepo;
import cw.repositoryInterfaces.UserRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instructor")
public class InstructorController {

    private final UserRepo userRepo;
    private final InstructorRepo instructorRepo;

    public InstructorController(UserRepo userRepo, InstructorRepo instructorRepo) {
        this.userRepo = userRepo;
        this.instructorRepo = instructorRepo;
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_INSTRUCTOR')")
    public List<Instructor> list() {
        return instructorRepo.findAll();
    }

    @GetMapping("{instructorID}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_INSTRUCTOR')")
    public Instructor read(@PathVariable Long instructorID) {
        return instructorRepo.findById(instructorID).orElseThrow(() -> new UserNotFoundEx(instructorID));
    }



    @PostMapping("{userID}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> create(@PathVariable Long userID) {
        if (!instructorRepo.existsById(userID))
            throw new UserNotFoundEx(userID);
        else
            return ResponseEntity.ok()
                    .body(instructorRepo.save(new Instructor(userID)));
    }

    @DeleteMapping("{instructorID}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long instructorID) {
        if (instructorRepo.existsById(instructorID)) {
            instructorRepo.deleteById(instructorID);
            return (ResponseEntity<?>) ResponseEntity.accepted();
        } else
            return (ResponseEntity<?>) ResponseEntity.notFound();
    }
}
