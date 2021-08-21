package cw.security;

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

    public MyUserDetails(String firstname, String lastname, String name,
                         String phone, boolean active, boolean isAdmin,
                         String username, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.name = name;
        this.phone = phone;
        this.active = active;
        this.isAdmin = isAdmin;
        this.username = username;
        this.password = password;
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
