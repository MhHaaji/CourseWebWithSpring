package cw.security;

import cw.DTO.userDTO.MyUserSecurityDTO;
import cw.entities.MyUser;
import cw.repositoryInterfaces.InstructorRepo;
import cw.repositoryInterfaces.StaffRepo;
import cw.repositoryInterfaces.StudentRepo;
import cw.repositoryInterfaces.UserRepo;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static cw.security.UserRoles.*;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;
    private final StudentRepo studentRepo;
    private final StaffRepo staffRepo;
    private final InstructorRepo instructorRepo;
    public MyUserDetailsService(UserRepo userRepo, StudentRepo studentRepo,
                                StaffRepo staffRepo, InstructorRepo instructorRepo) {
        this.userRepo = userRepo;
        this.studentRepo = studentRepo;
        this.staffRepo = staffRepo;
        this.instructorRepo = instructorRepo;

    }

    @Override
    public UserDetails loadUserByUsername(String username) {

        Optional<MyUser> user = userRepo.findByUsername(username);
        if (user.isEmpty())
            System.out.println("FOHSHE RAKIK!");
        MyUserSecurityDTO userSecurityDTO = convertToUserDTO(user.get());

        return new MyUserDetails(userSecurityDTO);

    }

    private Collection<SimpleGrantedAuthority> findAuthorities(Long userID) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if (studentRepo.existsStudentByUserID(userID))
            authorities.addAll(STUDENT.getGrantedAuthorities());
        if (staffRepo.existsStaffByUserID(userID))
            authorities.addAll(STAFF.getGrantedAuthorities());
        if (instructorRepo.existsInstructorByUserID(userID))
            authorities.addAll(INSTRUCTOR.getGrantedAuthorities());


        return authorities;
    }

    private MyUserSecurityDTO convertToUserDTO(MyUser user) {
        MyUserSecurityDTO userSecurityDTO = new MyUserSecurityDTO();
        userSecurityDTO.setUsername(user.getUsername());
        userSecurityDTO.setActive(user.isActive());
        userSecurityDTO.setFirstname(user.getFirstname());
        userSecurityDTO.setLastname(user.getLastname());
        userSecurityDTO.setName(user.getName());
        userSecurityDTO.setPassword(user.getPassword());
        userSecurityDTO.setId(user.getId());
        userSecurityDTO.setPhone(user.getPhone());
        userSecurityDTO.setActive(user.isActive());
        userSecurityDTO.setAuthorities(findAuthorities(user.getId()));
        checkIsAdmin(userSecurityDTO);
        return userSecurityDTO;
    }

    private void checkIsAdmin(MyUserSecurityDTO user) {
        if (user.getUsername().equals("admin")
                && user.getPassword()
                .equals("$2a$10$jvHD/zRc.QZOwKLd./t5OuNnPYipI/gLrtDAG2xzucvAGtCdjZLNG")
        ) {
            user.getAuthorities().addAll(ADMIN.getGrantedAuthorities());
        }
    }
}

