package cw.security;

import cw.DTO.userDTO.MyUserSecurityDTO;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;

public class MyUserDetails implements org.springframework.security.core.userdetails.UserDetails {



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

    public MyUserDetails(MyUserSecurityDTO myUser) {
        System.out.println("Here!!");
        this.firstname = myUser.getUsername();
        this.lastname = myUser.getLastname();
        this.name = myUser.getName();
        this.phone = myUser.getPhone();
        this.active = myUser.isActive();
        System.out.println("activity: " + active);

        this.username = myUser.getUsername();
        this.password = myUser.getPassword();
        this.userID = myUser.getId();
        this.authorities = myUser.getAuthorities();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        System.out.println(username + "auths are: " + authorities);
        return authorities;
    }

    @Override
    public String getPassword() {
        System.out.println(username + " password is " + password);
        return this.password;
    }

    @Override
    public String getUsername() {
        System.out.println(username + "is username");
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
        System.out.println(username + "is Enabled? " + active );
        return active;
    }


    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MyUserDetails))
            return false;
        if (this.password == ((MyUserDetails) obj).password && this.username == ((MyUserDetails) obj).username)
            return true;
        return false;
    }
}
