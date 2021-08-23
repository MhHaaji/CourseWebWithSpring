package cw.entities;


import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class MyUser {
    private
    @GeneratedValue
    @Id
    Long id;
    @NotNull
    private String firstname;
    @NotNull
    private String lastname;
    @Nullable
    private String name;
    @NotNull
    private String phone;

    private boolean active;

    private boolean isAdmin;

    @NotNull
    private String username;
    @NotNull
    private String password;



    MyUser(Long id, String firstname, String lastname, String name, String phone, boolean active, boolean isAdmin) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.name = name;
        this.phone = phone;
        this.active = active;
        this.isAdmin = isAdmin;
    }
}
