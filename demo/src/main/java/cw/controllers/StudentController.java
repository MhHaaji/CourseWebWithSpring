package cw.controllers;

import cw.entities.Student;
import cw.repositoryInterfaces.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private final UserRepo userRepo;
    @Autowired

    private final CourseRepo courseRepo;
    @Autowired

    private final CourseSectionRepo courseSectionRepo;
    @Autowired

    private final CourseSectionRegistrationRepo courseSectionRegistrationRepo;
    @Autowired

    private final StudentRepo studentRepo;

    @Autowired
    private final UserDetailsService userDetailsService;

    @Autowired
    ModelMapper modelMapper;

    public StudentController(UserRepo userRepo, CourseRepo courseRepo,
                             CourseSectionRepo courseSectionRepo,
                             CourseSectionRegistrationRepo courseSectionRegistrationRepo,
                             StudentRepo studentRepo, UserDetailsService userDetailsService) {
        this.userRepo = userRepo;
        this.courseRepo = courseRepo;
        this.courseSectionRepo = courseSectionRepo;
        this.courseSectionRegistrationRepo = courseSectionRegistrationRepo;
        this.studentRepo = studentRepo;
        this.userDetailsService = userDetailsService;

    }

    @GetMapping("/all")
    @PreAuthorize("permitAll()")
    public List<?> list() {
        return studentRepo.findAll();
    }

    @GetMapping("{userID}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STUDENT')")
    public List<Student> read(@PathVariable Long userID) {
        return studentRepo.findAllByUserID(userID);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')")
    public void create(@RequestBody Student student) {
        studentRepo.save(student);
    }

    @DeleteMapping("{userID}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')")
    public void delete(@PathVariable Long userID) {

        studentRepo.deleteAllByUserID(userID);
    }

    @GetMapping("hey")
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public void hey(){
        System.out.println("hey!");
    }

}   
