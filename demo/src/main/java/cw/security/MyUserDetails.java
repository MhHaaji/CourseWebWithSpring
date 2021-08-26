package cw.security;

import cw.entities.MyUser;
import cw.repositoryInterfaces.InstructorRepo;
import cw.repositoryInterfaces.StaffRepo;
import cw.repositoryInterfaces.StudentRepo;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;

import static cw.security.UserRoles.*;

public class MyUserDetails implements org.springframework.security.core.userdetails.UserDetails {

    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private StaffRepo staffRepo;
    @Autowired
    private InstructorRepo instructorRepo;


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
        System.out.println("Here!!");
        this.firstname = myUser.getUsername();
        this.lastname = myUser.getLastname();
        this.name = myUser.getName();
        this.phone = myUser.getPhone();
        this.active = myUser.isActive();

        this.username = myUser.getUsername();
        this.password = myUser.getPassword();
        this.userID = myUser.getId();
        this.authorities = findAuthorities(myUser.getId());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return authorities;
    }

    @Override
    public String getPassword() {
        System.out.println("password!");
        return this.password;
    }

    @Override
    public String getUsername() {
        System.out.println("username!");
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
        System.out.println("active!");
        return active;
    }

    private Collection<SimpleGrantedAuthority> findAuthorities(Long userID) {
        System.out.println("ah!");
        if (studentRepo.existsStudentByUserID(userID))
            authorities.addAll(STUDENT.getGrantedAuthorities());
        if (staffRepo.existsStaffByUserID(userID))
            authorities.addAll(STAFF.getGrantedAuthorities());
        if (instructorRepo.existsInstructorByUserID(userID))
            authorities.addAll(INSTRUCTOR.getGrantedAuthorities());
        System.out.println(authorities);
        return authorities;
    }


}
