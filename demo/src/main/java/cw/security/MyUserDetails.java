package cw.security;

import cw.entities.MyUser;
import cw.repositoryInterfaces.InstructorRepo;
import cw.repositoryInterfaces.StaffRepo;
import cw.repositoryInterfaces.StudentRepo;
import cw.repositoryInterfaces.UserRepo;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;

import static cw.security.UserRoles.*;

public class MyUserDetails implements org.springframework.security.core.userdetails.UserDetails {

    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private StaffRepo staffRepo;
    @Autowired
    private InstructorRepo instructorRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private String firstname;
    private String lastname;
    private String name;
    private String phone;
    private boolean active;
    private String username;
    private String password;
    private Collection<SimpleGrantedAuthority> authorities;
    @Getter
    private Long userID;

    public MyUserDetails(MyUser myUser) {
        this.firstname = myUser.getUsername();
        this.lastname = myUser.getLastname();
        this.name = myUser.getName();
        this.phone = myUser.getPhone();
        this.active = myUser.isActive();

        this.username = myUser.getUsername();
        this.password = myUser.getPassword();
        this.userID = myUser.getId();
        findAuthorities(myUser.getId());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

    private void findAuthorities(Long userID) {
        if (studentRepo.existsStudentByUserID(userID))
            authorities.addAll(STUDENT.getGrantedAuthorities());
        if (staffRepo.existsStaffByUserID(userID))
            authorities.addAll(STAFF.getGrantedAuthorities());
        if (instructorRepo.existsInstructorByUserID(userID))
            authorities.addAll(INSTRUCTOR.getGrantedAuthorities());
    }

//    UserDetails hich(MyUser myUser) {
//        return User.builder()
//                .username(myUser.getUsername())
//                .password(myUser.getPassword())
//                .roles(STUDENT.name())
//                .authorities(authorities)
//                .build();
//
//    }

}
