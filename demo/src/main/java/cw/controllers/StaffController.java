package cw.controllers;

import cw.entities.Staff;
import cw.exeptions.UserNotFoundEx;
import cw.repositoryInterfaces.StaffRepo;
import cw.repositoryInterfaces.UserRepo;
import net.bytebuddy.utility.RandomString;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staff")
public class StaffController {
    private final UserRepo userRepo;
    private final StaffRepo staffRepo;

    public StaffController(UserRepo userRepo, StaffRepo staffRepo) {
        this.userRepo = userRepo;
        this.staffRepo = staffRepo;
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')")
    public List<Staff> list() {
        return staffRepo.findAll();
    }

    @GetMapping("{staffID}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')")
    public Staff read(@PathVariable Long staffID) {
        return staffRepo.findById(staffID).orElseThrow(() -> new UserNotFoundEx(staffID));
    }

    @PostMapping("{userID}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> create(@PathVariable Long userID) {
        if (!userRepo.existsById(userID))
            throw new UserNotFoundEx(userID);
        else
            return ResponseEntity.ok()
                    .body(staffRepo.save(new Staff(userID, userID.toString() + RandomString.make(6))));
    }

    @DeleteMapping("{staffID}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long staffID){
       if (staffRepo.existsById(staffID)) {
           staffRepo.deleteById(staffID);
            return (ResponseEntity<?>) ResponseEntity.accepted();
       } else
           return (ResponseEntity<?>) ResponseEntity.notFound();
    }


}
