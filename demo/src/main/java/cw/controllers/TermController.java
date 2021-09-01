package cw.controllers;

import cw.DTO.termDTO.TermCreateDTO;
import cw.entities.Term;
import cw.repositoryInterfaces.*;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/term")
public class TermController {
    private final UserRepo userRepo;
    private final StudentRepo studentRepo;
    private final StaffRepo staffRepo;
    private final InstructorRepo instructorRepo;
    private final TermRepo termRepo;
    private final ModelMapper modelMapper;

    public TermController(UserRepo userRepo, StudentRepo studentRepo, StaffRepo staffRepo, InstructorRepo instructorRepo, TermRepo termRepo, ModelMapper modelMapper) {
        this.userRepo = userRepo;
        this.studentRepo = studentRepo;
        this.staffRepo = staffRepo;
        this.instructorRepo = instructorRepo;
        this.termRepo = termRepo;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('term:list')")
    public List<Term> list() {
        return termRepo.findAll();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('term:create')")
    public ResponseEntity<?> create(@RequestBody TermCreateDTO term) {

        return ResponseEntity.accepted()
                .body(termRepo.save(modelMapper.map(term, Term.class)));
    }

    @PutMapping("{termID}")
    @PreAuthorize("hasAuthority('term:update')")
    public ResponseEntity<?> openOrClose(@RequestBody boolean isOpen, @PathVariable Long termID) {
        if (termRepo.existsById(termID)) {
            Term term = termRepo.getById(termID);
            term.setOpen(isOpen);
            termRepo.save(term);
            return ResponseEntity.ok().body(term);
        } else return ResponseEntity.notFound().build();


    }


}
