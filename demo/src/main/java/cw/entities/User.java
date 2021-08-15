package cw.entities;

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
public class User {
    private
    @GeneratedValue
    @Id
    Long id;
    private String firstname;
    private String lastname;
    private String name;
    private String phone;
    private boolean active;
    private boolean isAdmin;


    User(Long id, String firstname, String lastname, String name, String phone, boolean active, boolean isAdmin) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.name = name;
        this.phone = phone;
        this.active = active;
        this.isAdmin = isAdmin;
    }
}
