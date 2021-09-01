package cw.entities;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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

    @NotNull
    private String username;
    @NotNull
    private String password;


    public MyUser(String firstname, String lastname, String phone, String username, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.name = firstname + " " + lastname;
        this.phone = phone;
        this.username = username;
        this.password = password;


    }

    public MyUser (String username, String password) {
        this.username = username;
        this.password = password;
        this.firstname = "haaji";
        this.lastname = "haaji";
        this.phone = "09135244700";
        this.active = true;
    }


}
