package cw.controllers;

import cw.repositoryInterfaces.InstructorRepo;
import cw.repositoryInterfaces.StaffRepo;
import cw.repositoryInterfaces.StudentRepo;
import cw.repositoryInterfaces.UserRepo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/term")
public class TermController {
    private final UserRepo userRepo;
    private final StudentRepo studentRepo;
    private final StaffRepo staffRepo;
    private final InstructorRepo instructorRepo;

    public TermController(UserRepo userRepo, StudentRepo studentRepo, StaffRepo staffRepo, InstructorRepo instructorRepo) {
        this.userRepo = userRepo;
        this.studentRepo = studentRepo;
        this.staffRepo = staffRepo;
        this.instructorRepo = instructorRepo;
    }


}
