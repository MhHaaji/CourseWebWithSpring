package cw.DTO.userDTO;

import cw.repositoryInterfaces.UserRepo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;

@Getter
@Setter
public class MyUserSecurityDTO {
    Long id;
    private String firstname;
    private String lastname;
    private String name;
    private String phone;
    private boolean active;
    private String username;
    private String password;
    private Collection<SimpleGrantedAuthority> authorities;

    public void setAuthorities(Collection<SimpleGrantedAuthority> authorities){
        this.authorities = authorities;
    }
}
