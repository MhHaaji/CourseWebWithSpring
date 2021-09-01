package cw.controllers;

import cw.DTO.courseDTO.CourseCreateDTO;
import cw.entities.Course;
import cw.repositoryInterfaces.CourseRepo;
import cw.repositoryInterfaces.TermRepo;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    private final CourseRepo courseRepo;
    private final TermRepo termRepo;
    private final ModelMapper modelMapper;

    public CourseController(CourseRepo courseRepo, TermRepo termRepo, ModelMapper modelMapper) {
        this.courseRepo = courseRepo;
        this.termRepo = termRepo;
        this.modelMapper = modelMapper;
    }

        @GetMapping("/all")
        @PreAuthorize("hasAuthority('course:list')")
        public List<Course> list() {
            return courseRepo.findAll();
        }

        @PostMapping
        @PreAuthorize("hasAuthority('course:create')")
        public ResponseEntity<?> create(@RequestBody CourseCreateDTO courseCreateDTO) {

            return ResponseEntity.accepted()
                    .body(courseRepo.save(modelMapper.map(courseCreateDTO, Course.class)));
        }

//        @PutMapping("{termID}")
//        @PreAuthorize("hasAuthority('course:update')")


}
