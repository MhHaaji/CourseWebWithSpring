package cw.security;

import cw.entities.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class MyUserDetails implements org.springframework.security.core.userdetails.UserDetails {
    private String firstname;
    private String lastname;
    private String name;
    private String phone;
    private boolean active;
    private boolean isAdmin;
    private String username;
    private String password;
    @Getter
    private Long userID;

    public MyUserDetails(User user) {
        this.firstname = user.getUsername();
        this.lastname = user.getLastname();
        this.name = user.getName();
        this.phone = user.getPhone();
        this.active = user.isActive();
        this.isAdmin = user.isAdmin();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.userID = user.getId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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

}
